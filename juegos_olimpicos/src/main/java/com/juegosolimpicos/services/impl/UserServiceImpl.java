package com.juegosolimpicos.services.impl;

import com.juegosolimpicos.exceptions.*;
import com.juegosolimpicos.mapper.UserMapper;
import com.juegosolimpicos.model.entities.mongo.UserEntity;
import com.juegosolimpicos.model.repositories.IUserRepository;
import com.juegosolimpicos.openapi.model.SessionDto;
import com.juegosolimpicos.openapi.model.UserDto;
import com.juegosolimpicos.services.IUserService;
import com.juegosolimpicos.ws.aop.ILogeable;
import com.juegosolimpicos.ws.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@ILogeable
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public List<UserDto> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }


    @Override
    public SessionDto register(UserDto userDto) throws BaseException{
        Objects.requireNonNull(userDto);
        Objects.requireNonNull(userDto.getNickName());
        Objects.requireNonNull(userDto.getPassword());
        SessionDto sessionDto = new SessionDto();
        String token = null;
        Boolean isOk = false;

        Optional<UserEntity> userEntityOptional = userRepository.findByNickName(userDto.getNickName());

        if(userEntityOptional.isPresent()){
           throw new ConflictException(ErrorCodeEnum.EXISTS_USER, String.format("There is a user with that name %s", userDto.getNickName()));
        }
        else{
            isOk = true;
            token = jwtUtils.getJWTToken(userDto);
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userRepository.save(this.userMapper.toUserEntity(userDto,new UserEntity()));
        }
        sessionDto.setToken(token);
        sessionDto.setIsOk(isOk);
        return sessionDto;
    }

    @Override
    public UserDto updatePassword(String password) throws BaseException{
        Objects.requireNonNull(password);

        if(Objects.isNull(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new UnauthorizedException(ErrorCodeEnum.UNAUTHORIZED, "You need to be authenticated to perform this action");
        }
        UserEntity userEntity = this.userRepository.findByNickName(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_USER,"There is no user with that name in the token"));
        return this.userMapper.toUserDto(userEntity);
    }
    @Override
    public UserDto getByName(String name) throws BaseException{
        Objects.requireNonNull(name);
        UserEntity userEntity = this.userRepository.findByNickName(name)
                .orElseThrow(()->new  EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_USER, String.format("There is no user with that name %s", name)));
        return this.userMapper.toUserDto(userEntity);
    }

    @Override
    public SessionDto authenticate(UserDto userDto) throws BaseException{
        Objects.requireNonNull(userDto.getNickName());
        Objects.requireNonNull(userDto.getPassword());
        Boolean isOk = false;
        String token = null;
        SessionDto sessionDto = new SessionDto();
        UserEntity userEntity = this.userRepository.findByNickName(userDto.getNickName())
                .orElseThrow(()->new  EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_USER, String.format("There is no user with that name %s", userDto.getNickName())));

        Boolean authenticate = bCryptPasswordEncoder.matches(userDto.getPassword(),userEntity.getPassword());
        if(Boolean.TRUE.equals(authenticate)){
            isOk = true;
            token = jwtUtils.getJWTToken(userMapper.toUserDto(userEntity));
        }
        sessionDto.setToken(token);
        sessionDto.setIsOk(isOk);
        return sessionDto;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = this.userRepository.findByNickName(userName).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        List roles = user.getRole().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new User(user.getNickName(), user.getPassword(), roles);
    }
}

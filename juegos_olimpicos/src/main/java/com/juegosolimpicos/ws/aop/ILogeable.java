package com.juegosolimpicos.ws.aop;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ILogeable {

}
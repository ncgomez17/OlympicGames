import React from 'react'
import { Route, Switch } from 'react-router-dom';
import Footer from './Footer/Footer';
import TopNavBar from './NavBar/TopNavBar';
import HomePage from './HomePage/HomePage';
import OlympicGamesPage from './OlympicGamesPage/OlympicGamesPage';
import CountriesPage from './CountriesPage/CountriesPage';
import CitiesPage from './CitiesPage/CitiesPage';
import HeadquartersPage from './HeadquartersPage/HeadquartersPage';
import TypesJjooPage from './TypesJjooPage/TypesJjooPage';
import AuditPage from './AuditsPage/AuditsPage';
import ViewDetailCountry from './ViewDetail/ViewDetailCountry';
import SignUpForm from './LoginPage/SignUpForm';
import SignInForm from './LoginPage/SignInForm';
function App() {
  return (
    <div className="App">
    <TopNavBar/>
      <div className="contenido">
        <Switch>
          <Route  exact path='/' component={HomePage} />
          <Route path='/olympicGames' component={OlympicGamesPage}/>
          <Route exact path='/countries' component={CountriesPage}  />
          <Route path='/countries/:id'  component={ViewDetailCountry} />
          <Route path='/cities' component={CitiesPage}  />
          <Route path='/headquarters' component={HeadquartersPage}  />
          <Route path='/typesJjoo' component={TypesJjooPage} />
          <Route path='/login' component={SignInForm}/>
          <Route path='/register' component={SignUpForm}/>
          <Route path='/audits' component={AuditPage}/>
          
        </Switch> 
      </div>
        <Footer/>  
    </div>
  );
}

export default App;

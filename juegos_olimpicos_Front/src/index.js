import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import './index.css';
import App from './components/App';
import reportWebVitals from './reportWebVitals';
import thunk from 'redux-thunk';
import promise from 'redux-promise-middleware';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import reducer from './reducers';
import { createBrowserHistory } from "history";
import errorMiddleware from './middleware';
import 'bootstrap/dist/css/bootstrap.min.css';

const store = createStore(reducer, applyMiddleware(errorMiddleware,thunk, promise))
const history = createBrowserHistory()

ReactDOM.render(

  <React.StrictMode>
  <BrowserRouter createHistory={history}>
    <Provider store={store}>
    <App />
    </Provider>
  </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

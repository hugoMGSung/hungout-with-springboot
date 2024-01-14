import React, { Component } from "react";
import { Container } from "reactstrap"; // reactstrap 이 처음엔 없음
import MainNavbar from "../routes/MainNavbar"
import Router from "../routes/Router";
import 'bootstrap/dist/css/bootstrap.css'; // Bootstrap 추가하면 됨!!!!

import '../styles/App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <MainNavbar />
        <Container className="classname">
          <Router />
        </Container>
      </div>
    );
  }
}

export default App;

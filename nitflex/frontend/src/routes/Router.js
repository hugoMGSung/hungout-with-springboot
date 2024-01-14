import React, { Component } from 'react';
// import { BrowserRouter, Route, Switch } from 'react-router-dom';  // React V5 Switch is deprecated...
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Main from '../components/Main'

class Router extends Component {
  render() {
    return (
      <div>
        <BrowserRouter>
          <div>
            <Routes>
              <Route path="/" element={<Main />} exact />
              {/* <Route path="/login" component={Login} />
              <Route path="/join" component={Join} />
              <Route path="/movie" component={Movies} />
              <Route path="/searchMovie" component={SearchMovie} />
              <Route path="/myContent" component={Favorite} />
              <Route path="/newContent" component={NewContent} /> */}
            </Routes>
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default Router;
import React, { Component } from 'react';
import card from './assets/card.png';
import './App.css';
import SendProposal from './components/proposal/SendProposal';
import SearchProposal from './components/proposal/SearchProposal';


class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="container">
          <img src={card} style={{ height: 300 }} alt="logo" />
          <SearchProposal />
          <SendProposal />
        </div>
        {/* <Container >
          <Image className="App-logo" src={card} fluid />
          <SearchProposal />
          <SendProposal />
        </Container > */}
      </div>
    );
  }
}

export default App;
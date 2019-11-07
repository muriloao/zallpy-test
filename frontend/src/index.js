import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { Provider } from 'react-redux';
import Store from './store';
import 'bootstrap/dist/css/bootstrap.css';
import Eureka from 'eureka-js-client';

// example configuration
const eureka = new Eureka({
    // application instance information
    instance: {
        app: 'frontend',
        hostName: 'eureka-server',
        ipAddr: '127.0.0.1',
        statusPageUrl: 'http://frontend:3000',
        port: {
            '$': 3000,
            '@enabled': 'true',
        },
        vipAddress: 'localhost',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        }
    },
    eureka: {
        // eureka server host / port
        host: '127.0.0.1',
        port: 8761,
        servicePath: '/eureka/apps/'
    },
});
eureka.start(function (error) {
    console.log(error || 'complete');
});

ReactDOM.render(<Provider store={Store}><App /></Provider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();

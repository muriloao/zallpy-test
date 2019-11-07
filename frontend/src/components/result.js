import React, { Component } from "react";

export default class Result extends Component {
    render() {
        if (this.props.valid) {
            let limit = '';
            if (this.props.maxLimit === 0) {
                limit = `superior a ${this.props.maxLimit}`;
            } else if (this.props.minLimit === 0) {
                limit = `inferior a ${this.props.maxLimit}`;
            } else {
                limit = `entre ${this.props.minLimit} e ${this.props.maxLimit}`;
            }
            return (
                <div class="card card-body" style={{ backgroundColor: '#66ff99' }}>
                    <p className="lead">Parabéns, sua solicitação foi aceita !!!</p>
                    <p>Seu limite é {limit}</p>
                    <p></p>
                </div>
            );
        } else {
            return (
                <div class="card card-body" style={{ backgroundColor: '#ff6666' }}>
                    <p className="lead">Ah não, infelizmente não conseguimos te liberar crédito</p>
                    <p className="lead">Causa: {this.props.reason}</p>
                </div>
            );
        }
    }
}
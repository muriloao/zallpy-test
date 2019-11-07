import React, { Component } from "react";

export default class Invalid extends Component {
    render() {
        if (this.props.valid) {
            return (<container />);
        } else {
            return (<label style={{ color: '#ff6666' }} >
                {this.props.text}
            </label>);
        }
    }
}
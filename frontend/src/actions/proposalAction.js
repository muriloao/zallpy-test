import axios from "axios";

export const addProposal = proposalData => dispatch => {
    console.log('addProposal');
    console.log(JSON.stringify(proposalData));
    return axios
        .post("http://localhost:8080/api/proposal", proposalData, { headers: { "Access-Control-Allow-Origin": "*", "Content-Type": "application/json" }, timeout: 20000 });
};

export const getProposal = cpf => dispatch => {
    console.log(`getProposal ${cpf}`);
    console.log(JSON.stringify(cpf));
    return axios
        .get(`http://localhost:8080/api/proposal/cpf/${cpf}`);
};
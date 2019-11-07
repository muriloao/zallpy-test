import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import { connect } from "react-redux";
import { addProposal } from "../../actions/proposalAction";
import { cpfMask } from '../../utils/cpfMask';
import Invalid from '../invalid';
import Result from '../result';

class SendProposal extends Component {

    constructor(props) {
        super(props)

        this.state = {
            process: false,
            proposal: null,
            name: '',
            cpf: '',
            age: 0,
            sex: 'M',
            maritalStatus: 'SINGLE',
            dependents: 0,
            income: 0,
            errors: {
                name: true,
                cpf: true,
                age: true,
                income: true,
            }
        }
        this.handleCpfChange = this.handleCpfChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

    }
    validationForm() {
        let name = true;
        let cpf = true;
        let age = true;
        let income = true;
        if (this.state.name.length < 2) {
            name = false;
        }
        if (this.state.cpf.length !== 14) {
            cpf = false;
        }
        if (this.state.age < 18) {
            age = false;
        }
        if (this.state.income <= 0) {
            income = false;
        }
        this.setState({
            errors: {
                name, cpf, age, income
            }
        })
        return name && cpf && age && income;
    }
    onSubmit = e => {
        this.setState({ proposal: null });
        e.preventDefault();
        if (!this.validationForm()) {
            return;
        }

        this.setState({
            process: true,
        })
        const cpf = this.state.cpf.replace(".", "").replace(".", "").replace("-", "");
        const proposalData = {
            customer: {
                name: this.state.name,
                cpf: cpf,
                sex: this.state.sex
            },
            age: this.state.age,
            maritalStatus: this.state.maritalStatus,
            dependents: this.state.dependents,
            income: this.state.income,
        };
        this.props.addProposal(proposalData).then(data => {
            console.log(JSON.stringify(data));
            this.setState({ proposal: data.data, process: false });
        }, err => {
            console.log(JSON.stringify(err))
            this.setState({ process: false });
        });
    };

    onChange = e => {
        this.setState({ [e.target.name]: e.target.value });
    };

    handleCpfChange(e) {
        this.setState({ cpf: cpfMask(e.target.value) });
    }

    render() {
        return (
            <div className="jumbotron jumbotron-fluid">
                <h2>Enviar solicitação de crédito</h2>
                <p>Informe seus dados para a análise de crédito</p>
                <form class="needs-validation">
                    <div class="form-row">
                        <div class="col-md-8 mb-6">
                            <label for="name">Nome completo</label>
                            <input type="text" class="form-control" id="name" name="name" onChange={this.onChange} placeholder="Seu nome completo" value={this.state.name} required />
                            <Invalid valid={this.state.errors.name} text="Informe seu nome completo" />
                        </div>
                        <div class="col-md-4 mb-4">
                            <label for="cpf">CPF</label>
                            <input type="text" class="form-control" id="cpf" placeholder="000.000.000-00" value={this.state.cpf} onChange={this.handleCpfChange} required />
                            <Invalid valid={this.state.errors.cpf} text="Informe um CPF válido" />

                        </div>
                    </div>

                    <div class="form-row">
                        <div class="col-md-4 mb-3">
                            <label for="name">Idade</label>
                            <input type="number" class="form-control" id="age" name="age" onChange={this.onChange} placeholder="Sua idade" value={this.state.age} required />
                            <Invalid valid={this.state.errors.age} text="Informe sua idade" />
                        </div>

                        {/* SEX */}
                        <div class="form-group col-md-4 mb-3">
                            <label for="sex">Sexo</label>
                            <select value={this.state.sex} name="sex" onChange={this.onChange} required class="form-control" id="sex">
                                <option value="M">Masculino</option>
                                <option value="F">Feminino</option>
                            </select>
                        </div>

                        {/* MARITAL STATUS */}
                        <div class="form-group col-md-4 mb-3">
                            <label for="maritalStatus">Estado civil</label>
                            <select value={this.state.maritalStatus} name="maritalStatus" onChange={this.onChange} required class="form-control" id="maritalStatus">
                                <option value='SINGLE'>Solteiro(a)</option>
                                <option value='MARRIED' >Casado(a)</option>
                                <option value='DIVORCED'>Divorciado(a)</option>
                                <option value='WIDOWER'>Viúvo(a)</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">

                        {/* DEPENDENTS */}
                        <div class="col-md-4 mb-3">
                            <label for="dependents">Número dependentes</label>
                            <input type="number" class="form-control" id="dependents" name="dependents" onChange={this.onChange} placeholder="Insira a quantidade de dependents" value={this.state.dependents} required />
                        </div>

                        {/* INCOME */}
                        <div class="col-md-4 mb-3">
                            <label for="income">Renda mensal</label>
                            <input type="number" class="form-control" id="income" name="income" value={this.state.income} onChange={this.onChange} required />
                            <Invalid valid={this.state.errors.income} text="Informe sua renda mensal" />
                        </div>

                    </div>

                    {/* SUBMIT */}
                    <div class="col-md-12 mb-12">
                        <button type="button" disabled={this.state.process} class="btn btn-info btn-lg" onClick={this.onSubmit}>Enviar</button>
                    </div>
                    {(this.state.proposal != null) ?
                        <Result valid={this.state.proposal.status === 'APPROVED'} maxLimit={this.state.proposal.maxLimit} minLimit={this.state.proposal.minLimit} reason={this.state.proposal.reason} />
                        :
                        <container />
                    }
                </form>
            </div>
        )
    }
}


const mapStateToProps = state => ({
});

const mapDispatchToProps = dispatch =>
    bindActionCreators({
        addProposal
    }, dispatch);

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SendProposal);

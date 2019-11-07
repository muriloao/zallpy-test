import React, { Component } from "react";
import { bindActionCreators } from 'redux';
import { connect } from "react-redux";
import { getProposal } from '../../actions/proposalAction'
import { cpfMask } from '../../utils/cpfMask';
import Result from "../result";
import Invalid from "../invalid";


class SearchProposal extends Component {
    constructor(props) {
        super(props)

        this.state = {
            process: false,
            proposal: null,
            error: false,
            search: '',
        }
        this.handleSearchChange = this.handleSearchChange.bind(this);
        this.handleClickSearch = this.handleClickSearch.bind(this);
    }

    handleSearchChange(e) {
        this.setState({ search: cpfMask(e.target.value) });
    }

    handleClickSearch() {
        if (this.state.search.length < 14) {
            this.setState({ process: false, error: true });
            return;
        }
        this.setState({ process: true, error: false });
        const cpf = this.state.search.replace(".", "").replace(".", "").replace("-", "");
        this.props.getProposal(cpf).then((data) => {
            console.log('Response')
            console.log(JSON.stringify(data));
            if (data.data && data.data.status) {
                this.setState({ proposal: data.data, process: false });
            } else {
                this.setState({ process: false });
            }
        }, err => {
            console.log(err);
            this.setState({ process: false });
        })

    }
    render() {
        return (

            <div className="jumbotron jumbotron-fluid">
                <h2>Pesquisar solicitação de crédito</h2>
                <p>Informe seu CPF para consultar sua solicitação</p>
                <form>
                    <div className="input-group mb-6">
                        <input value={this.state.search} onChange={this.handleSearchChange} type="text" className="form-control" placeholder="Insira seu CPF" aria-label="Recipient's username" aria-describedby="button-addon2" />
                        <div className="input-group-append">
                            <button className="btn btn-outline-secondary" disabled={this.state.process} type="button" id="button-addon2" onClick={this.handleClickSearch}>{this.state.process ? 'Pesquisando...' : 'Pesquisar'}</button>
                        </div>
                    </div>
                    {(this.state.error) ?
                        <Invalid text="Informe um cpf válido" />
                        :
                        <container />
                    }

                </form>
                {(this.state.proposal != null) ?
                    <Result valid={this.state.proposal.status === 'APPROVED'} maxLimit={this.state.proposal.maxLimit} minLimit={this.state.proposal.minLimit} reason={this.state.proposal.reason} />
                    :
                    <container />
                }
            </div>
        )
    }
}

// export default SearchProposal;

const mapStateToProps = state => ({
    loading: state.proposalReducer.loading,
    errors: state.proposalReducer.errors
});

const mapDispatchToProps = dispatch =>
    bindActionCreators({
        getProposal
    }, dispatch);

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SearchProposal);

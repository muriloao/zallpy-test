import { ADD_PROPOSAL, GET_PROPOSAL } from "../actions/types";

const initialState = {
    search: [],
    proposal: {},
};

export default function (state = initialState, action) {
    switch (action.type) {
        case GET_PROPOSAL:
            return {
                ...state,
                search: action.payload,
            };
        case ADD_PROPOSAL:
            return {
                ...state,
                proposal: state.proposal,
            };
        default:
            return state;
    }
}

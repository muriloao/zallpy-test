// import { createStore } from 'redux';
// import Reducers from './reducers';
// export const Store = createStore(Reducers);


import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialState = {};
const middleware = [thunk];

let Store;
let stage = process.env.REACT_APP_STAGE;
//stage = "production"
if (stage === undefined || stage === null || stage === "dev") {
    Store = createStore(
        rootReducer,
        initialState,
        compose(
            applyMiddleware(...middleware)
        )
    );
} else {
    Store = createStore(
        rootReducer,
        initialState,
        compose(applyMiddleware(...middleware))
    );
}
export default Store;

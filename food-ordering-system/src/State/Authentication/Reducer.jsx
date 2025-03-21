
import isPresentInFavorites from "../../Components/Config/logic";
import {
    REGISTER_FAILURE
    , ADD_TO_FAVORITE_SUCCESS
    , ADD_TO_FAVORITE_FAILURE,
    ADD_TO_FAVORITE_REQUEST,
    GET_USER_FAILURE, GET_USER_SUCCESS,
    LOGIN_FAILURE,
    LOGIN_SUCCESS,
    LOGIN_REQUEST,
    LOGOUT,
    GET_USER_REQUEST,
    REGISTER_REQUEST,
    REGISTER_SUCCESS
} from "./ActionType";

const intialState = {
    user: null,
    isLoading: false,
    error: null,
    jwt: null,
    favorites: [],
    success: null
}

export const authReducer = (state = intialState, action) => {
    switch (action.type) {
        case REGISTER_REQUEST:
        case LOGIN_REQUEST:
        case GET_USER_REQUEST:
        case ADD_TO_FAVORITE_REQUEST:
            return { ...state, isLoading: true, error: null, success: null }
        case REGISTER_SUCCESS:
        case LOGIN_SUCCESS:
            return {
                ...state,
                isLoading: false,
                jwt: action.payload,
                success: "Register Success"
            };
        case GET_USER_SUCCESS:
            return {
                ...state,
                isLoading: false,
                user: action.payload,
                favorites:action.payload.favorites
            };
        case ADD_TO_FAVORITE_SUCCESS:
            return {
                ...state,
                isLoading: false,
                error: null,
                favorites: isPresentInFavorites(state.favorites, action.payload) ? state.favorites.filter((item) => item.id !== action.payload.id) :
                    [action.payload, ...state.favorites]
            }
        case LOGOUT:
            return intialState;
        case REGISTER_FAILURE:
        case LOGIN_FAILURE:
        case GET_USER_FAILURE:
        case ADD_TO_FAVORITE_FAILURE:
            return { ...state, isLoading: false, error: action.payload, success: null }

        default:
            return state;
    }
}



// import { Category } from "@mui/icons-material";
// import  { CREATE_INGREDIENT_CATEGORY_SUCCESS, CREATE_INGREDIENT_SUCCESS, GET_INGREDIENT_CATEGORY_SUCCESS, UPDATE_STOCK } from "./ActionType"

// const initialState = {
//     ingredients: [],
//     update: null,
//     Category: []
// }

// export const ingredientsReducer = (state = initialState, action) => {
//     switch (action.type) {
//         case GET_INGREDIENTS:
//             return {
//                 ...state,
//                 ingredients: action.payload
//             }
//         case GET_INGREDIENT_CATEGORY_SUCCESS:
//             return {
//                 ...state,
//                 category: action.payload
//             };
//         case CREATE_INGREDIENT_CATEGORY_SUCCESS: {
//             return {
//                 ...state,
//                 category: [...state.ingredients, action.payload]
//             }
//         };
//         case CREATE_INGREDIENT_SUCCESS:
//             return {
//                 ...state,
//                 ingredients: [...state.ingredients, action.payload]
//             }

//         case UPDATE_STOCK:
//             return {
//                 ...state,
//                 update: action.payload,
//                 ingredients: state.ingredients.map((item) =>
//                     item.id === action.payload.id ? action.payload : item)
//             };
//         default: return state;
//     }
// }
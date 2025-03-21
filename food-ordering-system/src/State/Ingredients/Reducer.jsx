import { CREATE_INGREDIENT_SUCCESS, GET_INGREDIENT_CATEGORY_SUCCESS, GET_INGREDIENTS, UPDATE_STOCK } from "./ActionType"

const intitalState={
    ingredients:[],
    update:null,
    category:[],
}

export const ingredientReducer=(state=intitalState,action)=>{
    switch(action.type){
        case GET_INGREDIENTS:
            return {
                ...state,
                ingredients:action.payload
            };
            case GET_INGREDIENT_CATEGORY_SUCCESS:
                return {
                    ...state,
                    category:action.payload
                }
                case CREATE_INGREDIENT_SUCCESS:
                    return {
                        ...state,
                        category:[...state.ingredients,action.payload]
                    }

                 case UPDATE_STOCK:   
                 return {
                    ...state,
                    update:action.payload,
                    ingredients:state.ingredients.map((item)=>item.id===action.payload.id?action.payload:item)
                 }

                 default:
                    return state;
    }
}


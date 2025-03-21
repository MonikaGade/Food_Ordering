 import {API_URL,api} from '../../Components/Config/api';
import { CREATE_INGREDIENT_CATEGORY_SUCCESS, CREATE_INGREDIENT_SUCCESS, GET_INGREDIENT_CATEGORY_SUCCESS, GET_INGREDIENTS, UPDATE_STOCK } from './ActionType';


 export const getIngredientsRestaurant=({id,jwt})=>{
    return async (dispatch)=>{
        try{
            const response=await api.get(`api/admin/ingredients/restaurant/${id}`,
                {
                    headers:{
                        Authorization:`Bearer ${jwt}`,
                    }
                }
            );
            console.log("get all ingredients",response.data);
            dispatch({
                type:GET_INGREDIENTS,
                payload:response.data,
            })
        }catch(error){
            console.log("error",error);
        }
    }
 }



 export const createIngredient=({data,jwt})=>{
    return async (dispatch)=>{
        try{
            const response=await api.post(`api/admin/ingredients`,data,
                {
                    headers:{
                        Authorization:`Bearer ${jwt}`,
                    }
                }
            );
            console.log("create ingredients",response.data);
            dispatch({
                type:CREATE_INGREDIENT_SUCCESS,
                payload:response.data,
            })
        }catch(error){
            console.log("error",error);
        }
    }
 }


 export const createIngredientCategory=({data,jwt})=>{
    console.log("data",data,"jwt",jwt);
    return async (dispatch)=>{
        try{
            const response=await api.post(`/api/admin/ingredients/category`,data,
                {
                    headers:{
                        Authorization:`Bearer ${jwt}`,
                    }
                }
            );
            console.log("create ingredients category",response.data);
            dispatch({
                type:CREATE_INGREDIENT_CATEGORY_SUCCESS,
                payload:response.data,
            })
        }catch(error){
            console.log("error",error);
        }
    }
 }


 export const getIngredientCategory=({id,jwt})=>{
    return async (dispatch)=>{
        try{
            const response=await api.get(`api/admin/ingredients/restaurant/${id}/category`,
                {
                    headers:{
                        Authorization:`Bearer ${jwt}`,
                    }
                }
            );
            console.log("get ingredients category ",response.data);
            dispatch({
                type:GET_INGREDIENT_CATEGORY_SUCCESS,
                payload:response.data,
            })
        }catch(error){
            console.log("error",error);
        }
    }
 }


 export const updateStockOfIngredient=({id,jwt})=>{
    return async (dispatch)=>{
        try{
            const response=await api.get(`api/admin/ingredients/${id}/stoke`,
                {
                    headers:{
                        Authorization:`Bearer ${jwt}`,
                    }
                }
            );

            dispatch({
                type:UPDATE_STOCK,
                payload:data,
            })
            console.log('update ingredients stock',data);
        }catch(error){
            console.log("error",error);
        }
    }
 }

 
 

 
 
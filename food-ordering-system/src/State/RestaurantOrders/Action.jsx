import {api} from '../../Components/Config/api'
import { UPDATE_CARTITEM_REQUEST } from '../Cart/ActionType'
import { GET_RESTAURANTS_ORDER_FAILURE, GET_RESTAURANTS_ORDER_REQUEST, GET_RESTAURANTS_ORDER_SUCCESS, UPDATE_ORDER_STATUS_FAILURE } from './ActionType'

export const updateOrderStatus=({orderId,orderStatus,jwt})=>{
    return async (dispatch)=>{
        try{
            dispatch({type:UPDATE_CARTITEM_REQUEST})
   const response=await api.put(   `api/admin/orders/${orderId}/${orderStatus}`,{},{
    headers:{
        Authorization:`Bearer ${jwt}`
    }
   });

   const updatedOrder=response.data;
   console.log('updated order',updateOrder);
   dispatch({
    type:UPDATE_CARTITEM_REQUEST,
    payload:updatedOrder
   });


        }catch(error){
            console.log("catch error",error);
            dispatch({type:UPDATE_ORDER_STATUS_FAILURE,error});
        }
    }
};


export const fetchRestaurantsOrder=({restaurantId,orderStatus,jwt})=>{
    return async (dispatch)=>{
        try{
            dispatch({type:GET_RESTAURANTS_ORDER_REQUEST})
   const {data}=await api.get(   `api/admin/order/restaurant/${restaurantId}`,{
   params:{order_status:orderStatus},
    headers:{
        Authorization:`Bearer ${jwt}`
    },
});

   const orders=data;
   console.log('restaurants orders ------',orders);
   dispatch({
    type:GET_RESTAURANTS_ORDER_SUCCESS,
    payload:orders
   });


        }catch(error){
            console.log("catch error",error);
            dispatch({type:GET_RESTAURANTS_ORDER_FAILURE,error});
        }
    }
}



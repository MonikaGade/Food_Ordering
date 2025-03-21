import { ADD_TO_FAVORITE_FAILURE, GET_USER_FAILURE, GET_USER_REQUEST, LOGIN_FAILURE, LOGOUT, REGISTER_FAILURE,REGISTER_REQUEST ,REGISTER_SUCCESS,LOGIN_SUCCESS, GET_USER_SUCCESS,LOGIN_REQUEST, ADD_TO_FAVORITE_REQUEST, ADD_TO_FAVORITE_SUCCESS} from "./ActionType";
import {api, API_URL} from "../../Components/Config/api"
import axios from "axios";
export const registerUser = (reqData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST })
  try {
    const { data } = await axios.post(`${API_URL}/auth/signup`, reqData.userData);
    console.log(data);
    if (data.jwt) localStorage.setItem("jwt", data.jwt);
    if (data.role === "ROLE_RESTAURANT_OWNER") {
      reqData.navigate("/admin/restaurant");
    } else {
      reqData.navigate("/")
    }
    dispatch({ type: REGISTER_SUCCESS, payload: data.jwt });
    console.log('Stored token:', localStorage.getItem('token'));
    console.log("register success", data);

  } catch (error) {
    dispatch({type:REGISTER_FAILURE,payload:error})
    console.log('error', error);
  }
}



export const loginUser = (reqData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST })
  try {
    const { data } = await axios.post(`${API_URL}/auth/signin`, reqData.userData);
    if (data.jwt) localStorage.setItem("jwt", data.jwt);
    if (data.role === "ROLE_RESTAURANT_OWNER") {
      reqData.navigate("/admin/restaurant");
    } else {
      reqData.navigate("/")
    }
    dispatch({ type: LOGIN_SUCCESS, payload: data.jwt });
    console.log("Login success", data);

  } catch (error) {
    dispatch({type:LOGIN_FAILURE,payload:error})
    console.log('error', error);
  }
}



export const getUser = (jwt) => async (dispatch) => {
  dispatch({ type: GET_USER_REQUEST })
  try {
    const { data } = await api.get(`/api/users/profile`, {
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    });


    dispatch({ type: GET_USER_SUCCESS, payload: data });
    console.log("user success", data)


  } catch (error) {
    dispatch({type:GET_USER_FAILURE,payload:error})
    console.log('error', error);
  }
}


export const addToFavorite = ({ restaurantId,jwt }) => 
  async (dispatch) => {
   console.log(jwt)
  dispatch({ type:ADD_TO_FAVORITE_REQUEST })

  try {
    const { data } = await api.put(`/api/restaurants/${restaurantId}/add-favorites`, {},{
      headers: {
        Authorization: `Bearer ${jwt}`
      }
    });
    console.log('data',data)

    dispatch({ type: ADD_TO_FAVORITE_SUCCESS, payload: data });
    console.log("ADD to favorites", data)

  } catch (error) {
  
    dispatch({type:ADD_TO_FAVORITE_FAILURE,payload:error})
    console.log('error', error);
  }
}


export const logout = () => async (dispatch) => {

  try {

    localStorage.clear();
    dispatch({ type: LOGOUT });
    console.log("Logout success")

  } catch (error) {
    console.log('error', error);
  }
}


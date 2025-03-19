import { Box, Modal } from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import { Style } from "../Cart/Cart";
import RegisterForm from "./RegisterForm";
import LoginForm from "./LoginForm";

const Auth=()=>{
    const location=useLocation();
    const navigate=useNavigate();
    const handleOnClose=()=>{
        navigate("/")
    }
    return <>
        <Modal onClose={handleOnClose} open={
            location.pathname==='/account/register'
            ||location.pathname==="/account/login"
        }   
        >
       <Box sx={Style}>
        {location.pathname==='/account/register'?<RegisterForm/>:<LoginForm/>}
       </Box>

        </Modal>
    </>
}
export default Auth;
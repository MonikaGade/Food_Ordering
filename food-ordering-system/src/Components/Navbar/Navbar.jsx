import { Avatar, Badge, Box, IconButton } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import './Navbar.css'
import { Person } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
const Navbar = () => {
  const {auth,cart}=useSelector(store=>store);
   
  const navigate = useNavigate();

  const handleAvatarClick=()=>{
    if(auth.user?.role==="ROLE_CUSTOMER"){
      navigate("/my-profile");
    }
    else{
      navigate("/admin/restaurant");
    }
  }

  return <Box className="px-5 z-50  sticky top-0 py-[.8rem] bg-[#e91e67] 1g:px-20 flex justify-between ">

    <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
      <li className="logo font-semibold text-gray-300 text-2xl" onClick={()=>navigate("/")}>
        Yummy😋 Food
      </li>
    </div>

    <div className="flex items-center space-x-2 lg:space-x-10">
      <div className="">
        <IconButton>
          <SearchIcon sx={{ fontSize: "1.5rem" }}></SearchIcon>
        </IconButton>
      </div>

     <div className="">
      { auth.user?
        <Avatar onClick={handleAvatarClick} sx={{bgcolor:"white"}}>{auth.user?.fullName[0].toUpperCase()}</Avatar>:
        <IconButton onClick={()=>navigate("/account/login")}>
          <Person/>
        </IconButton>
      }
     </div>


      <div className="">
        <IconButton onClick={()=>navigate("/cart")}>
          <Badge color="primary" badgeContent={3}> 
          
           {/* <Badge color="primary" badgeContent={ cart.cart.items.length}>  */}
            <ShoppingCartIcon sx={{ fontSize: "1.5rem" }}></ShoppingCartIcon>
          </Badge>
        </IconButton>
      </div>
    </div>
  </Box>
}
export default Navbar;
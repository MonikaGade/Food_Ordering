import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';
import FavoriteIcon from '@mui/icons-material/Favorite';
import HomeIcon from '@mui/icons-material/Home';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import NotificationsActiveIcon from '@mui/icons-material/NotificationsActive';
import LogoutIcon from '@mui/icons-material/Logout';
import InsertInvitationIcon from '@mui/icons-material/InsertInvitation';
import { Divider, Drawer, useMediaQuery } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../../../State/Authentication/Action';

const menu=[
    {title:"Orders",icon:<ShoppingBagIcon/>},
    {title:"Favorites",icon:<FavoriteIcon/>},
    {title:"Address",icon:<HomeIcon/>},
    {title:"Payments",icon:<AccountBalanceWalletIcon/>},
    {title:"Notification",icon:<NotificationsActiveIcon/>},
    {title:"Events",icon:<InsertInvitationIcon/>},
    {title:"Logout",icon:<LogoutIcon/>},
    
]


const ProfileNavigation=({open,handleClose})=>{
     const isSmallScreen=useMediaQuery("(max-width:900px)")
     const dispatch=useDispatch();
     const navigate=useNavigate();
     const handleNavigate=(item)=>{
        if(item.title==="Logout"){
            dispatch(logout());
            navigate("/")
        }
        else
         navigate(`/my-profile/${item.title.toLowerCase()}`);
     }


    return <div>
         <Drawer  anchor='left' open={isSmallScreen?open:true} onClose={handleClose} variant={"permanent"} sx={{zIndex:1,position:"sticky"}}>
           
             <div className='w-[50vw] lg:w-[20vw] h-[100vh] flex flex-col justify-center text-xl gap-8 pt-16'>
                {
                    menu.map((item,i)=><>
                       <div className='px-5 flex items-center space-x-5 cursor-pointer'  onClick={()=>handleNavigate(item)}>
                        {item.icon}
                        <span>{item.title}</span>
                       </div> 
                       {i!==menu.length-1 && <Divider/>}
                    </>)
                }
             </div>
    
         </Drawer>
    </div>

}

export default ProfileNavigation
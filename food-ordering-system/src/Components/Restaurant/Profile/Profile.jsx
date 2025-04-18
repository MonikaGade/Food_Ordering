import { useState } from "react";
import Navbar from "../../Navbar/Navbar";
import ProfileNavigation from "./ProfileNavigation";
import { Route, Routes } from "react-router-dom";
import Order from './Order'
import Address from './Address'
import AddFavorites from './AddFavorites'
import Events from './Events'
import UserProfile from './UserProfile'

const Profile=()=>{
    const [openSideBar,setOpenSideBar]=useState(false);
   return <div className="lg:flex justify-between">

    <div className="sticky h-[80vh] lg:w-[20%]">
        <ProfileNavigation open={openSideBar}/>
    </div>
    <div className="lg:w-[80%]">
        <Routes>
            <Route path="/" element={<UserProfile/>}></Route>
            <Route path="/orders" element={<Order/>}></Route>
            <Route path="/address" element={<Address/>}></Route>
            <Route path="/favorites" element={<AddFavorites/>}></Route>
            <Route path="/events" element={<Events/>}></Route>

        </Routes>
    </div>
   </div>
}

export default Profile;
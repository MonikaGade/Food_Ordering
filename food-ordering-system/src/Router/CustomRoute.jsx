import Home from '../Components/Home/Home'
import Navbar from "../Components/Navbar/Navbar"
import RestaurantDetails from '../Components/Restaurant/RestaurantDetails'
import Profile from '../Components/Restaurant/Profile/Profile'
import Cart from '../Components/Cart/Cart'
import { Route, Routes } from 'react-router-dom'
import Auth from '../Components/Auth/Auth'
import PaymentSuccess from '../Components/PaymentSuccess/PaymentSuccess'

const CustomRoute=()=>{
    return <div>
        <Navbar/>
        <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/account/:register" element={<Home/>}></Route>
            <Route path="/restaurant/:city/:title/:id" element={<RestaurantDetails/>}></Route>
            <Route path="/cart" element={<Cart/>}></Route>
            <Route path="/my-profile/*" element={<Profile/>}></Route>
            <Route path="/payment/success/:id" element={<PaymentSuccess/>}></Route>
        </Routes>
       <Auth/>
    </div>
}
export default CustomRoute
import { useSelector } from 'react-redux';
import RestaurantCard from '../RestaurantCard'
const AddFavorites=()=>{
    const {auth}=useSelector(store=>store)
    console.log(auth["favorites"])
     return <div>
        <h1 className="py-5 text-xl font-semibold text-center">My Favorites</h1>
        <div className="flex flex-wrap justify-cente gap-4">
            {auth["favorites"].map((item)=>{
                console.log("item",item)
                return <RestaurantCard item={item}></RestaurantCard>
            })}
        </div>
     </div>
}
export default AddFavorites;
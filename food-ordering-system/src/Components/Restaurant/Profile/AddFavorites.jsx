import RestaurantCard from '../RestaurantCard'
const AddFavorites=()=>{
     return <div>
        <h1 className="py-5 text-xl font-semibold text-center">My Favorites</h1>
        <div className="flex flex-wrap justify-cente gap-4">
            {[1,1,1].map((item)=><RestaurantCard></RestaurantCard>)}
        </div>
     </div>
}
export default AddFavorites;
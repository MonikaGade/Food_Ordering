export const isPresentInFavorites=(favorites,restaurant)=>{
    for(let item of favorites){
        if(restaurant.id===item.is){
            return true;
        }
    }
    return false;
}
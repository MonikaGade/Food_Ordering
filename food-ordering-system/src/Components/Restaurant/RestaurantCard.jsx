import { Card, Chip, IconButton } from "@mui/material";
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
const RestaurantCard = () => {
     return <Card className=" w-[18rem]">
          <div className={`${true ? 'cursor-pointer' : "cursor-not-allowed"} relative`}>
               <img src="https://cdn.pixabay.com/photo/2015/06/30/18/36/st-826688_1280.jpg"
                    className="w-full h-[10rem] rounded-t-md object-cover"
               />
               <Chip size="small" className="absolute top-2 left-2"
                    color={true ? "success" : "error"}
                    label={true ? "open" : "closed"}
               />
          </div>
          <div className="p-4 textPart lg:flex w-full justify-between">
               <div className="space-y-1">
                    <p className="font-semibold text-lg">Indian Fast Food</p>
                    <p className="text-gray-500 text-sm">
                         Craving it all? Dive into our global Fla.....
                    </p>
               </div>
               <div>
                    <IconButton>
                         {true ? <FavoriteIcon /> : <FavoriteBorderIcon />}
                    </IconButton>
               </div>
          </div>
     </Card>
}
export default RestaurantCard;
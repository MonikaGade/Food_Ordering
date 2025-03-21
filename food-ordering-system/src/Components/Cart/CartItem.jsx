import { Chip, IconButton } from "@mui/material";
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { removeCartItem, updateCartItem } from "../../State/Cart/Action";

const CartItem = ({ item }) => {

    const { auth, cart } = useSelector(store => store);
    const jwt = localStorage.getItem("jwt");
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const handleUpdateCartItem = (value) => {
        if (value === -1 && item.quantity ===1) {
            handleRemovecartItem();
        }
        const data={cartItemId:item.id,jwt:item.quantity+value}
        dispatch(updateCartItem({data,jwt}));
    }

    const handleRemovecartItem = () => {
        dispatch(removeCartItem({ cartItemId: item.id, jwt: auth.jwt || jwt }));
    }

    return <div className="px-5">
        <div className="lg:flex items-center lg:space-x-5">
            <div>
                <img className="w-[5rem] object-cover" src={item.food.images[0]} alt="">
                </img>
            </div>
            <div className="flex items-center justify-between lg:w-[70%]">
                <div className="spce-y-1 lg:space-y-1 lg:space-y-3 w-full">
                    <p>{item.food.name}</p>
                    <div className="flex justify-between items-center">
                        <div className="flex items-center space-x-1">
                            <IconButton onClick={()=>handleUpdateCartItem(-1)}>
                                <RemoveCircleOutlineIcon></RemoveCircleOutlineIcon>

                            </IconButton>

                            <div className="w-5 h-5 text-xs flex items-center justify-center">
                                {item.quantity}
                            </div>

                            <IconButton onClick={()=>handleUpdateCartItem(1)}>
                                <AddCircleOutlineIcon />
                            </IconButton>

                        </div>
                    </div>
                </div>

                <p>₹{item.totalPrice}</p>
            </div>
        </div>
        <div className="pt-3 space-x-2">
            {item.ingredients.map((ingredient, index) => {
                return <Chip label={ingredient} key={index}></Chip>
            })}
        </div>
    </div>
}
export default CartItem;
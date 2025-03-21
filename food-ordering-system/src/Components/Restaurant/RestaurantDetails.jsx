
import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from "@mui/material";
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import { useEffect, useState } from "react";
import MenuCard from "./MenuCard";
import { useNavigate, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getRestaurantById, getRestaurantsCategory } from "../../State/Restaurant/Action";
import {getMenuItemsByRestaurantId} from "../../State/Menu/Action"



const foodTypes = [
    {
        label: "All", value: "all"
    },
    {
        label: "Vegetarian Only", value: "Vegetarian"
    },
    {
        label: "Non-Vegetarian Only", value: "Non-Vegetarian"
    },
    {
        label: "Seasonal Only", value: "Seasonal"
    }
]




const RestaurantDetails = () => {

    let [foodType, setFoodType] = useState("all")
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const jwt = localStorage.getItem('jwt')
    const { auth, restaurant ,menu} = useSelector(store => store);
    const [selectedCategory,setSelectedCategory]=useState("");
    const { id, city } = useParams();

    const handleFilter = (e) => {
        setFoodType(e.target.value)
        console.log(e.target.value, e.target.name)
    }

    const handleFilterCategory = (e,value) => {
        setSelectedCategory(value);
        console.log(e.target.value, e.target.name,value)
    }

    console.log("restaurants details", restaurant, "jwt ")

    useEffect(() => {
        dispatch(getRestaurantById({ jwt: jwt, restaurantId: id }));
        dispatch(getRestaurantsCategory({jwt,restaurantId:id}))
       
    }, [])


    useEffect(()=>{
        dispatch(getMenuItemsByRestaurantId({jwt,restaurantId:id,
            vegetarian:foodType==="vegetarian",
            nonveg:foodType==="non_vegetarian",
            seasonal:foodType==="seasonal",
            foodCategory:selectedCategory}))
    },[selectedCategory,foodType])


    return <div className="px-5 lg:px-20">
        <section>
            <h3 className="text-gray-500 py-2 mt-10">Home/india/indian fast food/3</h3>
            <div>
                <Grid container spacing={2}>

                    <Grid item xs={12}>
                        {/* <img src={restaurant.restaurant?.images[0]} alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img> */}
                        <img src="https://cdn.pixabay.com/photo/2018/08/22/23/57/terrace-3624912_1280.jpg" alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img>
                    </Grid>
                    <Grid item xs={12} lg={6}>
                        {/* <img src={restaurant.restaurant?.images[1]} alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img> */}
                        <img src="https://cdn.pixabay.com/photo/2021/12/18/06/02/kitchen-6878026_1280.jpg" alt=""
                            className="w-full h-[40vh] object-cover"></img>
                    </Grid>

                    <Grid item xs={12} lg={6}>
                        <img src="https://cdn.pixabay.com/photo/2022/02/10/05/45/lantern-7004643_1280.jpg" alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img>
                    </Grid>
                </Grid>
            </div>

            <div className="pt-3 pb-5">
                <h1 className="text-4xl font-semibold">{restaurant.restaurant?.name}</h1>
                <p className="text-gray-500 mt-1">
                    {restaurant.restaurant?.description}
                </p>



                <div className="space-y-3 mt-3">  <p className="text-gray-500 flex items-center gap-3">
                    <LocationOnIcon />
                    <span>
                        Mumbai , Maharstra
                    </span>
                </p>

                    <p className="text-gray-500 flex items-center gap-3">
                        <CalendarTodayIcon />
                        <span>
                            Mon-Sun: 9:00 AM - 9:00 PM (Today)
                        </span>
                    </p></div>
            </div>
        </section>

        <Divider />

        <section className="pt-[2rem] lg:flex relative ">
            <div className="space-y-10 lg:w-[20%] filter ">
                <div className="box space-y-5 lg:sticky top-28">
                    <div>
                        <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                            Food Type
                        </Typography>
                        <FormControl className="py-10 space-y-5" component={"fieldset"}>
                            <RadioGroup onChange={handleFilterCategory} name="food_category" 
                         value={selectedCategory}
                            >
                                {
                                    foodTypes.map((item) => <FormControlLabel
                                        key={item.value}

                                        value={item.value} control={<Radio
                                            sx={{
                                                color: "#e91e67", // White color when not checked
                                                "&.Mui-checked": {
                                                    color: "#2563eb", // Darker blue when checked
                                                },
                                            }}
                                        />} label={item.label} />)
                                }
                            </RadioGroup>
                        </FormControl>
                    </div>

                    <Divider />
                    <div>
                        <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                            Food Category
                        </Typography>
                        <FormControl className="py-10 space-y-5" component={"fieldset"}>
                            <RadioGroup onChange={handleFilter} name="food_type" value={foodType}>
                                {
                                    restaurant.categories.map((item) => <FormControlLabel
                                        key={item}

                                        value={item.name} control={<Radio
                                            sx={{
                                                color: "#e91e67", // White color when not checked
                                                "&.Mui-checked": {
                                                    color: "#2563eb", // Darker blue when checked
                                                },
                                            }}
                                        />} label={item.name} />)
                                }
                            </RadioGroup>
                        </FormControl>

                    </div>
                </div>

            </div>
            <div className="space-y-10 lg:w-[80%] lg:pl-10">

                {
                    menu.menuItems?.map((item) => <MenuCard item={item}/>)
                }


            </div>
        </section>
    </div>
}

export default RestaurantDetails;

import { Divider, FormControl, FormControlLabel, Grid, Radio, RadioGroup, Typography } from "@mui/material";
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import { useState } from "react";
import MenuCard from "./MenuCard";

const categories = [
    "pizza",
    "biryani",
    "burger",
    "chicken",
    "rice"
]

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

const menu = [11, 3, 1, 1, 1, 1]


const RestaurantDetails = () => {

    let [foodType, setFoodType] = useState("all")
    const handleFilter = (e) => {
        console.log(e.target.value, e.target.name)
    }


    return <div className="px-5 lg:px-20">
        <section>
            <h3 className="text-gray-500 py-2 mt-10">Home/india/indian fast food/3</h3>
            <div>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <img src="https://cdn.pixabay.com/photo/2017/08/28/13/34/bar-2689548_1280.jpg" alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img>
                    </Grid>

                    <Grid item xs={12} lg={6}>
                        <img src="https://cdn.pixabay.com/photo/2016/11/18/14/05/brick-wall-1834784_1280.jpg" alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img>
                    </Grid>

                    <Grid item xs={12} lg={6}>
                        <img src="https://cdn.pixabay.com/photo/2022/02/10/05/45/lantern-7004643_1280.jpg" alt=""
                            className="w-full h-[40vh] object-cover"
                        ></img>
                    </Grid>
                </Grid>
            </div>

            <div className="pt-3 pb-5">
                <h1 className="text-4xl font-semibold">Indian Fast Food</h1>
                <p className="text-gray-500 mt-1">
                    lobd hbduygubef huyh hbhsygyy hbsgdvyf hsyqvdy gvsytdytv hsytfdybf syvdytvyg hbiniud hyyd hywyf hskdkdhdj aabdye gvsdhencokdh hdtuub
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
                            <RadioGroup onChange={handleFilter} name="food-type" value={foodType}>
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
                                    categories.map((item) => <FormControlLabel
                                        key={item}

                                        value={item} control={<Radio
                                            sx={{
                                                color: "#e91e67", // White color when not checked
                                                "&.Mui-checked": {
                                                    color: "#2563eb", // Darker blue when checked
                                                },
                                            }}
                                        />} label={item} />)
                                }
                            </RadioGroup>
                        </FormControl>

                    </div>
                </div>

            </div>
            <div className="space-y-10 lg:w-[80%] lg:pl-10">

                {
                    menu.map((item) => <MenuCard />)
                }


            </div>
        </section>
    </div>
}

export default RestaurantDetails;
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import { topMeal } from "../Data/topMeal";
import CarouselItem from "./CarouselItem";

const MultiItemCarousel = () => {
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 5,
        slidesToScroll: 1,
        autoplay: true,
        autoplayspeed: 1000
    };
    return <div>
        <Slider {...settings}>
            {topMeal.map((item,i) => {
                return <CarouselItem image={item.image} title={item.title} key={i}></CarouselItem>
            })}
        </Slider>
    </div>
}
export default MultiItemCarousel;
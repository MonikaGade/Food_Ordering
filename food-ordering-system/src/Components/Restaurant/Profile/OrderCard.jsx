import { Button, Card } from "@mui/material";

const OrderCard=()=>{
    return <Card className="flex justify-between items-center p-5">
        <div className="flex items-center space-x-5">
            <img src="https://cdn.pixabay.com/photo/2019/11/09/17/02/burger-4614022_1280.jpg" alt=""
                className="h-16 w-16"
            />
            <div>
                <p>Burger</p>
                <p>₹199</p>
            </div>
        </div>
        <div>
            <Button className="cursor-not-allowed">completed</Button>
        </div>
    </Card>
}
export default OrderCard;
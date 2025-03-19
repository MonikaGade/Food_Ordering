import OrderCard from "./OrderCard";

const Order=()=>{

    return <div className="flex items-center flex-col">
        <h1 className="text-xl text-center py-7 font-semibold ">My orders</h1>
        <div className="space-y-5 w-full lg:w-1/2">
            {
                [1,1,1,1].map((item)=>{
                    return <OrderCard/>
                })
            }
        </div>
    </div>

}
export default Order;
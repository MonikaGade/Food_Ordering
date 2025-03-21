import { Box, Button, Card, Divider, Grid, Modal, TextField } from "@mui/material";
import AddLocationAltIcon from '@mui/icons-material/AddLocationAlt';
import CartItem from "./CartItem";
import AddressCart from "./AddressCart";
import React from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { useDispatch, useSelector } from "react-redux";
import {createOrder} from "../../State/Order/Action"

// import * as Yup from "yup"

export const Style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    outline: "none",
    boxShadow: 24,
    p: 4,
};

const Cart = () => {




    const [open, setOpen] = React.useState(false);

    const handleClose = () => setOpen(false);

    const {cart,auth}=useSelector(store=>store);


    const createOrderUSingSelectedAddress = () => {

    }

    const handleOpenAddressModal = () => setOpen(true);


    const initialValues = {
        streetAddress: "",
        state: "",
        pincode: "",
        city: ""
    }

    const dispatch=useDispatch();
    //  const validationSchema=Yup.object.shape({
    //     streetAddress:Yup.string().required("Street address is required"),
    //     state:Yup.string().required("State is required"),
    //     pincide:Yup.required("pincode is required"),
    //     city:Yup.string().required("city is required"),
    //  }) 

    const handleSubmit = (values) => {
       console.log("from value",values);

        const data={
            jwt:localStorage.getItem("jwt"),
            order:{
                // restaurantId:cart.cartItems[0]?.food?.restaurant.id,
                restaurantId:103,
                deliveryAddress:{
                    fullName:auth.user?.fullName,
                    streetAddress:values.streetAddress,
                    city:values?.city,
                    state:values.state,
                    postalCode:values.pincode,
                    country:"india"
                }
            }
        }
        dispatch(createOrder(data))
        console.log("from value ",values)

       }

    return <>
        <main className="lg:flex justify-between">
            <section className="lg:w-[30%] space-y-6 lg:min-h-screen pt-10">
                {
                    cart.cartItems?.map((item, index) => <CartItem  key={index} item={item}/>)
                }
                <Divider />
                <div className="billDetails px-5 text-sm">
                    <p className="font-extralight py-5">Bill Details</p>
                    <div className="space-y-3">
                        <div className="flex justify-between text-gray-400">
                            <p>Item Total</p>
                            <p>₹{cart.cart?.total}</p>
                        </div>
                        <div className="space-y-3">
                            <div className="flex justify-between text-gray-400">
                                <p>Deliver Fee</p>
                                <p>₹21</p>
                            </div>
                        </div>
                        <div className="space-y-3">
                            <div className="flex justify-between text-gray-400">
                                <p>GST and Restaurant Charges</p>
                                <p>₹33</p>
                            </div>
                            <Divider />
                        </div>
                        <div className="flex justify-between text-gray-400">
                            <p>Total Pay</p>
                            <p>₹{(cart.cart?.total+33+21)>1?cart.cart?.total+33+21:0}</p>
                        </div>
                    </div>
                </div>
            </section>
            <Divider orientation="vertical" flexItem />
            <section className="lg:w-[70%] flex justify-center px-5 pb-10 lg:pb-0">
                <div>
                    <h1 className="text-center font-semibold text-2xl py-10">Choose Delivery Address</h1>
                    <div className="flex gap-5 flex-wrap justify-center">
                        {
                            [1, 1, 1, 1, 1].map((item, index) => {
                                return <AddressCart item={item} showButton={true} handleSelectAddress={createOrderUSingSelectedAddress} key={index} />
                            })
                        }


                        <Card className="flex gap-5 w-64 p-5">
                            <AddLocationAltIcon />
                            <div className="space-y-3 text-gray-500">
                                <h1 className="font-semibold text-lg text-white">Add New Address</h1>

                                <Button onClick={handleOpenAddressModal} variant="outlined" fullWidth >Add</Button>

                            </div>
                        </Card>


                    </div>

                </div>
            </section>

        </main>


        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={Style}>
                <Formik initialValues={initialValues} onSubmit={handleSubmit}
                // validationSchema={validationSchema}
                >
                    <Form>

                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <Field as={TextField} name="streetAddress" label=
                                    "streetAddress" placeholder="name"
                                    fullWidth
                                    variant="outlined"
                                // error={!ErrorMessage("streetAddress")}
                                // helperText={
                                //     <ErrorMessage>
                                //         {(msg)=><span className="text-red-600">{msg} </span>}
                                //     </ErrorMessage>
                                // }
                                />

                            </Grid>

                            <Grid item xs={12}>
                                <Field as={TextField} name="state" label=
                                    "state"
                                    fullWidth placeholder="state"
                                    variant="outlined"
                                // error={!ErrorMessage("streetAddress")}
                                // helperText={
                                //     <ErrorMessage>
                                //         {(msg)=><span className="text-red-600">{msg} </span>}
                                //     </ErrorMessage>
                                // }
                                />

                            </Grid>


                            <Grid item xs={12}>
                                <Field as={TextField} name="city" label=
                                    "city"
                                    fullWidth placeholder="city"
                                    variant="outlined"
                                // error={!ErrorMessage("streetAddress")}
                                // helperText={
                                //     <ErrorMessage>
                                //         {(msg)=><span className="text-red-600">{msg} </span>}
                                //     </ErrorMessage>
                                // }
                                />

                            </Grid>

                            <Grid item xs={12}>
                                <Field as={TextField} name="pincode" label=
                                    "pincode" placeholder="pincode"
                                    fullWidth
                                    variant="outlined"
                                // error={!ErrorMessage("streetAddress")}
                                // helperText={
                                //     <ErrorMessage>
                                //         {(msg)=><span className="text-red-600">{msg} </span>}
                                //     </ErrorMessage>
                                // }
                                />

                            </Grid>


                            <Grid item xs={12}>
                                <Button variant="contained" type="submit" color="primary" fullWidth>Deliver Here</Button>
                            </Grid>

                        </Grid>
                    </Form>
                </Formik>
            </Box>
        </Modal>
    </>


}
export default Cart;
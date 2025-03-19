import { Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography } from "@mui/material";
import { Field, Form, Formik, validateYupSchema } from "formik";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../State/Authentication/Action";
import { useDispatch } from "react-redux";
import { SystemUpdateOutlined } from "@mui/icons-material";



const initialValues = {
    fullName: "",
    email: "",
    password: "",
    role: "ROLE_CUSTOMER"
}



const RegisterForm = () => {
    const navigate = useNavigate();
    const dispatch=useDispatch()
    const handleSubmit = (values) => {
           console.log(values)
           
            dispatch(registerUser({userData:values,navigate}))
    }
    return <div>
        <Typography variant="h5" className="text-center">
            Register
        </Typography>
        <Formik initialValues={initialValues} onSubmit={handleSubmit}>
            <Form>
                <Field as={TextField} name="fullName" label=
                    "fullName" placeholder="name"
                    fullWidth
                    variant="outlined"
                    margin="normal"
                />

                <Field as={TextField} name="email" label=
                    "email" placeholder="email"
                    fullWidth
                    variant="outlined"
                    margin="normal"
                />


                <Field as={TextField} name="password" label=
                    "password" placeholder="password"
                    fullWidth
                    variant="outlined"
                    margin="normal"
                    type="password"
                />


               
                    <InputLabel id="role-simple-select-label">Role</InputLabel>
                    <Field as={Select} fullWidth margin="normal"
                        labelId="role-simple-select-label"
                        id="demo-simple-select"
                        name="role"
                    >
                        <MenuItem value={"ROLE_CUSTOMER"}>CUTSOMER</MenuItem>
                        <MenuItem value={"ROLE_RESTAURANT_OWNER"}>RESTAURANT OWNER</MenuItem>
                       
                    </Field>
            
                <Button sx={{ mt: 2, padding: "1rem" }} fullWidth type="submit" variant="contained">Register</Button>
            </Form>
        </Formik>

        <Typography variant="body2" align="center" sx={{ mt: 3 }}>
            If have an account already?
            <Button className="" size="small" onClick={() => navigate("/account/login")}>
                Login
            </Button>
        </Typography>




    </div>
}
export default RegisterForm;
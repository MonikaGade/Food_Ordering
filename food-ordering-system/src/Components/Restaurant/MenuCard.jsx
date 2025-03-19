import { Accordion, AccordionDetails, AccordionSummary, Button, Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore"




const demo = [
    {
        Category: "Nuts & seeds",
        ingredients: ["Cashews"]
    },
    {
        Category: "Protein",
        ingredients: ["Ground beef", "Bacon strips"]
    }
]


const MenuCard = () => {

    const handleCheckBoxChange = (value) => {
        console.log(value);
    }

    return <Accordion slotProps={{ heading: { component: 'h4' } }}>
        <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1-content"
            id="panel1-header"
        >
            <div className="lg:flex items-center justify-between">
                <div className="lg:flex items-center lg:gap-5">
                    <img className="w-[7rem] h-[7rem] object-cover " src="https://cdn.pixabay.com/photo/2024/03/01/11/08/ai-generated-8606255_1280.jpg" alt=""></img>
                    <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
                        <p className="font-semibold text-xl">Burger</p>
                        <p>₹499</p>
                        <p className="text-gray-400">nice Food</p>
                    </div>
                </div>
            </div>
        </AccordionSummary>
        <AccordionDetails>
            <form>


                <div className="flex gap-5 flex-wrap">

                    <div className="flex gap-5 flex-wrap">
                        {
                            demo.map((item) => {
                                return <div>
                                    <p>{item.Category}</p>
                                    <FormGroup>
                                        {
                                            item.ingredients.map((item) => <FormControlLabel control={<Checkbox onChange={() => handleCheckBoxChange(item)} />} label={item} />)
                                        }

                                    </FormGroup></div>
                            })
                        }
                    </div>
                </div>
                <div className="pt-5">
                    <Button variant="contained" disabled={false} type="submit">{true ? "Add to Cart" : "Out Of stock"}</Button>
                </div>
            </form>
        </AccordionDetails>
    </Accordion>

}
export default MenuCard;
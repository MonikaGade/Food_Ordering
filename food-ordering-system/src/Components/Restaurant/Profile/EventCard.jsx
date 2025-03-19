import { Card, CardActions, CardContent, CardMedia, IconButton, Typography } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
const EventCard = () => {
    return <div>
        <Card>
            <CardMedia image="https://cdn.pixabay.com/photo/2017/11/25/17/17/sandwich-2977251_1280.jpg"
                sx={{ height: 345 }}
            />
            <CardContent>
                <Typography variant="h5" >
                    Indian Fast Food
                </Typography>
                <Typography variant="body2" >
                    50% off on your first Order
                </Typography>
                <div className="py-2 space-y-2">
                    <p>{"mumbai"}</p>
                    <p className="text-sm text-blue-500">February 14, 2024 12:00 AM</p>
                    <p className="text-sm text-red-500">February 14, 2024 12:00 AM</p>
                </div>
            </CardContent>

            {
                false && <CardActions>
                    <IconButton>
                        <DeleteIcon></DeleteIcon>
                    </IconButton>
                </CardActions>
            }
        </Card>
    </div>
}
export default EventCard;

import { ThemeProvider } from '@emotion/react'
import './App.css'
import { darkTheme } from './Theme/DarkTheme'
import { CssBaseline } from '@mui/material'

import CustomRoute from './Router/CustomRoute'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'

import {getUser} from "./State/Authentication/Action.jsx"

function App() {
  const dispatch=useDispatch();
  const jwt=localStorage.getItem("jwt");
  const {auth}=useSelector(store=>store)
 useEffect(()=>{
    dispatch(getUser(auth.jwt||jwt))
  },[auth.jwt])

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
    
     <CustomRoute/>
    </ThemeProvider>
  )
}

export default App

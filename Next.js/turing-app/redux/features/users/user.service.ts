import { createAsyncThunk } from "@reduxjs/toolkit";
import { fetchAllUsersAPI } from "./user.api";


export const fetchAllUsers:any = createAsyncThunk(
    'users/fetctAllUsers',
    async (page:number)=>{
    const data : any =await fetchAllUsersAPI(1);
    return data
    }
)
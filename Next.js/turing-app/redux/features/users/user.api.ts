import { instance } from "@/redux/common/config/axios-config"


export const fetchAllUsersAPI = async (page:number)=>{

    try {
        const response = await instance.get('/all-users',{
            params:{page,limit:10}
        })
        console.log(response.data.messege)
        return response.data
    }catch(error){
        console.log(error)
    }
}
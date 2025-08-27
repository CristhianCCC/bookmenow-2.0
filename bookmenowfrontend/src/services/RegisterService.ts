import axios from "axios";
import type { user } from "../types/user";


const REGISTER_URL= "http://localhost:8090/users";

export default class RegisterService {

    static async postUser(userData: user){
        try{
            const response = await axios.post(REGISTER_URL, userData)
            return response.data;

        } catch(error: any){
            if(axios.isAxiosError(error) && error.response){
                const custom: any = new Error(error.response.data.message ?? "Something went wrong");
                custom.status = error.response.status;
                custom.response = error.response;
                throw custom
            }
            throw error;
        }
    }
}
import axios from "axios";
import type { authForm } from "../types/authForm";


const AUTH_URL = "http://localhost:8090/auth/login";

export default class AuthService {

    static async postAuthURL(authForm: authForm) {
        try{
            const response = await axios.post(AUTH_URL, authForm)
            return response.data; //returning the data

        } catch (error: any) { 
            //customized error message 
            if(axios.isAxiosError(error) && error.response){
                const custom: any = new Error(error.response.data?.message ?? "Something went wrong");
                custom.status = error.response.status;
                custom.response = error.response;
                throw custom;
            }
        throw error;
        }
    }
}
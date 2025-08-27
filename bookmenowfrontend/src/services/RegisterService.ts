import axios from "axios";


const REGISTER_URL= "http://localhost:8090/users";

export default class RegisterService {

    static async postUser(RegisterService: RegisterService){
        try{
            const response = await axios.post(REGISTER_URL, RegisterService)
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
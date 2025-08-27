import { useForm, type SubmitHandler } from "react-hook-form"
import formImg from "./../../img/form.png"
import AuthService from "../../services/AuthService";
import { useState } from "react";
import { Link } from "react-router-dom";


type FormData = {
email: string;
password: string;
};

export default function Auth () {

    const [successMessage, setSuccessMessage] = useState<string | null> (null);

    /*wrapping the errors*/
    const {
        register,
        handleSubmit,
        formState: { errors },
        setError
    } = useForm<FormData>();

    //connecting onSubmit with axios and making the bridge with backend
    const onSubmit: SubmitHandler<FormData> = async (data) => {
        try{
            //calling backend for email and password
            const response = await AuthService.postAuthURL(data);

            //assigning the token from the backend
            const token = response.token;

            //saving token in localstorage
            localStorage.setItem("token", token); 

            //when user is authenticated they'll see this message
            setSuccessMessage("User authenticated, redirecting");
            setTimeout(() => {window.location.href = "/profile"; }, 2000);

        }catch ( error: any ){

            if(error.response?.status === 401 || error.response.status === 401){
                setError ( "password", {
                    type: "manual",
                    message: "Email or Password incorrect"
                });
                return ;
            }
            setError("email", {type: "manual", message: "connection error, please try it again"});
        }
    }

    
    return (
        <>



        {/*for the whole form*/}
            <div className="grid grid-cols-1 lg:grid-cols-2 pt-20 xl:p-40 bg-gradient-to-b from-blue-500 to-emerald-500 rounded-2xl">

                {/*for the img*/}
                <div>
                    <img src={formImg} alt="form-img" className="w-full h-full lg:w-300 lg:h-max rounded-2xl hidden md:block blur-xs "/>
                </div>
                {/*for the content inside of the form box*/}
                <form onSubmit={handleSubmit(onSubmit)} className="bg-gradient-to-b from-blue-500 to-emerald-500 md:bg-none md:bg-white rounded-2xl flex flex-col text-center items-center gap-3 ">

                    <h1 className="text-4xl font-bold pt-15 text-white md:text-emerald-500">Sign-in</h1>

                    {/*email*/}
                    <label className="font-bold text-white md:text-black">Email</label>
                    <input 
                    {...register("email", {
                        required: true,
                        pattern: /^\S+@\S+$/i
                    })}
                    className="bg-white md:bg-none md:border md:border-gray-400 rounded-2xl w-100 text-center"
                    placeholder="email@example.com"
                    />
                    {errors?.email?.type === "required" && <p className="bg-red-600 rounded-2xl px-20 text-white">Please enter your Email</p>}
                    {errors.email?.type === "pattern" && <p className="bg-red-600 rounded-2xl px-20 text-white">Email is incorrect</p>}

                    {/*password*/}
                    <label className="font-bold text-white md:text-black">Password</label>
                    <input
                    { ...register("password", {
                        required: true,
                    }) }
                    type="password"
                    className="bg-white md:bg-none md:border md:border-gray-400 rounded-2xl w-100 text-center"
                    placeholder="Password"
                    />
                    {errors?.password?.type === "required" && <p className="bg-red-600 rounded-2xl px-20 text-white">Please enter your password</p>}
                    {errors.password && (<p className="bg-red-600 rounded-2xl px-20 text-white">{errors.password.message}</p>)}
                    
                    <input type="submit" value="Log in" className="text-white bg-gradient-to-br from-green-400 to-blue-600 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2 cursor-pointer"/>
                
                    {/*if auth, success message will show on screen*/}
                    {successMessage && <div className="bg-emerald-500 text-white rounded-2xl px-5 py-2 mt-2"> { successMessage } </div>}
                    <Link to="/register" className=" text-purple-50 md:text-pink-500 flex md:underline font-extrabold">Are you new? click here and create an account</Link>
                </form>
            </div>
        </>
    )
}
import { useForm, type SubmitHandler } from "react-hook-form"
import formImg from "./../../img/form.png"
import AuthService from "../../services/AuthService";
import { useState } from "react";
import { Link } from "react-router-dom";
import { easeInOut, motion } from "motion/react";


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
            setError("email", 
            {type: "manual", 
            message: "connection error, please try it again"});
        }
    }

    
    return (
        <>



        {/*for the whole form*/}
            <div className="bg-cover relative">
                <img src={formImg} alt="form-img" className="object-cover w-screen h-screen brightness-75 blur-xs"/>
                    <div className="flex justify-center">
                    <form onSubmit={handleSubmit(onSubmit)} className="absolute translate-y-15 top-[30px] flex justify-center flex-col p-15 gap-3 rounded-2xl backdrop-blur-sm bg-white/30">

                        <h1 className="text-2xl font-bold text-white text-center">Sign-in</h1>

                        {/*email*/}
                        <label className="font-bold text-white">Email</label>
                        <motion.input 
                        {...register("email", {
                            required: true,
                            pattern: /^\S+@\S+$/i
                        })}
                        className="text-center border border-black rounded-2xl text-white font-bold"
                        transition={{ duration: 0.4, ease: easeInOut }}
                        whileHover={{ scale: 1.1 }}
                        whileTap={{ scale: 0.5 }}
                        placeholder="email@example.com"
                        />
                        {errors?.email?.type === "required" && 
                        <motion.p 
                        initial = {{ opacity: 0, y: 20 }}
                        animate = {{ opacity: 1, y: 0 }}
                        className="bg-red-600 rounded-2xl px-20 text-white">Please enter your Email</motion.p>}
                        {errors.email?.type === "pattern" && 
                        <motion.p 
                        initial = {{ opacity: 0, y: 20 }}
                        animate = {{ opacity: 1, y: 0 }}
                        className="bg-red-600 rounded-2xl px-20 text-white">Email is incorrect</motion.p>}

                        {/*password*/}
                        <label className="font-bold text-white">Password</label>
                        <motion.input
                        { ...register("password", {
                            required: true,
                        }) }
                        type="password"
                        className="text-center border border-black rounded-2xl text-white font-bold"
                        transition={{ duration: 0.4, ease: easeInOut }}
                        whileHover={{ scale: 1.1 }}
                        whileTap={{ scale: 0.5 }}
                        placeholder="Password"
                        />
                        {errors?.password?.type === "required" && 
                        <motion.p 
                        initial = {{opacity: 0, y: 20}}
                        animate = {{opacity: 1, y: 0}}
                        className="bg-red-600 rounded-2xl px-20 text-white">Please enter your password</motion.p>}
                        {errors.password && 
                        (<motion.p 
                        initial = {{ opacity:0, y:20 }}
                        animate = {{ opacity:1, y:0 }}
                        className="bg-red-600 rounded-2xl px-20 text-white">{errors.password.message}</motion.p>)}
                        
                        <motion.input 
                        type="submit" 
                        value="Log in" 
                        className="text-white bg-blue-600  font-medium rounded-lg text-sm px-5 py-2.5 text-center cursor-pointer"
                        initial = {{ opacity: 0, y: 20 }}
                        animate = {{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.4, ease: easeInOut }}
                        whileHover={{ scale: 1.1 }}
                        whileTap={{ scale: 0.5 }}
                        />
                    
                        {/*if auth, success message will show on screen*/}
                        {successMessage && <div className="bg-emerald-500 text-white rounded-2xl px-5 py-2 mt-2"> { successMessage } </div>}
                        
                        <div className="flex justify-center">
                            <Link to="/register" className=" text-blue-50  flex md:underline font-bold">Are you new? click here and create an account</Link>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}
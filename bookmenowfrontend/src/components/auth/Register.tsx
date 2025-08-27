import { useForm, type SubmitHandler } from "react-hook-form";
import form  from "./../../img/form.png";
import type { user } from "./../../types/user"
import { easeInOut, motion } from "motion/react";
import RegisterService from "../../services/RegisterService";
import { useState } from "react";




export default function Register() {

    const [successMessage, setSuccessMessage] = useState<string | null>();

    //setting the form
    const{
        register,
        handleSubmit,
        formState: { errors },
        setError
    } = useForm<user>();

    //connecting onsubmit with axios and making the bridge with backend
    const onSubmit: SubmitHandler<user> = async (data) => {
        try{
            const response = await RegisterService.postUser(data)
            console.log("user successfully created");

            const userCreated = response.userCreated;

            localStorage.setItem("usercreated", userCreated);

            setSuccessMessage("User successfully created, redirecting...");

            setTimeout(() => {window.location.href = "/auth"}, 2000);

        } catch (error: any){

            if(error.response?.status === 409 || error.response.status === "2003"){
                setError ("email", {
                    type: "manual",
                    message: "Email already in use, try with another one"
                });
                return;
            }

        }
    }

    return (
        <>
        <div className="bg-cover relative">
            <img src={form} alt="register-img" className="h-screen w-screen object-cover brightness-70 blur-xs"/>
            <div className="flex justify-center">

            <form onSubmit={handleSubmit(onSubmit)} 
            className="absolute translate-y-15 top-[30px] flex justify-center flex-col p-15 gap-5 rounded-2xl backdrop-blur-sm bg-white/30"
            >

                <h2 className="text-2xl text-white text-center">Sign-up</h2>

                <div className="flex flex-col justify-self-start">
                    <label className="text-white font-bold">Name</label>
                    <motion.input 
                    className="text-center border border-black rounded-2xl text-white font-bold" 
                    transition={{ duration: 0.4, ease: easeInOut }}
                    whileHover={{ scale: 1.1 }}
                    whileTap={{scale: 0.5 }}
                    type="text"
                    {...register("name", {
                        required:true,
                        pattern:/^[A-Za-z\s]+$/i
                    })}
                    placeholder="Your first name"
                    />
                    {errors.name?.type === "required" && 
                    <motion.p 
                    initial = {{ opacity: 0, y: 20 }}
                    animate = {{ opacity: 1, y: 0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Name is a must</motion.p>}
                    {errors.name?.type === "pattern" && 
                    <motion.p 
                    initial = {{ opacity:0, y:20 }}
                    animate = {{ opacity:1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Only letters are allowed</motion.p>}
                </div>
                
                <div className="flex flex-col justify-self-start">
                    <label className="text-white font-bold">Last Name</label>
                    <motion.input 
                    className="text-center border border-black rounded-2xl text-white font-bold" 
                    transition={{ duration: 0.4, ease: easeInOut }}
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale: 0.5 }}
                    type="text"
                    placeholder="Your last name"
                    {...register("lastName", {
                        required: true,
                        pattern: /^[A-Za-z\s]+$/i
                    })}
                    />
                    {errors.lastName?.type === "required" && 
                    <motion.p 
                    initial = {{ opacity: 0, y: 20 }}
                    animate = {{ opacity: 1, y: 0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Last name is a must</motion.p>}
                    {errors.lastName?.type === "pattern" && 
                    <motion.p 
                    initial = {{ opacity:0, y: 20 }}
                    animate = {{ opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Only letters are allowed</motion.p>}
                </div>

                <div className="flex flex-col justify-self-start">
                    <label className="text-white font-bold">Email</label>
                    <motion.input 
                    className="text-center border border-black rounded-2xl text-white font-bold" 
                    transition={{ duration: 0.4, ease: easeInOut }}
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale:0.5 }}
                    type="text"
                    placeholder="email@example.com"
                    {...register("email", {
                        required: true,
                        pattern: /^\S+@\S+$/i
                    })}
                    />
                    {errors.email?.type === "required" && 
                    <motion.p  
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Email is required</motion.p >}
                    {errors.email?.type === "pattern" && 
                    <motion.p  
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Email not valid</motion.p >}
                    <motion.p 
                    initial = {{ opacity: 0, y: 20 }}
                    animate = {{ opacity: 1, y: 0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold"
                    >{errors.email?.message}</motion.p>
                </div>

                <div className="flex flex-col justify-self-start">
                    <label className=" text-white font-bold">Password</label>
                    <motion.input 
                    className="text-center border border-black rounded-2xl text-white font-bold" 
                    transition={{ duration: 0.4, ease: easeInOut }}
                    whileHover={{scale: 1.1}}
                    whileTap={{ scale:0.5 }}
                    type="password"
                    placeholder="pasword123456"
                    {...register("password", {
                        required: true,
                        pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
                    })}
                    />
                    {errors.password?.type === "required" && 
                    <motion.p 
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold"> Password is required</motion.p>}
                    {errors.password?.type === "pattern" && 
                    <motion.p 
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    >Must be at least 8 characters and contain letters and numbers</motion.p >}
                </div>

                <div className="flex flex-col justify-self-start">
                    <motion.select
                        id="role"
                        className="
                        w-64 px-4 py-2
                        border border-gray-300 rounded-2xl 
                        text-white font-semibold text-center 
                        shadow-md cursor-pointer 
                        bg-gray-800"
                        {...register("userRoles", {
                            required: true
                        })}
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        transition={{ duration: 0.4, ease: "easeOut" }}
                        whileHover={{ scale: 1.05 }}
                        whileTap={{ scale: 0.95 }}>

                        <option value="PROVIDER" className="text-black">PROVIDER</option>
                        <option value="CLIENT" className="text-black">CLIENT</option>
                    </motion.select>
                </div>

                <div className="flex flex-col justify-self-start">
                    <label className="text-white font-bold">Phone</label>
                    <motion.input 
                    className="text-center border border-black rounded-2xl text-white font-bold" 
                    transition={{ duration: 0.4, ease: easeInOut }}
                    whileHover={{ scale: 1.1 }}
                    whileTap={{ scale: 0.5 }}
                    type="text"
                    placeholder="+123456789"
                    {...register("phone", {
                        required: true,
                        pattern: /^\d{10}$/
                    })}
                    />
                    {errors.phone?.type === "required" && 
                    <motion.p 
                    initial = {{ opacity: 0, y: 20 }}
                    animate= {{ opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Phone number is required</motion.p >}
                    {errors.phone?.type === "pattern" && 
                    <motion.p  
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Invalid phone number format</motion.p >}
                </div>

                <div className="flex flex-col justify-self-start">
                    <label className="text-white font-bold">Address</label>
                    <motion.input 
                    className="text-center border border-black rounded-2xl text-white font-bold" 
                    transition={{ duration: 0.4, ease: easeInOut }}
                    whileTap={{ scale: 0.5 }}
                    whileHover={{ scale: 1.1 }}
                    type="text"
                    placeholder="Address123456"
                    {...register("address", {
                        required: true,
                        pattern: /^[A-Za-z0-9\s\-\.,#]+$/
                    })}
                    />
                    {errors.address?.type === "required" && 
                    <motion.p 
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Address is required</motion.p>}

                    {errors.address?.type === "pattern" && 
                    <motion.p 
                    initial = {{opacity: 0, y: 20 }}
                    animate= {{opacity: 1, y:0 }}
                    className="bg-red-600 rounded-2xl px-20 text-white font-bold">Invalid address format</motion.p>}
                </div>

                <motion.input 
                type="submit" 
                value="Sign Up"
                className="mt-4 px-6 py-3 bg-blue-600 hover:bg-blue-700 text-white font-bold rounded-2xl shadow-lg cursor-pointer"
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.4, ease: "easeOut" }}
                whileHover={{ scale: 1.1 }}
                whileTap={{ scale: 0.5 }}
                />

                {/*if account successfully created*/}
                {successMessage && 
                <motion.div 
                initial = {{ opacity: 0, y: 20 }}
                animate = {{ opacity: 1, y: 0 }}
                className="bg-emerald-500 text-white rounded-2xl">
                { successMessage }</motion.div>}

            </form>
            </div>
        </div>

        </>
    )
}


import { useState } from "react"
import { FaPlus } from "react-icons/fa";
import { FaBusinessTime } from "react-icons/fa";
import { FaPersonCirclePlus } from "react-icons/fa6";


{/*
    * in this component I have to add the quantity of clients, providers, and set a comparasion when the company started to be compared with the current year    
*/}

export default function Body () {

    const currentYear = new Date().getFullYear();
    const companyStartYear = 2024;
    const yearsOperating = currentYear - companyStartYear;

    const [providers, setProviders] = useState();
    const [clients, setClients] = useState();


    return (
        <>
            <h1 className="text-center text-5xl font-bold py-10">Why chose Bookmenow?</h1>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-10 lg:px-[100px]">
                    <div className="text-center flex-col justify-items-center hover:text-3xl duration-300 ease-in-out shadow-2xl rounded-2xl p-10">
                        <FaBusinessTime className="text-emerald-500"/>
                        <h1 className="font-bold py-5 text-2xl hover:text-3xl duration-300 ease-in-out">More than <span className="text-emerald-500">{providers}</span> providers over world</h1>
                        <p className="text-gray-600 text-sm">A growing network of trusted professionals ready to help you wherever you are.</p>
                    </div>
                    <div className="text-center flex-col justify-items-center hover:text-3xl duration-300 ease-in-out shadow-2xl rounded-2xl p-10">
                        <FaPlus className="text-emerald-500"/>
                        <h1 className="font-bold py-5 text-2xl hover:text-3xl duration-300 ease-in-out">More than <span className="text-emerald-500">{yearsOperating}</span>  year operating</h1>
                        <p className="text-gray-600 text-sm">Proven track record of connecting people with the right services — every time.</p>
                    </div>
                    <div className="text-center flex-col justify-items-center hover:text-3xl duration-300 ease-in-out shadow-2xl rounded-2xl p-10">
                        <FaPersonCirclePlus className="text-emerald-500"/>
                        <h1 className="font-bold py-5 text-2xl hover:text-3xl duration-300 ease-in-out">More than <span className="text-emerald-500">{clients}</span> clients who trust us over world</h1>
                        <p className="text-gray-600 text-sm">Join a global community that chooses BookMeNow for convenience and trust.</p>
                    </div>
                </div>
        </>
    )

}
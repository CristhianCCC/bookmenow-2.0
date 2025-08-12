import { FaQuestionCircle } from "react-icons/fa";


export default function Guide () {
    return (
        <>
        
            <h1 className="text-3xl text-emerald-500 py-5 font-bold text-center hover:text-4xl duration-300 ease-in-out ">How to use BookmeNow?</h1>
            <div className="grid lg:grid-cols-2 justify-center xl:text-left divide-x xl:divide-gray-300 gap-10">
                <div className="text-center justify-center">
                    <FaQuestionCircle className="text-9xl mx-30 md:mx-60 xl:mx-150"/>
                </div>
                <div className="grid grid-cols-1 justify-center gap-5 text-center lg:text-left">
                    <div>
                        <h1 className="text-2xl font-bold"><span className="text-emerald-500">1.</span> Register</h1>
                        <p className="text-gray-700">Create an account using email and password, chose if you're going to be a provider or client</p>
                    </div>
                    <div>
                        <h1 className="text-2xl font-bold"><span className="text-emerald-500">2. </span>Depending on your role</h1>
                        <p className="text-gray-700">you can search through our providers and book a service with a provider</p>
                    </div>
                    <div>
                        <h1 className="text-2xl font-bold"><span className="text-emerald-500">3. </span>Review and make a payment for the services</h1>
                        <p className="text-gray-700">You can pay via bank transfer/card</p>
                    </div>
                </div>
            </div>

        </>
    )
}
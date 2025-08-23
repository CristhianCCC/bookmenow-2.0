import { motion } from "motion/react";
import type { Variants } from "motion/react";

type ServiceCardProps = {
    serviceImg: string;
    service: string;
    serviceDescription: string;
}



export default function ServicesCard ({serviceImg, service, serviceDescription}: ServiceCardProps) {

    const cardVariants: Variants = {
        offscreen: {
            y:100,
            opacity:0,
        },
        onscreen: {
            y:0,
            opacity: 1,
            transition:{
                type: "spring",
                bounce: 0,
                duration: 1,
            },
        },
    }

    return (
        <>
        <motion.div
        initial="offscreen" //initial status will be off
        whileInView="onscreen" //while on view is going to be when the animation will change
        viewport={{ amount: 0 }} //controls whether is visible to be animated, on this case 80%
        variants={ cardVariants } //will be a variable outside the return, it must be defined within the function and will define the animated states
        >
                <div className="max-w-sm bg-white border border-gray-200 rounded-lg shadow-lg h-120 ">
                <a href="#">
                    <img className="rounded-t-lg" src={serviceImg} alt="service image" />
                </a>
                    <div className="p-5">
                        <a href="#">
                            <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{service}</h5>
                        </a>
                        <p className="mb-3 font-normal text-gray-700 dark:text-gray-400">{serviceDescription}</p>
                        <a href="#" className="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-gradient-to-b from-emerald-300 to bg-emerald-600 rounded-lg hover:bg-emerald-800 focus:ring-4 focus:outline-none hover:cursor-pointer">
                        get this service
                            <svg className="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                            </svg>
                        </a>
                    </div>
                </div>
            </motion.div>
        </>
    )
}
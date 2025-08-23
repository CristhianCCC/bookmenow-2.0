import { motion, useMotionValue, useScroll, useSpring, useTime, useTransform, useVelocity, wrap } from "motion/react";
import { GiSpikedCollar } from "react-icons/gi";





export default function FooterText () {

    //function for spining GiSpikedCollar --------------------------------------------------------------------------------------------------------------------------------------------------
        const time = useTime() //using usetime to define the time in milliseconds
        const rotate = useTransform( //creating the function with the properties to make the image spin 360 grades during 4 seconds
            time,
            [0, 4000], //time in milliseconds
            [0, 360], //rotation in degrees
            { clamp: false }
        )


    //function for x movement while scrolling ----------------------------------------------------------------------------------------------------------------------------------------------
    
    const baseX = useMotionValue(0);
    const {scrollY} = useScroll();
    const scrollVelocity = useVelocity (scrollY);
    const smoothVelocity = useSpring(scrollVelocity, {
        damping: 50,
        stiffness: 400
    });
    const velocityFactor = useTransform(smoothVelocity, [30, 1000], [0, 5], {
        clamp:false
    })

    const x = useTransform(baseX, (v) => `${wrap(-20, -45, v)}%`);


    return (
        <>
                <motion.div className="flex flex-col md:flex-row md:justify-around py-10 text-2xl md:text-5xl xl:text-6xl text-center items-center gap-5 md:gap-6" style={{ x: velocityFactor  }}>
                    <h2>Innovate</h2>
                    <motion.div style={ {rotate} }> {/*using rotate which is a class previously created on the function to make the item spin on the screen*/}
                        <GiSpikedCollar className="text-pink-500"/>
                    </motion.div>
                    <h2>Inspire</h2>
                    <motion.div style={ {rotate} }>
                        <GiSpikedCollar className="text-pink-500"/>
                    </motion.div>
                    <h2>Create</h2>
                    <motion.div style={ {rotate} }>
                        <GiSpikedCollar className="text-pink-500"/>
                    </motion.div>
                    <h2>Want To Join us?</h2>
                </motion.div>
        </>
    )
}
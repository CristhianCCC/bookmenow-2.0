import { motion, useScroll } from "motion/react";
import About from "./About";
import Body from "./Body";
import FooterText from "./FooterText";
import Guide from "./Guide";
import Hero from "./Hero";
import Services from "./Services";


export default function Landing () {

    const {scrollYProgress} = useScroll();

    return (
        <>  
            <motion.div id="scroll-indicator" style={{ scaleX: scrollYProgress, originX: 0 }} className="fixed top-0 left-0 right-0 h-2 bg-pink-500"/>
            <Hero />
            <Body />
            <About />
            <Services />
            <Guide />
            <FooterText />
        </>
    )

}
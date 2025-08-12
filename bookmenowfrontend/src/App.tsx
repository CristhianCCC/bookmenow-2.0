import About from "./components/landing/About"
import Body from "./components/landing/Body"
import Hero from "./components/landing/Hero"
import Navbar from "./components/landing/Navbar"
import Services from "./components/landing/services"
import "./index.css"

export default function App() {

  return (
    <>
        <Navbar/>
        <Hero/>
        <Body/>
        <About/>
        <Services/>
    </>
  )
}


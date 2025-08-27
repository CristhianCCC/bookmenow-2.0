import Navbar from "./components/landing/Navbar"
import RoutingPaths from "./routes/RoutingPaths"
import "./index.css"



export default function App() {


  

  return (
    <>  
      <Navbar/>
      {/*calling RoutingPaths that will handle all routes available*/}
      <RoutingPaths/> 
    </>
  )
}


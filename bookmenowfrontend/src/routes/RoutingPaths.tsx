import { Route, Routes } from "react-router-dom";
import Auth from "../components/auth/Auth";
import Landing from "../components/landing/Landing";
import Register from "../components/auth/Register";


export default function RoutingPaths () {
    return (
        <>
            <Routes>
                <Route path="/" element={<Landing/>}></Route>
                <Route path="/auth" element={<Auth/>}></Route>
                <Route path="/register" element={<Register/>}></Route>
            </Routes>
        </>
    )
}
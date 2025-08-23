import { Route, Routes } from "react-router-dom";
import Auth from "../auth/Auth";
import Landing from "../landing/Landing";


export default function RoutingPaths () {
    return (
        <>
            <Routes>
                <Route path="/" element={<Landing/>}></Route>
                <Route path="/auth" element={<Auth/>}></Route>
            </Routes>
        </>
    )
}
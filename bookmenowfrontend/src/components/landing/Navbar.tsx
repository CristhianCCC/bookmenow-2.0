import { useState } from "react";
import { Link } from "react-router-dom";

export default function Navbar () {

    const img = "src/img/bookmenowlogo.png";

    const[menuOpen, setMenuOpen] = useState(false);

    return (
        <>
            <nav>
                <button className="md:hidden text-gray-800" onClick={() => setMenuOpen(!menuOpen)}>
                    <svg className="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            {menuOpen ? (
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12"/>
                            ) : (
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
                            )}
                        </svg>
                </button>
                <ul className={`rounded-2xl flex lg:justify-evenly justify-between items-center bg-gray-900 shadow-2xl text-gray-200 overflow-hidden duration-1000 ease-out ${menuOpen ? 'flex-col-reverse max-h-96 opacity-100' : 'max-h-0 opacity-0'} md:max-h-none md:opacity-100`}>
                    <Link to="/" className="hover:bg-emerald-400 rounded-2xl px-5 hover:text-xl duration-300 ease-in ">Home</Link>
                    <Link to="/services" className="hover:bg-emerald-400 rounded-2xl px-5 hover:text-xl duration-300 ease-in">Services</Link>
                    <Link to="/"><img src={img} className="w-20" alt="Logo" /></Link>
                    <Link to="/projects" className="hover:bg-emerald-400 rounded-2xl px-5 hover:text-xl duration-300 ease-in">Providers</Link>
                    <Link to="/auth" className="hover:bg-emerald-400 rounded-2xl px-5 hover:text-xl duration-300 ease-in">Sign-in</Link>
                </ul>
            </nav>
                
                
        </>
    )
}
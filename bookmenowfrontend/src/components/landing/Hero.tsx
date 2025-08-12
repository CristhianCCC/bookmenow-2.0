

{/*
    * Pending to add functionality to search by services
*/}

export default function Hero () {

        const header = "src/img/header.jpg";

    return (
        <>
        <div className="relative flex justify-center">
            <img className="rounded-2xl m-4 h-auto w-full lg:max-w-[1500px] lg:m-[15px] header shadow-2xl" src={header} alt="img header"/>
                <h1 className="absolute top-20 max-w-[90%] lg:max-w-[40%] px-8 text-white text-3xl md:text-6xl lg:text-8xl font-bold transform lg:-translate-y-1 md:translate-x-5 md:text-center lg:text-left lg:-translate-x-90">Empowering clients and freelancers to build great projects.</h1>
                <input className="absolute bg-white md:top-100 top-50 lg:top-180 lg:max-w-[40%] lg:-translate-y-1 lg:-translate-x-130 lg:px-20 lg:py-3 text-center" placeholder="Find services" type="text"></input>
        </div>
        </>
    )
}
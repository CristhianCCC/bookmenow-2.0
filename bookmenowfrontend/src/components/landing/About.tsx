import videoFile from '../../img/section-2.mp4';


export default function About () {

    const imagesection1 = "src/img/section-1.avif"

    return (
        <>
            <div className="grid md:grid-cols-2 pt-20 gap-10 justify-center lg:divide-x divide-gray-300">
                <div className='flex flex-col justify-center md:text-center'>
                    <h1 className="font-bold text-3xl text-center">Turning Ideas and Businesses Into Reality</h1>
                    <p className="text-center py-5 xl:px-50 text-gray-600">We help entrepreneurs and companies transform concepts into thriving projects, connecting them with the right people and resources to succeed in today's competitive market.</p>
                    <div className='flex justify-center '>
                        <img src={imagesection1} alt="Img section" className="rounded-2xl w-full max-w-md"/>
                    </div>
                </div>
                <div>
                    <h1 className="font-bold text-3xl text-center">Bringing Visions to Life</h1>
                    <p className="text-center xl:px-50 text-gray-600 py-5"> We turn innovative ideas into reality, using creativity and expertise to deliver results that inspire and drive success. Watch as concepts evolve into impactful solutions.</p>
                    <div className='flex justify-center'>
                        <video autoPlay muted loop playsInline className="rounded-2xl w-full max-w-md xl:mx-20">
                            <source src={videoFile} type="video/mp4" /> 
                        </video>
                    </div>
                </div>
            </div>
        </>
    )
}
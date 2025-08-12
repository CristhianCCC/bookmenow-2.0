import ServicesCard from "./ServicesCard";
import serviceImg1 from "../../img/services.jpg"
import serviceImg2 from "../../img/services2.jpg"
import serviceImg3 from "../../img/services3.jpg"
import serviceImg4 from "../../img/services4.jpg"
import serviceImg5 from "../../img/services5.jpg"
import serviceImg6 from "../../img/services6.jpg"


export default function Services () {
    return (
        <>
            <div className="my-10 text-center py-25 bg-gradient-to-r from-black via-gray-800 to-black rounded-2xl">
                <h1 className="text-emerald-500 font-bold text-2xl">Most Picked <span className="bg-black rounded-2xl p-1 text-white font-bold">Services</span></h1>
                <p className="lg:px-40 text-white font-bold">We provide creative, modern solutions tailored to your needs, offering a wide range of services to help you find the perfect fit for your business.</p>
                <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-10 p-15 justify-center">
                    <ServicesCard
                        serviceImg={serviceImg1}
                        service="Software Development"
                        serviceDescription="Custom software solutions tailored to your business goals, from web platforms to scalable enterprise applications."
                    />
                    <ServicesCard
                        serviceImg={serviceImg2}
                        service="Mobile App Development"
                        serviceDescription="High-performance mobile apps for iOS and Android, built with modern frameworks for a smooth user experience."
                    />
                    <ServicesCard
                        serviceImg={serviceImg3}
                        service="UI/UX Design"
                        serviceDescription="Intuitive, visually stunning interfaces that enhance usability and create memorable digital experiences."
                    />
                    <ServicesCard
                        serviceImg={serviceImg4}
                        service="Cloud Solutions"
                        serviceDescription="Seamless migration and deployment of cloud infrastructure to ensure flexibility, scalability, and security."
                    />
                    <ServicesCard
                        serviceImg={serviceImg5}
                        service="Gourmet Catering"
                        serviceDescription="Delight your guests with personalized menus, fresh ingredients, and exceptional culinary experiences for any event."
                    />
                    <ServicesCard
                        serviceImg={serviceImg6}
                        service="Luxury Travel Planning"
                        serviceDescription="Tailor-made travel itineraries, exclusive accommodations, and unique experiences around the world."
                    />
                </div>        
            </div>
        </>
    )
}
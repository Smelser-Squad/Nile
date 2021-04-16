import React from 'react'
import './Home.css'
import HomeProduct from './HomeProduct';


function Home() {
    return (
        <div className='home' >
            <div className="home_container">
                <img
                    className="home_image"
                    alt="Find the perfect gift for Mother's Day"
                    src="https://images-na.ssl-images-amazon.com/images/G/01/US-hq/2021/img/Events/MothersDay/Homepage/T3_2021_MothersDay_GW_HomepageHero_Desktop_Gifting_2x_3000x1200._CB658614325_.jpg">
                </img>


                {/* <img alt="Certified Refurbished; Save on like-new Amazon devices." src="https://images-na.ssl-images-amazon.com/images/G/01/kindle/journeys/YWIyMWNkMjQt/YWIyMWNkMjQt-Nzc5NDk1N2Qt-w3000._CB660698761_.jpg" height="600px" width="1500px" ></img> */}


                {/* <br>
                </br> */}
                <div className="home_row">
                    <HomeProduct
                        productId="12321341"
                        name="Gift for Mom"
                        price={80.99}
                        // rating={4}
                        image="https://i.etsystatic.com/5584756/r/il/7d61bd/2957120029/il_1588xN.2957120029_kefx.jpg" />


                    <HomeProduct
                        productId="12321341"
                        name="Wood Tabletop"
                        price={10.99}
                        // rating={4}
                        image="https://target.scene7.com/is/image/Target/GUEST_f3404e1d-acae-49cb-a7f0-6ce4f2b82793?wid=488&hei=488&fmt=pjpeg" />                </div>

                <div className="home_row">
                    <HomeProduct />
                    <HomeProduct />
                    <HomeProduct />

                </div>

                <div className="home_row">
                    <HomeProduct />
                </div>
            </div >
        </div>
    )
}

export default Home;

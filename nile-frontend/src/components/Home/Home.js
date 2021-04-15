import React from 'react'
import './Home.css'
import { Link } from "react-router-dom";

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


                <br>
                </br>
                
                <Link to="/singleProductListing">
                <img id="myImg" src="https://media.reformclothing.com/design_lab/ysd-products/C1005_T-Shirt/C1005_T_Shirt_Front_A_800.png"
                    alt="product">
                </img>
                </Link>

            </div >
        </div>
    )
}

export default Home;

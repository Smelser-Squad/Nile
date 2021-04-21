import React, { useState } from 'react'
import { getProducts } from '../../service/ProductService'

import './Home.css'
import HomeProduct from './HomeProduct';

function Home() {

    const [cards, setCards] = useState([]);

    const ProductList = [];

    if (cards.length === 0) {
        getProducts().then((list) => {
            list.map((item) =>
                ProductList.push(item)
            );
            const cards=ProductList.map((product)=>
            
                <HomeProduct
                        productId={product.productId}
                        name={product.name}
                        price={product.price}
                        rating={calcRating(product)}
                        image={product.photos[0].imageSrc}
                        primeEligible={product.primeEligible}
                        />
                        
                    
                       

            );
            setCards(cards);
        }
        );
    }
    function calcRating(product)  {
        let sum = 0;
        for(let i = 0; i < product.reviews.length; i++) {
            sum += product.reviews[i].rating;
        }
        const avgRating = sum / product.reviews.length;
        return avgRating;
    }
    function ShowFirst(product){
        let photo;
        for(let i=0; i<product.photos.length;i++){
            photo=product.photos[0].imgSrc;
            console.log(photo);
        }
        return photo;
    }

    return (
        <div className='home' >
            <div className="home_container">
                <img
                    className="home_image"
                    alt="Find the perfect gift for Mother's Day"
                    src="https://images-na.ssl-images-amazon.com/images/G/01/US-hq/2021/img/Events/MothersDay/Homepage/T3_2021_MothersDay_GW_HomepageHero_Desktop_Gifting_2x_3000x1200._CB658614325_.jpg">
                </img>


                {/* <img alt="Certified Refurbished; Save on like-new Amazon devices." src="https://images-na.ssl-images-amazon.com/images/G/01/kindle/journeys/YWIyMWNkMjQt/YWIyMWNkMjQt-Nzc5NDk1N2Qt-w3000._CB660698761_.jpg" height="600px" width="1500px" ></img> */}
                <div className="home_row">
                    {cards.slice(0, 2)}
                </div>

                <div className="home_row">
                    {cards.slice(3, 6)}
                </div>

                <div className="home_row">
                    {cards.slice(7, 10)}
                </div>

                <div className="home_row">
                    {cards.slice(11, 500)}
                </div>
            </div >
        </div>
    )
}

export default Home;
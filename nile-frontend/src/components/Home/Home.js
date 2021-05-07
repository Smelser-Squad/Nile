import React, { useState, useEffect } from 'react';
import { getProducts } from '../../service/ProductService';
import './Home.css';
import HomeProduct from './HomeProduct';


function Home() {

    const [cards, setCards] = useState([]);

    useEffect(() => {
        const ProductList = [];
        if (cards.length === 0) {
            getProducts().then((list) => {
                list.map((item) =>
                    ProductList.push(item)
                );
                const cards = ProductList.map((product) =>

                    <HomeProduct
                        key={product.productId}
                        productId={product.productId}
                        name={product.name}
                        price={product.price}
                        rating={calcRating(product)}
                        image={product.photos[0].imageSrc}
                        description={product.description}
                        quantity={1}

                    />
                );
                setCards(cards);
            }
            );
        }
    }, []);
    function calcRating(product) {
        let sum = 0;
        for (let i = 0; i < product.reviews.length; i++) {
            sum += product.reviews[i].rating;
        }
        const avgRating = sum / product.reviews.length;
        return avgRating;
    }


    return (
        <div className='home' >
            <div className="home_container">

                <div class="home_image_div">
                    <img
                        className="home_image"
                        alt=""
                        src="https://africa-facts.org/wp-content/uploads/2014/12/Giraffe-at-Nile-River.jpeg"
                    >
                    </img>

                    <h1 className="image_text">Find Your Treasure on the Nile</h1>
                </div>
                <div className="home_row">
                    {cards.slice(0, 2)}
                </div>

                <div className="home_row">
                    {cards.slice(2, 5)}
                </div>

                <div className="home_row">
                    {cards.slice(5, 7)}
                </div>

                <div className="home_row">
                    {cards.slice(7, 15)}
                </div>
            </div >
        </div>
    )
}

export default Home;
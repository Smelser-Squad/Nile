import React, { useState, useEffect } from 'react';
import './Home.css';
import HomeProduct from './HomeProduct';
import { useStateValue } from "../../StateProvider";

function Home() {
    const [cards, setCards] = useState([]);
    const [{ filteredProducts }, dispatch] = useStateValue();

    useEffect(() => {
        const ProductList = [];
        filteredProducts.map((item) =>
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
        , [filteredProducts]);
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
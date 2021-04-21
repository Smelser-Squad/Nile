import './SingleProductListing.css';
import axios from 'axios'

import React, { useEffect, useState } from 'react'
import MoreProducts from '../MoreProducts/MoreProducts';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';
import QuestionAnswer from '../QA/QA.jsx';
import Reviews from '../Reviews/Reviews.js';
import { ProductColorSelector } from '../ProductColorSelector/ProductColorSelector';
import ReviewSummary from '../ReviewSummary/ReviewSummary';
import { useParams } from 'react-router-dom'
import { useStateValue } from "../../StateProvider";


function SingleProductListing() {

    const { productId } = useParams()

    const [Product, setProduct] = useState([]);
    console.log(Product);
    const [{ cart }, dispatch] = useStateValue();
    const addToCart = () => {
        // dispatch the item into the data layer
        dispatch({
            type: "ADD_TO_CART",
            product: {
                productId: Product.productId,
                name: Product.name,
                // image: Product.image,
                price: Product.price,
                // rating: Product.rating
            },
        });
    };


    useEffect(() => {
        axios.get(`http://localhost:80/api/products/${productId}`)
            .then(res => {
                setProduct(res.data);

            })
    }, [])

    return (
        <div className="SingleProductListing">
            <h2>{Product.name}</h2>
            <h3>{Product.description}</h3>
            <ProductPhotos />
            {/* <ProductColorSelector /> */}
            <div className="add_toCart">
                <button onClick={addToCart}>Add to Cart</button>
            </div>

            <br />
            <br />

            {/* <MoreProducts />
            <QuestionAnswer />
            <ReviewSummary />
            <Reviews /> */}


        </div>
    )
}

export default SingleProductListing
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

function SingleProductListing({ productid, image, name, price, rating }) {
    const { productId } = useParams()
    const [Product, setProduct] = useState([]);
    const [{ cart }, dispatch] = useStateValue();
    const addToCart = () => {
        // dispatch the item into the data layer
        dispatch({
            type: "ADD_TO_CART",
            product: {
                productId: productid,
                name: name,
                image: image,
                price: price,
                rating: rating
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
            <ProductColorSelector />
            <div className="add_toCart">
                <button onClick={addToCart}>Add to Cart</button>
            </div>
            <br />
            <br />
            <MoreProducts />
            <QuestionAnswer />
            <ReviewSummary />
            <Reviews />

        </div>
    )
}
export default SingleProductListing
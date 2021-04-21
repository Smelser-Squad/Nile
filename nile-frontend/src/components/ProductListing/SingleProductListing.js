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
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
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
                image: Product.photos[0].imageSrc,
                price: Product.price,
                reviewCount: Product.reviews.length,
                rating: calcRating(Product)
            },
        });
    };

    function calcRating(product) {
        let sum = 0;
        for (let i = 0; i < product.reviews.length; i++) {
            sum += product.reviews[i].rating;
        }
        const avgRating = sum / product.reviews.length;
        return avgRating;
    }


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
                <RadioGroup className="button_purchase">
                    <FormControlLabel control={<Radio />} label="One-time purchase:" />
                </RadioGroup>
                <p id="price_tag">
                    <small>$</small>
                    <strong>{Product.price}.00</strong>

                </p>
                <button onClick={addToCart}>Add to Cart</button>
            </div>

            <br />
            <br />

            <MoreProducts />
            {/* <QuestionAnswer /> */}
            <ReviewSummary />
            <Reviews />


        </div>
    )
}

export default SingleProductListing
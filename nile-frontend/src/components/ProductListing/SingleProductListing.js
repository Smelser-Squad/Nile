import './SingleProductListing.css';
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import MoreProducts from '../MoreProducts/MoreProducts';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';
import QuestionAnswer from '../QA/QA.jsx';
import Reviews from '../Reviews/Reviews.js';
import { ProductColorSelector } from '../ProductColorSelector/ProductColorSelector';
import ReviewSummary from '../ReviewSummary/ReviewSummary';
import Comparison from '../Comparison/Comparison';
import { useParams } from 'react-router-dom'
import { useStateValue } from "../../StateProvider";
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import LockIcon from '@material-ui/icons/Lock';
import { Link } from 'react-router-dom'

function SingleProductListing() {

    const { productId } = useParams()
    const [Product, setProduct] = useState([]);
    const [{ cart }, dispatch] = useStateValue();
    const [color, setProductColor] = useState('white');

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

            <Link to={`/products/brand/${Product.brand}`}> Brand: {Product.brand}</Link>


            <ProductPhotos />
            {/* <ProductColorSelector setProductColor={setProductColor}/> */}
            <div className="add_toCart">
                <RadioGroup className="button_purchase">
                    <FormControlLabel control={<Radio />} label="One-time purchase:" />
                </RadioGroup>
                <p id="price_tag">
                    <small>$</small>
                    <strong>{Product.price}</strong>

                </p>
                <button onClick={addToCart} className="shop_button" >Add to Cart</button>
                <Link to='/payment' onClick={addToCart}><button className="shop_button ">Shop Now</button></Link>
                <p className="secure"> <LockIcon className="lock_icon" />Secure transaction</p>

                <p className="ship">
                    <small>Ships From </small>
                    <strong>Nile</strong>
                </p>
                {/* <p className="ship">
                    <small>Sold By </small>
                    <strong>{Product.vendor.vendor.name} </strong>
                </p> */}
                <small className="prime">
                    <input type="checkbox" />Yes, I want FREE delivery, as fast as today with Prime
            </small>
                <small className="pro_gift">
                    <input type="checkbox" />Add a gift receipt for easy returns
            </small>
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

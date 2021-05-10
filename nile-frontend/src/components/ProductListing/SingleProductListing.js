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

function SingleProductListing(props) {
    const { productId } = useParams()
    const [Product, setProduct] = useState([]);
    const [{ cart }, dispatch] = useStateValue();
    const [defaultColor, setDefaultColor] = useState('')
    const [color, setProductColor] = useState('Black');

    const ColoredLine = ({ color }) => (
        <hr
            style={{
                color: color,
                backgroundColor: color,
                height: 2,
                width: 300
            }}
        />
    );

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
                rating: calcRating(Product),
                quantity: 1
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
        axios.get(`http://54.82.100.75/api/products/${productId}`)
            .then(res => {
                setProduct(res.data);

            })
    }, [])

    console.log(Product)

    const numQuestions = 0;
    const numReviews = 0;


    return (

        <div className="SingleProductListing">

            <div className="box">
                <div className="left_div">
                    <ProductPhotos color={color} />
                    {/* <ProductColorSelector setProductColor={setProductColor} setDefaultColor={setDefaultColor} /> */}
                </div>


                <div className="middle_div">
                    <div className="right_item_description">
                        <p className="right_name">Product.name</p>
                        {/* <Link to={`/products/brand/${Product.brand}`}><p className="blue_text"> Brand: {Product.brand}</p></Link> */}
                        <p className="blue_text"> {numReviews} Reviews | {numQuestions} Questions Answered!</p>
                        <ColoredLine color="black" />
                        <p className="item_price">List Price: $Product.price</p>
                        <p className="green_text">In Stock.</p>
                        <p>Arrives: <h3>Wednesday, May 5 2021</h3></p>
                        <p>Fastest Delivery: <h3>Tuesday, May 4 2021</h3></p>
                        <p className="opaque_text"> Order Within: 11hr 15min 30sec</p>
                        <p >Offer Type: <b>With Special Offers</b></p>
                        <button ><b>With Special Offers</b></button> | <button><b>Without Special Offers</b></button>
                        <ColoredLine color="black" />
                        <ProductColorSelector setProductColor={setProductColor} setDefaultColor={setDefaultColor} />
                        {/* <p className="opaque_text">Style: <h3>{Product.type.typeName}</h3></p> */}
                        <ul>
                            <li>Product.category.name</li>
                            <li>Product.type.typeName</li>
                            <li>Product.description</li>
                        </ul>

                    </div>
                </div>

                <div className="right_div">
                    <RadioGroup className="button_purchase">
                        <FormControlLabel control={<Radio />} label="One-time purchase:" />
                    </RadioGroup>
                    <p id="price_tag">
                        <small>$</small>
                        <strong>{Product.price}</strong>
                    </p>
                    <br />

                    <p className="ship">
                        <small>Status </small>
                        <strong>

                            {Product.stock > 0 ? (
                                <span className="success">In Stock</span>
                            ) : (
                                <span className="danger">Unavailable</span>
                            )}
                        </strong>
                    </p>

                    <button onClick={addToCart} className="shop_button" >Add to Cart</button>
                    <Link to='/payment' ><button className="shop_button ">Shop Now</button></Link>
                    <p className="secure"> <LockIcon className="lock_icon" />Secure transaction</p>

                    <p className="ship">
                        <small>Ships From </small>
                        <strong>Nile</strong>
                    </p>
                    <small className="prime">
                        <input type="checkbox" />Yes, I want FREE delivery, as fast as today with Prime
            </small>
                    <small className="pro_gift">
                        <input type="checkbox" />Add a gift receipt for easy returns
            </small>
                </div>
            </div>
            <br />
            <br />

            <div className="comparison_div">
                <Comparison
                    product={Product}
                />
            </div>

            <br />
            <MoreProducts />
            <QuestionAnswer productId={productId} />
            <ReviewSummary />
            <Reviews />
            <Link
                to={`./all-product-reviews/${Product.productId}`} >
                <p class="all-reviews-link">See all reviews </p>
            </Link>
        </div >

    )
}

export default SingleProductListing;

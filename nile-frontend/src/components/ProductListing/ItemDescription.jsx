import React, { useState, useEffect } from 'react';
import './ItemDescription.css';
import { useParams } from 'react-router';
import { useStateValue } from "../../StateProvider";
import axios from 'axios';
import { Link } from 'react-router-dom'
import { ProductColorSelector } from '../ProductColorSelector/ProductColorSelector';

function ItemDescription(productId) {
    const [Product, setProduct] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:80/api/products/${productId}`)
            .then(res => {
                setProduct(res.data);
            })
    }, [])

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

    console.log(Product);

    return (
        <div className="right_item_description">
            <p className="right_name">{Product.name}</p>
            <Link to={`/products/brand/${Product.brand}`}><p className="blue_text"> Brand: {Product.brand}</p></Link>
            <p className="blue_text"> {Product.review.length} Reviews | {Product.questions.length} Questions Answered!</p>
            <ColoredLine color="black" />
            <p className="item_price">List Price: ${Product.price}</p>
            <p className="green_text">In Stock.</p>
            <p>Arrives: <h3>Wednesday, May 5 2021</h3></p>
            <p>Fastest Delivery: <h3>Tuesday, May 4 2021</h3></p>
            <p className="opaque_text"> Order Within: 11hr 15min 30sec</p>
            <p >Offer Type: <b>With Special Offers</b></p>
            <button ><b>With Special Offers</b></button> | <button><b>Without Special Offers</b></button>
            <ColoredLine color="black" />
            {/* <ProductColorSelector setProductColor={setProductColor} setDefaultColor={setDefaultColor} /> */}
            {/* <p className="opaque_text">Style: <h3>{Product.type.typeName}</h3></p> */}
            <ul>
                <li>{Product.category.name}</li>
                <li>{Product.type.typeName}</li>
                <li>{Product.description}</li>
            </ul>

        </div>
    );

}
export default ItemDescription;
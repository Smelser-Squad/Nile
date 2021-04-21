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


function SingleProductListing() {

const {productId} = useParams()

const[Product,setProduct]=useState([]);


    useEffect(()=>{
        axios.get(`http://localhost:80/api/products/${productId}` )
        .then(res=>{
                setProduct(res.data);
            
        })
    }, [])

    return(
        <div className="SingleProductListing">
            <h2>{Product.name}</h2> 
            <h3>{Product.description}</h3>
            <ProductPhotos/>
            <ProductColorSelector/>
            <MoreProducts/>
            <QuestionAnswer />
            <ReviewSummary />
            <Reviews/>


        </div>
    )
}

export default SingleProductListing
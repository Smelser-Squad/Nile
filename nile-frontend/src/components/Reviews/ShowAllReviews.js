import React, { useState } from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'
import {getProduct} from '../../service/ProductService';
import {getReviews} from '../../service/ReviewService';


function ShowAllReviews(){

    const[allReviews, setAllReviews] = useState();
    const { productId } = useParams();
    // const { productName } = useParams();

    getReviews(productId).then((name)=>{
        setAllReviews(allReviews);
    })

    console.log(productId);
    // console.log(productName);

    return(
        <div class="all-reviews-container">
            Reviews will be here
            {allReviews}

        </div>
    )

}

export default ShowAllReviews;
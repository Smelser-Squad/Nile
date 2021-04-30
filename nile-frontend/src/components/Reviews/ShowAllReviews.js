import React, { useState } from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'
import {getProduct} from '../../service/ProductService';
import {getReviews} from '../../service/ReviewService';
import SingleReview from './SingleReview';



function ShowAllReviews(){

    const[allReviews, setAllReviews] = useState([]);
    const { productId } = useParams();
    // const { productName } = useParams();

    const reviewsList =[];

    getReviews(productId).then((list)=>{
        list.map((summary)=>
        reviewsList.push(summary));
        console.log(reviewsList);
        const reviews = reviewsList.map((review)=>
        

        <SingleReview
        key={review.reviewId}
        helpful={review.helpful}
        rating={review.rating}
        reviewDate={review.reviewDate}
        title={review.title}
        summary={review.summary}/>
        );
        console.log(allReviews);
        setAllReviews(reviews);
    })

    return(
        <div class="all-reviews-container">
            
            {allReviews}

        </div>
    )

}

export default ShowAllReviews;
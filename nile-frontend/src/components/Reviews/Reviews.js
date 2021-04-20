import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from '../../service/ReviewService';
import { useState } from 'react';

function Reviews() {

    const[reviews,setReviews]=useState([]);

    const reviewList=[];

    if(reviews.length===0){
        getReviews().then((list)=>
        {
            list.map((item)=>
            reviewList.push(item)
            );
            const reviews=reviewList.map((review)=>
            <SingleReview
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}
            />
            );
            setReviews(reviews)
        }
        )
    }

    return (
        <div class="reviews-container">
            {reviews}
        </div>
    )
}

export default Reviews;
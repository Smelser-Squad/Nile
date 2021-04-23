import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from '../../service/ReviewService';
import { useState } from 'react';
import Tag from '../../components/ReviewTag/Tag';

function Reviews() {

    const[reviews,setReviews]=useState([]);

    const reviewList=[];

    const test = document.URL.substring(43)

    console.log(test)

    if(reviews.length===0){
        getReviews(test).then((list)=>
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
            <Tag reviews = {reviews}/>
        {reviews}






        </div>
    )
}

export default Reviews;
import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from '../../service/ReviewService';
import { useState } from 'react';
import Tag from '../../components/ReviewTag/Tag';

function Reviews() {

    const[reviews,setReviews]=useState([]);
    const[filterTag,setFilterTag]=useState([]);
    const[filteredReviews,setFilteredReviews]=useState([]);

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

    //Recieve data from Tag.js
    const sendDataToParent = (tag) =>
    {
        console.log("From the parent "+ tag);
        setFilterTag(tag);
    }

    console.log(filterTag);

    return (
        <div class="reviews-container">
            <Tag reviews sendDataToParent={sendDataToParent} />
            {reviews}
        </div>
    )
}

export default Reviews;
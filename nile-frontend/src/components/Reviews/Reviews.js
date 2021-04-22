import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from '../../service/ReviewService';
import { useState } from 'react';
import Tag from '../../components/ReviewTag/Tag';

function Reviews() {

    const[reviews,setReviews]=useState([]);
    // const[filterTag,setFilterTag]=useState("");
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
    //filter reviews based on the tag
    const sendDataToParent = (tag) =>
    {
        console.log("From the parent "+ tag);
        for(let i = 0; i < reviews.length;i++)
        {
            if(reviews[i].props.summary.includes(tag))
            {
                console.log(reviews[i].props.summary);
                const filtered = reviewList.map((review)=>
                <SingleReview
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}
            />
                );
                setFilteredReviews(reviews)
            }
            else
            {
                console.log("Nothing to see here");
            }
        }

    }



    

    return (
        <div class="reviews-container">
            <Tag reviews sendDataToParent={sendDataToParent} />
            {reviews}
        </div>
    )
}

export default Reviews;
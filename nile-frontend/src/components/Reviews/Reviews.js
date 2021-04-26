import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from '../../service/ReviewService';
import { useState } from 'react';
import Tag from '../../components/ReviewTag/Tag';

function Reviews() {

    const[reviews,setReviews]=useState([]);
    const[filteredReviews,setFilteredReviews]=useState([]);

    const reviewList=[];

    const test = document.URL.substring(43)

    // console.log(test)

    if(reviews.length===0){
        getReviews(test).then((list)=>
        {
            list.map((item)=>
            reviewList.push(item)
            );
            const reviews=reviewList.map((review)=>
                <SingleReview
                    key={review.reviewId}
                    helpful={review.helpful}
                    rating={review.rating}
                    reviewDate={review.reviewDate}
                    title={review.title}
                    summary={review.summary}
                />
            );
            setReviews(reviews)
            setFilteredReviews(reviews)
        }
        )
    }

    //Recieve data from Tag.js
    //filter reviews based on the tag
    const sendDataToParent = (tag) =>
    {   
        setReviews(reviews)
        for(let i = 0; i < reviews.length;i++)
        {
            if(tag!=="clearSelection" && reviews[i].props.summary.includes(tag))
            {
                reviewList.push(reviews[i].props)
                const filtered = reviewList.map((review)=>
                <SingleReview
                key = {review.reviewId}
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}
            />
                );
                setFilteredReviews(filtered)
            }
            else if(tag==="clearSelection"){
                setFilteredReviews(reviews)

            }
        }

    }


    return (
        <div class="reviews-container">
            <Tag
            reviews={reviews}
            sendDataToParent={sendDataToParent} />
            {filteredReviews}
        </div>
    )
}

export default Reviews;
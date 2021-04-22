import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from '../../service/ReviewService';
import { useState } from 'react';
import Tag from '../../components/ReviewTag/Tag';

function Reviews() {

    const[reviews,setReviews]=useState([]);
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
            setFilteredReviews(reviews)
        }
        )
    }

    //Recieve data from Tag.js
    //filter reviews based on the tag
    const sendDataToParent = (tag, selected) =>
    {   
        setReviews(reviews)
        console.log("From the parent "+ tag);
        for(let i = 0; i < reviews.length;i++)
        {
            if(tag!=="clear" && reviews[i].props.summary.includes(tag))
            {
                console.log(reviews[i].props.summary);
                reviewList.push(reviews[i].props)
                const filtered = reviewList.map((review)=>
                <SingleReview
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}
            />
                );
                setFilteredReviews(filtered)
            }
            else if(tag==="clear"){
                setFilteredReviews(reviews)

            }
        }

    }


    return (
        <div class="reviews-container">
            <Tag reviews sendDataToParent={sendDataToParent} />
            {filteredReviews}
        </div>
    )
}

export default Reviews;
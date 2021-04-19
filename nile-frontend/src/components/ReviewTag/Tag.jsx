import "./Tag.css";
import { useEffect, useState } from "react";
import {getReviews} from '../../service/ReviewService';
import Reviews from "../Reviews/Reviews";

function Tag (props)
{
    const [reviews, setReviews]= useState([]);
    const allReviews= [];


    //get the summary reviews and put into an array
    if(reviews.length===0)
    {
        getReviews().then((list) =>
        {
            list.map((review) =>
            allReviews.push(review)
            
        );
        console.log(allReviews)
            const reviews = allReviews.map((review)=>
         <Reviews
            reviewId = {review.reviewId}
            title = {review.title}
            summary= {review.summary}
            user = {review.user}
            reviewDate = {review.reviewDate}
            rating = {review.rating}
            helpful = {review.helpful}
            feature = {review.feature}
            />
            );
            setReviews(reviews);
         }
        );
    }
    
    console.log(reviews);

    const tagArr = ["soft touch", "great length","easy to manage"];

    const tagList = tagArr.map((phrase) =>
    <button>{phrase}</button> );

    return (
        <div class= "tag-container">
            {tagList}
            {allReviews}
        </div>

    );
}

export default Tag;


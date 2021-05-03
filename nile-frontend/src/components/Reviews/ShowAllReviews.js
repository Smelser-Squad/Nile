import React, { useState } from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'
import {getProduct} from '../../service/ProductService';
import {getReviews} from '../../service/ReviewService';
import SingleReview from './SingleReview';



function ShowAllReviews (){

    const[allReviews, setAllReviews] = useState([]);
    const[mostPositive, setPositive]= useState();
    const[mostNegative, setNegative]=useState();
    const { productId } = useParams();

    const reviewsList =[];
    // const positive=[];
    // const negative=[];


    if(allReviews.length===0)
    {
        getReviews(productId).then((list)=>{
            list.map((summary)=>
            reviewsList.push(summary));
            const reviews = reviewsList.map((review)=>
            <SingleReview
            key={review.reviewId}
            helpful={review.helpful}
            rating={review.rating}
            reviewDate={review.reviewDate}
            title={review.title}
            summary={review.summary}/>
            );
            const positive = reviewsList.filter(review => review.rating === 5).slice(0,1).map((review)=>
            <SingleReview
            key={review.reviewId}
            helpful={review.helpful}
            rating={review.rating}
            reviewDate={review.reviewDate}
            title={review.title}
            summary={review.summary}/>
            );
            const negative = reviewsList.filter(review => review.rating === 1).slice(0,1).map((review)=>
            <SingleReview
            key={review.reviewId}
            helpful={review.helpful}
            rating={review.rating}
            reviewDate={review.reviewDate}
            title={review.title}
            summary={review.summary}/>
            );
            setPositive(positive);
            setNegative(negative);
        
            setAllReviews(reviews);
        })
    }
    console.log(mostPositive)
    console.log(mostNegative)



    return(
        <div class="all-reviews-container">
            <h3>Top positive review:</h3>
            {mostPositive}
            <h3>Top critical review:</h3>
            {mostNegative}
            {allReviews}
        </div>
    )

}

export default ShowAllReviews;
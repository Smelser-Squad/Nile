import React, { useState } from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'
import {getProduct} from '../../service/ProductService';
import {getReviews} from '../../service/ReviewService';
import SingleReview from './SingleReview';



function ShowAllReviews(){

    const [showLength, setLength] = useState(3);
    const[allReviews, setAllReviews] = useState([]);
    const[mostPositive, setPositiove]= useState([]);
    const[mostNegative, setNegative]=useState([]);
    const { productId } = useParams();
    // const { productName } = useParams();

    const reviewsList =[];

    getReviews(productId).then((list)=>{
        list.map((summary)=>
        reviewsList.push(summary));
        console.log(reviewsList);
        const reviews = reviewsList.slice(0,showLength).map((review)=>
        

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

    const showMoreReviews = () =>
    {
        setLength(showLength+3);
    }

    return(
        <div class="all-reviews-container">
            
            {allReviews}
            <button onClick={showMoreReviews}>Show More</button>
        </div>
    )

}

export default ShowAllReviews;
import React from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'


function ShowAllReviews(){

    const { productId } = useParams();

    return(
        <div class="all-reviews-container">
            Reviews will be here

        </div>
    )

}

export default ShowAllReviews;
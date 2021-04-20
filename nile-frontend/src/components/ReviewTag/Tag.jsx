import "./Tag.css";
import { useEffect, useState } from "react";
import {getReviews} from '../../service/ReviewService';
import Reviews from "../Reviews/Reviews";

//hard coded phrases
const tagArr = ["soft touch", "great length","easy to manage"];
const reviewArr = ["This is soft to the touch", "This has a great length!","This is easy to manage!", "This is soft and has a great length!", "This is a very good value",
"Amazing cost and very soft", "Really long and amazing. So easy to handle!"];


function Tag (props)
{
    
    const [reviews, setReviews]= useState(reviewArr);
    const [filter, setFilter]= useState([]);





    console.log(reviews)


    //Find common phrases from the reviews
    //Fill this array with those phrases
    //filter by the phrases

    const tagList = tagArr.map((phrase) =>
    <button>{phrase}</button> );


    return (
        <div class= "tag-container">
            <h3>Read reviews that mention</h3> 
            {tagList}
        </div>

    );
}

export default Tag;


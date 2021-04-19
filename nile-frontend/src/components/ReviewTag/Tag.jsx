import "./Tag.css";
import { useEffect, useState } from "react";
import {getReviews} from '../../service/ReviewService';
import Reviews from "../Reviews/Reviews";

function Tag (props)
{
    const [reviews, setReviews]= useState([]);
    const allReviews= [];
    console.log(reviews)
    console.log(allReviews);


    const tagArr = ["soft touch", "great length","easy to manage"];

    const tagList = tagArr.map((phrase) =>
    <button>{phrase}</button> );

    return (
        <div class= "tag-container">
            {tagList}
        </div>

    );
}

export default Tag;


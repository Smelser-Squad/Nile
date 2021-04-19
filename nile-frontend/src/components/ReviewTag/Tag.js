import "./Tag.css";
import axios from "axios";
import { useEffect, useState } from "react";

function Tag (props)
{
    // TODO: retrieve review data from api call
    //hardcoded for now
    const [data, getReviews]= useState("");
    const reviews= [];
    const url = "http://localhost:8080/api/reviews";


    //call function
    useEffect(()=> {
        getAllReviews();
    });

    //get the summary reviews and put into an array
    const getAllReviews = () => {
        axios.get(`${url}`)
        .then((response ) => 
        {
            for(let i=0; i < response.data.length;i++)
            {
                reviews.push(response.data[i].summary);

            }
            console.log(reviews);
        })
        .catch(error => console.error(`Error: ${error}`)); 
       }

    console.log(reviews);
    const tagArr = ["soft touch", "great length","easy to manage"];
    console.log(tagArr)
    const tagList = tagArr.map((phrase) =>
    <button>{phrase}</button> );

    return (
        <div class= "tag-container">
            {tagList}
            {props.summary}
        </div>

    );
}

export default Tag;


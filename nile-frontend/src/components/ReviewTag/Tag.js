import "./Tag.css";
import axios from "axios";
import { useEffect, useState } from "react";

function Tag(props)
{
    // TODO: retrieve review data from api call
    //hardcoded for now
    // const [reviews]= useState("");
    const reviews= [];
    const url = "http://localhost:8080/api/reviews";

    useEffect(()=> {
        getAllReviews();

    }, []);

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
    console.log(props);
    const tagArr = ["soft touch", "great length","easy to manage"];
    console.log(tagArr)
    const tagList = tagArr.map((phrase) =>
    <button>{phrase}</button> );

    return (
        <div class= "tag-container">
            {tagList}
        </div>

    );
}

export default Tag;


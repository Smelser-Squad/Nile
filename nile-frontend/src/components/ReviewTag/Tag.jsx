import "./Tag.css";
import React from 'react'
 
//Find common phrases from the reviews
//Fill this array with those phrases
//filter by the phrases

//hard coded phrases
const tagArr = ["soft", "length", "manageable","cheap"];

const Tag = ({sendDataToParent}) =>
{

    //set up buttons
   const tagList = tagArr.map((phrase) =>
    <button 
    //send data to Review.js
    onClick = {()=> sendDataToParent(phrase)}
    value = {phrase}>
        {phrase}
    </button> );


    return (
        <div class= "tag-container">
            <h3>Read reviews that mention</h3> 
            {tagList}
        </div>

    );
}



export default Tag;
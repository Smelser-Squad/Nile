import "./Tag.css";
import React from 'react'
 
//Find common phrases from the reviews
//Fill this array with those phrases
//filter by the phrases

//hard coded phrases
const tagArr = ["soft", "length", "manageable"];
// const reviewArr = ["This is soft to the touch", "This has a great length!","This is easy to manage!", "This is soft and has a great length!", "This is a very good value",
// "Amazing cost and very soft", "Really long and amazing. So easy to handle!"];

//potentially can leave this out
function Tag (props)
{
    console.log(props.reviews);

    const tagList = tagArr.map((phrase) =>
    <button 
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
import "./Tag.css";
import React from 'react'
import ToggleButton from '@material-ui/lab/ToggleButton';
import HighlightOffIcon from '@material-ui/icons/HighlightOff';
import { ToggleButtonGroup } from '@material-ui/lab';
import { useState } from 'react';


 
//Find common phrases from the reviews
//Fill this array with those phrases
//filter by the phrases

//hard coded phrases
const tagArr = ["soft", "length", "manageable","cheap"];

const Tag = ({reviews,sendDataToParent}) =>
{

    const stringArr = [];
    Object.entries(reviews).map(([summary,value])=>stringArr.push(value.props.summary));
    console.log(stringArr);


    const tagList = (
  
        tagArr.map((phrase)=>
        <ToggleButton
            value = {phrase}         
            onClick = {()=> 
            sendDataToParent(phrase)}>
            {phrase}
        </ToggleButton>
        )

      )
        

    return (
        <div class= "tag-container">
            <h3 id="tag-header">Read reviews that mention</h3> 
            <ToggleButtonGroup>
            {tagList}
            <ToggleButton>
            <HighlightOffIcon 
            value="clearSelection"
            onClick = {()=> 
                sendDataToParent("clearSelection")}
                >
            </HighlightOffIcon>
            </ToggleButton>
            </ToggleButtonGroup>

        </div>

    );
}

// var wordCounts = { };
// var words = str.split(/\b/);

// for(var i = 0; i < words.length; i++)
//     wordCounts["_" + words[i]] = (wordCounts["_" + words[i]] || 0) + 1;

export default Tag;
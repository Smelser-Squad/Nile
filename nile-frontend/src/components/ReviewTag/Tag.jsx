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
const tagArr = [];

const Tag = ({reviews,sendDataToParent}) =>
{
    //create a string array and push all review text from parent into that array
    const stringArr = [];
    Object.entries(reviews).map(([summary,value])=>stringArr.push(value.props.summary));

    let wordCounts = { };
    for(let i =0; i<stringArr.length;i++)
    {

        let words = stringArr[i].toLowerCase().split(/\b/);
        for(let j = 0; j < words.length; j++)
        {
            wordCounts[words[j]] = (wordCounts[words[j]] || 0) + 1;
        }
    }


for (const key in wordCounts) {
    console.log(key);
    if (Object.hasOwnProperty.call(wordCounts, key)) {
        const element = wordCounts[key];
        if(element>=3)
        {
            console.log(element)
            tagArr.push(key);
        }
        
    }
}

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

export default Tag;
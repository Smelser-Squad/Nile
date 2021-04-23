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

const Tag = ({sendDataToParent}) =>
{
    const[selected, setSelected] = useState(false); 

    const handleSelected = (event, newSelect) =>
    {
        setSelected(!selected);
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
                sendDataToParent("clearSelection")}>
            </HighlightOffIcon>
            </ToggleButton>
            </ToggleButtonGroup>

        </div>

    );
}

// {/* <ToggleButtonGroup
// value= {selected}
// exclusive={true}
// onChange = {handleSelected}
// > */}

export default Tag;
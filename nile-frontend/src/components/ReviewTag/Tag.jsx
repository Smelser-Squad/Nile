import "./Tag.css";
import React from 'react'
import ToggleButton from '@material-ui/lab/ToggleButton';
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

    return (
        <div class= "tag-container">
            <h3>Read reviews that mention</h3> 
            <ToggleButtonGroup
                value= {selected}
                exclusive={true}
                onChange = {handleSelected}
                >
                {tagArr.map((phrase)=>

                <ToggleButton
                    value = {phrase}         
                    onClick = {()=> 
                    sendDataToParent(phrase)}>
                    {phrase}
                </ToggleButton>

                )}
                </ToggleButtonGroup>
        </div>

    );
}


// filteredReviews (){
//     if(this.search){
//       return this.list.filter((item)=>{
//         return item.reviewer.description.toLowerCase().includes(this.search.toLowerCase());
//       })
//       }else{
//         return this.list;
//       }
//     }
//   }


export default Tag;
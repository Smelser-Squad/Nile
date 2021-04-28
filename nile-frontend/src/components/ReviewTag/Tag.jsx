import "./Tag.css";
import React from 'react'
import ToggleButton from '@material-ui/lab/ToggleButton';
import { ToggleButtonGroup } from '@material-ui/lab';
import { useState } from 'react';
import { makeStyles } from "@material-ui/core";



const commonWords = getStopWords();


const Tag = ({reviews,sendDataToParent}) =>
{
    const [filterPhrase, setFilterPhrase] = useState("")
    const [selected, setSelected] = React.useState(false);

    //toggle button
    const handleToggle = (event, newFilterPhrase) => {
        setFilterPhrase(newFilterPhrase);
        if(newFilterPhrase===null)
        { 
            sendDataToParent("clearSelection")
        }    
    }

    //array to hold string tags
    const tagArr = [];
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
    // console.log(key);
    if (Object.hasOwnProperty.call(wordCounts, key)) {
        const element = wordCounts[key];
        //push phrases into tag array if it's occurence is above a certain amount
        //and it isn't already present in the array
        if(element>=2 && !tagArr.includes(key)&& !commonWords.includes(key))
        {
            // console.log(element)
            tagArr.push(key);
        }
        
    }
}

const useStyles = makeStyles({
    root: {
        borderRadius: "0px",
        border: "transparent",
        borderBottom: "1px solid grey",
    },
    groupedHorizontal: {
        "&:not(:first-child)":{
            border: "transparent",
            borderBottom: "1px solid grey" 
        },
    }
});

const classes = useStyles();

    const tagList = (
        tagArr.map((phrase)=>
        <ToggleButton
            classes= {{
                root: classes.root,
                selected: classes.selected,
            }}
            disableRipple
            disableFocusRipple
            value = {phrase}         
            onClick = {()=> 
            sendDataToParent(phrase)}
            onChange={handleToggle}>
            {phrase}
        </ToggleButton>
        )

      )
        

      return (
        <div class= "tag-container">
            <h3 id="tag-header">Read reviews that mention</h3> 
            <ToggleButtonGroup
            classes={{
                groupedHorizontal: classes.groupedHorizontal,
            }}
            value={filterPhrase}
            exclusive
            orientation = "horizontal"
            onChange={handleToggle}>
            {tagList}
            </ToggleButtonGroup>
        </div>

    );
}

function getStopWords()
{
    return ["s"," ","'","!",".","?",",","/","a","able","about","across","after","all","almost","also","am","among","an","and","any","are","as","at","be","because","been","but","by","can","cannot","could","dear","did","do","does","either","else","ever","every","for","from","get","got","had","has","have","he","her","hers","him","his","how","however","i","if","in","into","is","it","its","just","least","let","like","likely","may","me","might","most","must","my","neither","no","nor","not","of","off","often","on","only","or","other","our","own","rather","said","say","says","she","should","since","so","some","than","that","the","their","them","then","there","these","they","this","tis","to","too","twas","us","wants","was","we","were","what","when","where","which","while","who","whom","why","will","with","would","yet","you","your","ain't","aren't","can't","could've","couldn't","didn't","doesn't","don't","hasn't","he'd","he'll","he's","how'd","how'll","how's","i'd","i'll","i'm","i've","isn't","it's","might've","mightn't","must've","mustn't","shan't","she'd","she'll","she's","should've","shouldn't","that'll","that's","there's","they'd","they'll","they're","they've","very","wasn't","we'd","we'll","we're","weren't","what'd","what's","when'd","when'll","when's","where'd","where'll","where's","who'd","who'll","who's","why'd","why'll","why's","won't","would've","wouldn't","you'd","you'll","you're","you've"];
}




export default Tag;
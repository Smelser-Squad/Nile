import React, { useEffect, useState } from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'
import {getProduct} from '../../service/ProductService';
import {getReviews} from '../../service/ReviewService';
import SingleReview from './SingleReview';
import { Button, TextField,InputAdornment } from '@material-ui/core';
import { makeStyles } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';




function ShowAllReviews (){

    const[allReviews, setAllReviews] = useState([]);
    const[filteredReviews, setFilteredReviews] = useState([]);
    const[mostPositive, setPositive]= useState();
    const[mostNegative, setNegative]=useState();
    const { productId } = useParams();

    const reviewsList =[];

    useEffect(()=>
    {
        if(allReviews.length===0)
        {
            getReviews(productId).then((list)=>{
                list.map((summary)=>
                reviewsList.push(summary));
                const reviews = reviewsList.map((review)=>
                <SingleReview
                key={review.reviewId}
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}/>
                );
                const positive = reviewsList.filter(review => review.rating === 5).slice(0,1).map((review)=>
                <SingleReview
                key={review.reviewId}
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}/>
                );
                const negative = reviewsList.filter(review => review.rating === 1).slice(0,1).map((review)=>
                <SingleReview
                key={review.reviewId}
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}/>
                );
                setPositive(positive);
                setNegative(negative);
                setAllReviews(reviews);
                setFilteredReviews(reviews)
            
            })
        }
    },[allReviews,mostNegative,mostPositive]);




    //SEARCH FILTER HERE!!!

    

    const useInputStyles = makeStyles({
        root:{
            marginTop: "1%",
            marginBottom: "1%",
            width: "35%",
            
        },
        adornedStart:
        {
            paddingLeft:"8px",
        }

    });

    const useButtonStyles = makeStyles({
        root:{
            marginTop: "1%",
            marginBottom: "1%",
            marginLeft: "1%",
            
        },

    });

    const inputClasses = useInputStyles();
    const buttonClasses = useButtonStyles();
    
 


    return(
        <div class="all-reviews-container">
            <hr></hr>
            <div class="top-reviews">
            <div class="positive-review">
                <h3>Top positive review:</h3>
                {mostPositive}
            </div>
            <div class="negative-review">            
                <h3>Top critical review:</h3>
                {mostNegative}
            </div>
            </div>
            <hr></hr>

            <TextField
            classes = {{
                root: inputClasses.root,
                adornedStart: inputClasses.adornedStart,
            }}
            InputProps={{
                startAdornment: 
                <InputAdornment 
                classes ={{
                    adornedStart: inputClasses.adornedStart,
                }}
                position="start">
                    <SearchIcon/>
                </InputAdornment>,
              }}
             variant="outlined" 
             size="small" ></TextField>
            <Button 
            classes={{
                root: buttonClasses.root,
            }}
            size="small" 
            variant="contained">
                Search</Button>
            {filteredReviews}
        </div>
    )

}

export default ShowAllReviews;
import React, { useEffect, useState } from 'react'
import  "./ShowAllReviews.css";
import {useParams} from 'react-router'
import {getReviews} from '../../service/ReviewService';
import SingleReview from './SingleReview';
import { Button, TextField,InputAdornment } from '@material-ui/core';
import { makeStyles } from "@material-ui/core";
import SearchIcon from '@material-ui/icons/Search';
import Pagination from "../../common/Pagination";




function ShowAllReviews (){

    const[allReviews, setAllReviews] = useState([]);
    const[filteredReviews, setFilteredReviews] = useState([]);
    const[filtered, setFiltered] = useState(false);
    const[filterText, setFilterText] = useState("");
    const[mostPositive, setPositive]= useState();
    const[mostNegative, setNegative]=useState();
    
    const[currPage, setCurrPage]=useState(1);
    const[postsPerPage, setPostsPerPage]=useState(10);

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

    const indexOfLastPost = currPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentPosts = allReviews.slice(indexOfFirstPost, indexOfLastPost);

    const useInputStyles = makeStyles({
        root:{
            marginTop: "1%",
            marginBottom: "1%",
            width: "35%",
            background: "white",
            borderRadius: "6%",  
         
        },
        adornedStart:
        {
            paddingLeft: "8px", 
        }
     
    });

    const useButtonStyles = makeStyles({
        root:{
            marginTop: "1%",
            marginBottom: "1%",
            marginLeft: "1%",
            fontFamily: "inherit",
            fontWeight: "bolder",
            color: "white",
            textTransform: "none",
            background: "linear-gradient(to bottom, #72787F, #444C55)",

        },

    });

    const inputClasses = useInputStyles();
    const buttonClasses = useButtonStyles();


    //SEARCH FILTER HERE!!!
    const handleFilter = () => {

        for(let i = 0; i < allReviews.length;i++)
        {
            setAllReviews(allReviews)
            if(allReviews[i].props.summary.includes(filterText))
            {
                reviewsList.push(allReviews[i].props)
                const filtered = reviewsList.map((review)=>
                <SingleReview
                key = {review.reviewId}
                helpful={review.helpful}
                rating={review.rating}
                reviewDate={review.reviewDate}
                title={review.title}
                summary={review.summary}
            />
                );
                setFilteredReviews(filtered)
                setFiltered(true)
            }
        }
    }

    const handleSearch = (event) => {
        setFilterText(event.target.value)
    }

    const clearFilter = () =>
    {
        setFilteredReviews(allReviews)
        document.getElementById("textField").value = ""; 
        setFilterText("")
        setFiltered(false)
    }

    const paginate = pageNumber =>{ setCurrPage(pageNumber)}


    return(
        <div class="all-reviews-container">
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
            id="textField"
            placeholder="Search customer reviews"
            onChange={handleSearch}
            classes = {{
                root: inputClasses.root,
            }}
            InputProps={{
                startAdornment: 
                <InputAdornment
                position="start">
                    <SearchIcon/>
                </InputAdornment>,
                classes:{adornedStart: inputClasses.adornedStart}
              }}
             variant="outlined" 
             size="small" ></TextField>
            <Button 
            onClick={handleFilter}
            classes={{
                root: buttonClasses.root,
            }}
            size="small"
            variant="contained">
                Search</Button>
                {filtered === true &&
                <div class="header-container">
                <h3 >Filtered By</h3> <br/>
                <span>Containing "{filterText}"</span>
                <button id="clearButton" onClick={()=>{clearFilter()}}>Clear filter</button> <br/>
                {filteredReviews}
                </div>
                }
            {currentPosts}
            <Pagination 
            postsPerPage={postsPerPage} 
            totalPosts={allReviews.length} 
            paginate={paginate}></Pagination>
        </div>
    )

}

export default ShowAllReviews;
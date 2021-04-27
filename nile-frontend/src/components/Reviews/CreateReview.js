import './CreateReview.css'
import ReactStars from "react-rating-stars-component";
import {addReview} from "../../service/ReviewService"
import { Radio } from '@material-ui/core';
import React from 'react';


    function onTitleChange() {
        this.setState({
            title: this.target.value
        });
    };

function CreateReview(){

    

    const Review = {
        rating : 5,
        review_date : "2021-04-16",
        summary : "test summary",
        title: "test title",
        product : {
            productId: 1
        }
    }

    return (
        <div id="createReviewContainer">
            Create review
            <div>
                (IMAGE)(ITEM DESCRIPTION)
            </div>
            <hr></hr>
            <div>
            Overall rating
            <br/>
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
            </div>
            <hr></hr>
            Rate features
            <br/>
            (FEATURE 1)
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
            <br/>
            <div>
            (FEATURE 2)
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
            </div>
            <div>
            (FEATURE 3)
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
            </div>
            <div id="sizeDiv">
                <span>(HOW IT FITS)</span>
                <div id="sizeButtonDiv">
                    <input type="radio" class="sizeReview" id="size1" name="size"></input>
                    <input type="radio" class="sizeReview" id="size2" name="size"></input>
                    <input type="radio" class="sizeReview" id="size3" name="size"></input>
                    <input type="radio" class="sizeReview" id="size4" name="size"></input>
                    <input type="radio" class="sizeReview" id="size5" name="size"></input>
                </div>
                <div id="sizeDescription">
                    <span>Too small</span>
                    <span>as expected</span>
                    <span>Too large</span>
                </div>
            </div>
            <hr></hr>
            Add a photo
            <br/>
            (PHOTO SUBMIT)
            <hr></hr>
            Add a headline
            <br/>
            {/* <input  placeholder="title"
            value={this.Review.title}
            onChange={this.onTitleChange} required
            /> */}
            <br/>
            Add a written review
            <br/>
            <input placeholder="Review summary"/>
            <br/>
            <button onClick={()=>addReview(Review)}>submit</button>
        </div>
    )
    

}

export default CreateReview;
import './CreateReview.css'
import ReactStars from "react-rating-stars-component";
import { Radio } from '@material-ui/core';


function createReview(){

    return (
        <div id="createReviewContainer">
            Create review
            <br/>
            (IMAGE)(ITEM DESCRIPTION)
            <hr></hr>
            Overall rating
            <br/>
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
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
            (FEATURE 2)
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
            <br/>
            (FEATURE 3)
            <ReactStars
                    count={5}
                    edit={true}
                    value={0}
                    activeColor="#FFA41C"
                    size={15}
                />
            <br/>
            (HOW IT FITS)
            <div>
                <input type="radio" class="sizeReview" id="size1" name="size"></input>
                <input type="radio" class="sizeReview" id="size2" name="size"></input>
                <input type="radio" class="sizeReview" id="size3" name="size"></input>
                <input type="radio" class="sizeReview" id="size4" name="size"></input>
                <input type="radio" class="sizeReview" id="size5" name="size"></input>
            </div>
            <span id="size-description">Too small - as expected - Too large</span>
            <hr></hr>
            Add a photo
            <br/>
            (PHOTO SUBMIT)
            <hr></hr>
            Add a headline
            <br/>
            <input placeholder="Review title"/>
            <br/>
            Add a written review
            <br/>
            <input placeholder="Review summary"/>
        </div>
    )
}

export default createReview;
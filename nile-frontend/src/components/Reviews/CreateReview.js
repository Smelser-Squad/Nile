import './CreateReview.css'
import ReactStars from "react-rating-stars-component";
import {addReview} from "../../service/ReviewService"
import { Radio } from '@material-ui/core';
import React, { useState } from 'react';
import {getProduct} from '../../service/ProductService';
import { PrintDisabled } from '@material-ui/icons';
import { useParams } from 'react-router';





function CreateReview(){

    // const[Product,setProduct,summary,title]=useState([]);

    const [name,setName]=useState([]);
    const [image,setImage]=useState([]);
    const [summary, setSummary]=useState([]);
    const [title, setTitle]=useState([]);
    const [rating, setRating]=useState([]);


    const { productId } = useParams();

    getProduct(productId).then((name)=>{
        setName(name.name);
    })

    getProduct(productId).then((image)=>{
        setImage(image.photos[0].imageSrc);
        setRating(parseInt(document.getElementsByTagName("p")[0].innerHTML))
    })



    const mili = Date.now();
    const formattedDate = new Date(mili);
    const stringDate = formattedDate.getFullYear()+"-"+(formattedDate.getMonth()+1)+"-"+formattedDate.getDate();

    const Review = {
        rating : rating,
        review_date : stringDate, //YYYY-MM-DD
        summary : summary,
        title: title,
    }
    console.log(Review)

    return (
        
        <div id="createReviewContainer">
            <div id="product-info-review">
                <img id="product-image-review"src={image}></img>
                <span id="product-name-review">{name}</span>
            </div>
            <hr></hr>
            <div id="overall-rating">
                Overall rating
                <br/>
                    <ReactStars
                        count={5}
                        edit={true}
                        value={rating}
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
            <input id="textTitle" placeholder="Review title" onChange={e => setTitle(e.target.value)}/>
            <br/>
            Add a written review
            <br/>
            <textarea id="textSummary" placeholder="Review summary" onChange={e => setSummary(e.target.value)}/>
            <br/>
            <button onClick={()=>addReview(Review)}>submit</button>
        </div>
    )
    

}

export default CreateReview;
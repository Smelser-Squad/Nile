import './CreateReview.css'
import ReactStars from "react-rating-stars-component";
import {addReview,addFeatureRating} from "../../service/ReviewService"
import React, { useState } from 'react';
import {getProduct} from '../../service/ProductService';
import { useParams } from 'react-router';
import {useEffect} from 'react';




function CreateReview(){

    // The only way I could get rating passed to review object was to grab the innerHTML from the p tag at the 0th index
    // since it is the react-stars at the begining. features will be done in the same way once they are finalized

    const [name,setName]=useState([]);
    const [image,setImage]=useState([]);
    const [summary, setSummary]=useState([]);
    const [title, setTitle]=useState([]);
    const [rating, setRating]=useState(-1);
    const [features, setFeatures]=useState([]);

    const { productId } = useParams();

    useEffect(()=> {
        getProduct(productId).then((response)=>
        {
            setFeatures(response.features)
            setName(response.name)
            setImage(response.photos[0].imageSrc);

        })
    },[])


    const mili = Date.now();
    const formattedDate = new Date(mili);
    const stringDate = formattedDate.getFullYear()+"-"+(formattedDate.getMonth()+1)+"-"+formattedDate.getDate();

    const Review = {
        rating : rating,
        review_date : stringDate, //YYYY-MM-DD
        summary : summary,
        title: title,
        product : {
            productId : productId
        }
    }

    function submitReview(Review,productId){
        for(var i=0;i<features.length;i++){

            const featureRating = {
                product :{
                    productId : productId
                },
                feature :{
                    featureId : features[i].featureId
                },
                rating : document.getElementsByTagName("p")[i+1].innerHTML
            }

            addFeatureRating(featureRating)
        }
        setRating(document.getElementsByTagName("p")[0].innerHTML)
        addReview(Review,productId)

    }

    return (
        
        <div id="createReviewContainer">
            {/* <img src="src/assets/loading.gif"></img> */}
            <h2>Create Review</h2>
            <div id="product-info-review">
                <img id="product-image-review"src={image}></img>
                <h5 id="product-name-review">{name}</h5>
            </div>
            <hr class="divide"></hr>
            <div id="overall-rating">
                <h3>Overall Rating</h3>
                    <ReactStars
                        onChange={setRating}
                        count={5}
                        edit={true}
                        value={0}
                        activeColor="#FFA41C"
                        size={40}
                    />
            </div>
            <hr class="divide"></hr>
            <h3>Rate Features</h3>
            {features.length>0 ?

             (
            <div id="feature-rating">
                <span>{features[0].name}</span>
                <ReactStars
                        count={5}
                        edit={true}
                        value={0}
                        activeColor="#FFA41C"
                        size={30}
                    />
                <div>
                    <span>{features[1].name}</span>
                    <ReactStars
                            count={5}
                            edit={true}
                            value={0}
                            activeColor="#FFA41C"
                            size={30}
                        />
                </div>
                <div>
                    <span>{features[2].name}</span>
                    <ReactStars
                            count={5}
                            edit={true}
                            value={0}
                            activeColor="#FFA41C"
                            size={30}
                        />
                </div>
                <div>
                    <span>{features[3].name}</span>
                    <ReactStars
                            count={5}
                            edit={true}
                            value={0}
                            activeColor="#FFA41C"
                            size={30}
                        />
                </div>
                <div>
                    <span>{features[4].name}</span>
                    <ReactStars
                            count={5}
                            edit={true}
                            value={0}
                            activeColor="#FFA41C"
                            size={30}
                        />
                </div>
                <div>
                    <span>{features[5].name}</span>
                    <ReactStars
                            count={5}
                            edit={true}
                            value={0}
                            activeColor="#FFA41C"
                            size={30}
                        />
                </div>
            </div>
         ):

         (<div>LOADING</div>)}
            
            {/* <div id="sizeDiv">
                <span>How does this product fit?</span>
                <div id="sizeButtonDiv">
                    <input type="radio" class="sizeReview" id="size1" name="size"></input>
                    <input type="radio" class="sizeReview" id="size2" name="size"></input>
                    <input type="radio" class="sizeReview" id="size3" name="size"></input>
                    <input type="radio" class="sizeReview" id="size4" name="size"></input>
                    <input type="radio" class="sizeReview" id="size5" name="size"></input>
                </div>
                <div id="size-description">
                    <span>Too small</span>
                    <span>as expected</span>
                    <span>Too large</span>
                </div>
            </div> */}
            <hr class="divide"></hr>
            <div id="photo-submit">
                <h3>Add a photo</h3>
                <span id="photo-submit-description">shoppers find images and videos more helpful than text alone</span>
                <svg width="24" height="24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd"><path d="M23 0v20h-8v-2h6v-16h-18v16h6v2h-8v-20h22zm-12 13h-4l5-6 5 6h-4v11h-2v-11z"/></svg>
            </div>
            <hr class="divide"></hr>
            <div id="review-writting">
                <h3>Add a headline</h3>
                <input id="text-title" alt="image of product" placeholder="What's important to know?" onChange={e => setTitle(e.target.value)}/>
                <h3>Add a written review</h3>
                <textarea id="text-summary" placeholder="What did you like or dislike? How did you use this product?" onChange={e => setSummary(e.target.value)}/>
                <button id="submit-review" disabled={Review.rating===-1 || Review.title<1 || Review.summary < 1} onClick={()=>submitReview(Review,productId)}>submit</button>
            </div>
        </div>
    )
    

}



export default CreateReview;
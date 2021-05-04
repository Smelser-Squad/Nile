import {useState,useEffect} from 'react';
import axios from 'axios'


export async function getReviews(productId){
    const reviews=await axios.get(`http://localhost:80/api/reviews/byproduct/${productId}`)
    const data=reviews.data;
    return data;

}

export function addReview(Review,productId){
    axios.post(`http://localhost:80/api/reviews`,Review);
    window.location.replace(`http://localhost:3000/singleProductListing/${productId}`)
}

export function addFeatureRating(featureRating){
    axios.post(`http://localhost:80/api/featureRating`,featureRating)
}
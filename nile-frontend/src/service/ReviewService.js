import {useState,useEffect} from 'react';
import axios from 'axios'


export async function getReviews(productId){
    const reviews=await axios.get(`http://localhost:80/api/reviews/byproduct/${productId}`)
    const data=reviews.data;
    // console.log(data);

    return data;

}

export function addReview(Review){
    axios.post(`http://localhost:80/api/reviews`,Review);
}
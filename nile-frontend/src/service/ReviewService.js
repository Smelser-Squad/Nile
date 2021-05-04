import {useState,useEffect} from 'react';
import axios from 'axios'


export async function getReviews(productId){
    const reviews=await axios.get(`http://localhost:80/api/reviews/byproduct/${productId}`)
    const data=reviews.data;
    return data;

}

export async function getAverageReviewScore(productId) {
    const reviewData = {avg: 0, length: 0};
    const reviews=await axios.get(`http://localhost:80/api/reviews/byproduct/${productId}`)
        .catch(err => {
            return 0;
        });
    if (reviews === 0) {
        return reviewData;
    }
    const data=reviews.data;
    let sum = 0;
    for (let i = 0; i < data.length; i++) {
        sum += data[i].rating;
    }
    let avg = sum / data.length;
    reviewData["avg"] = avg.toFixed(1);
    reviewData["length"] = data.length;
    return reviewData;
}

export function addReview(Review,productId){
    axios.post(`http://localhost:80/api/reviews`,Review);
    window.location.replace(`http://localhost:3000/singleProductListing/${productId}`)
}
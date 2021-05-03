import {useState,useEffect} from 'react';
import axios from 'axios'


export async function getFeatures(productId){
    const reviews=await axios.get(`http://localhost:80/api/features/byproduct/${productId}`)
    const data=reviews.data;
    return data;

}

//product_id[OBJ], feature_id[OBJ], rating[INT]
//all inside featureRating object
export function addFeatureRating(featureRating){
    axios.post(`http://localhost:80/api/featureRating`,featureRating);
}
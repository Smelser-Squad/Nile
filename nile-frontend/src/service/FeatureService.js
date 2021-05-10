import {useState,useEffect} from 'react';
import axios from 'axios'


export async function getFeatures(productId){
    const reviews=await axios.get(`http://54.82.100.75/api/features/byproduct/${productId}`)
    const data=reviews.data;
    return data;

}

//product_id[OBJ], feature_id[OBJ], rating[INT]
//all inside featureRating object
export function addFeatureRating(featureRating){
    axios.post(`http://54.82.100.75/api/featureRating`,featureRating);
}
import {useState,useEffect} from 'react';
import axios from 'axios'

export function useReview(id){
    
    const[data,setData]=useState([]);

    const getReviews=async()=>{
        await axios.get('http://localhost:80/api/reviews/' + id)
        .then(res=>{
            const product=res.data;
            if(res.satus===200){
                setData(product)};
            })
        
    };
    useEffect(()=>{
        getReviews();},[]);
        return(
            data
        )
}
async function getReviews(productId){
    console.log("made it to function " + productId)
    const reviews=await axios.get(`http://localhost:80/api/reviews/byproduct/${productId}`)
    const data=reviews.data;
    // console.log(data);

    return data;

}
export {getReviews}
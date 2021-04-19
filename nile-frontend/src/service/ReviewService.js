import {useState,useEffect} from 'react';
import axios from 'axios'

export function useReview(id){
    const[data,setData]=useState([]);
        return(
            data
        )
}

async function getReviews(){
    const reviews=await axios.get('http://localhost:80/api/reviews')
    const data=reviews.data;
    return data;
}
export {getReviews}
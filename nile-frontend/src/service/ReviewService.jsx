import {useState,useEffect} from 'react';
import axios from 'axios';


export function useReviews(id)
{
    const[data,setData]=useState([]);

    const getReview = async()=> {

        await axios.get("http://localhost:80/api/reviews/"+id)
        .then(response => {
            const review = response.data;
            if(response.status === 200){
                setData(review)
            };
        })
    };
    
    useEffect(()=>{
    getReviews();

    },[]);

    return (data); 

}

async function getReviews(){
    const reviews=await axios.get('http://localhost:80/api/reviews')
    const data=reviews.data;
    return(data);

}
export {getReviews}
import {useState,useEffect} from 'react';
import axios from 'axios'
import { useParams } from 'react-router';


    // const {id}=useParams();

    async function getPhotos(productId){
        // const {id}=useParams();
        const photos=await axios.get(`http://localhost:80/api/productPhotos/${productId}`)
        const data=photos.data;
    
        return(data);
        
    }
    export {getPhotos}



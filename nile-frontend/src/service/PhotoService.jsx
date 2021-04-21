import {useState,useEffect} from 'react';
import axios from 'axios'
import { useParams } from 'react-router';


export function useProduct(id){
    
   
    }
    // const {id}=useParams();

    async function getPhotos(id){
        // const {id}=useParams();
        const photos=await axios.get(`http://localhost:80/api/productPhotos/` +id)
        const data=photos.data;
    
        return(data);
        
    }
    export {getPhotos}



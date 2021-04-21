import {useState,useEffect} from 'react';
import axios from 'axios'

export function useProduct(id){
    
   
    }
    async function getPhotos(id){
        const photos=await axios.get('http://localhost:80/api/productPhotos/' + 1)
        const data=photos.data;
    
        return(data);
        
    }
    export {getPhotos}



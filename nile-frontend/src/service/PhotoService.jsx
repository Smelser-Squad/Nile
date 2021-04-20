import {useState,useEffect} from 'react';
import axios from 'axios'

export function useProduct(id){
    
    // const[data,setData]=useState([]);

    // const getPhotos=async()=>{
    //     await axios.get('http://localhost:80/api/productPhotos/' + id)
    //     .then(res=>{
    //         const photos=res.data;
    //         if(res.status===200){
    //             setData(photos)
    //         };
    //     })
    // };
    // useEffect(()=>{
    //     getPhotos();},[]);
    //     return(
    //         data
    //     )
    }
    async function getPhotos(){
        const photos=await axios.get('http://localhost:80/api/productPhotos/' + 1)
        const data=photos.data;
    
        return(data);
        
    }
    export {getPhotos}



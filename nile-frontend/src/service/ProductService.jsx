import {useState,useEffect} from 'react';
import axios from 'axios'


export function useProduct(id){
    const[data,setData]=useState([]);

    const getProduct=async()=>{
        await axios.get('http://localhost:80/api/products/' + id)
        .then(res=>{
            const product=res.data;
            if(res.satus===200){
                setData(product)};
            })
        
    };
    useEffect(()=>{
        getProduct();},[]);
        return(
            data
        )
}
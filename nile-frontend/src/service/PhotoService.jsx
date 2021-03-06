
import axios from 'axios';

    async function getPhotos(productId,color){
        
        const photos=await axios.get(`http://localhost:80/api/productPhotos/${productId}/${color}`)
        const data=photos.data;
        
        return(data);
        
    }
    async function getListColors(productId){
        
        const photos=await axios.get(`http://localhost:80/api/productPhotos/colors/${productId}`)
        const data=photos.data;
    
        return(data);
        
    }

    async function getReviewPhotosForProduct(productId) {
        const res = await axios.get(`http://localhost:80/api/reviews/photosByProduct/${productId}`);
        return res.data;
    }
    export {getPhotos, getListColors, getReviewPhotosForProduct}



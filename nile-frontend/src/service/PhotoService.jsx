
import axios from 'axios'

    async function getPhotos(productId){
        
        const photos=await axios.get(`http://localhost:80/api/productPhotos/${productId}`)
        const data=photos.data;
    
        return(data);
        
    }
    export {getPhotos}



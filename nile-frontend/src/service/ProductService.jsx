import axios from 'axios'

async function getProducts(){
    const products=await axios.get('http://localhost:80/api/products')
    const data=products.data;

    return(data);
    
}

function getProductById(productId){
    return axios.get(`http://localhost:80/api/products/${productId}`)
}
export {getProducts,getProductById}


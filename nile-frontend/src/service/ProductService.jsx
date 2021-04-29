import axios from 'axios'


async function getProducts(){
    const products=await axios.get('http://localhost:80/api/products')
    const data=products.data;
    console.log(data);

    return(data);
    
}
async function getProduct(productId){
    const products=await axios.get(`http://localhost:80/api/products/${productId}`)
    const data=products.data;

    return(data);
}
async function getBrandProducts(brand){
    
    const products=await axios.get(`http://localhost:80/api/products/brand/${brand}`)
    const data=products.data;
    

    return(data);
}
async function getTypeProducts(type){
    const products=await axios.get(`http://localhost:80/api/products/type/${type}`);
    const data=products.data;

    return(data);
}
export {getProducts,getProduct, getBrandProducts, getTypeProducts}


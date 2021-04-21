import axios from 'axios'

async function getProducts(){
    const products=await axios.get('http://localhost:80/api/products')
    const data=products.data;
    console.log(data);

    return(data);
    
}
export {getProducts}


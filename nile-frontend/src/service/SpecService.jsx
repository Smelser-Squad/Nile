import axios from 'axios'

async function getProductSpecsById(productId, specId) {
  const specs=await axios.get('http://localhost:80/api/productspecs/product/${productId}/spec/${specId}')
    .catch(() => {
      return null;
    })
  const data=specs.data;
  
  return(data);
}

export { getProductSpecsById }
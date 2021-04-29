import axios from 'axios'

async function getProductSpecsById(productId, specId) {
  console.log("productId: " + productId);
  console.log("specId: " + specId);
  const specs=await axios.get('http://localhost:80/api/productspecs/product/'+productId+'/spec/'+specId)
    .catch(() => {
      return null;
    })
  console.log("Specs from backend: " + JSON.stringify(specs));
  const data=specs === null ? null : specs.data;
  
  return(data);
}

async function getSpecById(specId) {
  const specs=await axios.get('http://localhost:80/api/specs/'+specId)
    .catch(() => {
      return null;
    })
    const data=specs === null ? null : specs.data;
    
    return(data);
}

export { getProductSpecsById, getSpecById }
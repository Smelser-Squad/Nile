import axios from 'axios'

async function getProductSpecsById(productId, specId) {
  const specs=await axios.get('http://54.82.100.75/api/productspecs/product/'+productId+'/spec/'+specId)
    .catch(() => {
      return null;
    })
  const data=specs === null ? null : specs.data;
  
  return(data);
}

async function getSpecById(specId) {
  const specs=await axios.get('http://54.82.100.75/api/specs/'+specId)
    .catch(() => {
      return null;
    })
    const data=specs === null ? null : specs.data;
    
    return(data);
}

export { getProductSpecsById, getSpecById }
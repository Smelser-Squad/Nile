import axios from 'axios';

async function getProducts() {
    return await axios.get('http://54.82.100.75/api/products')
        .then(res => res.data)
        .catch(err => Promise.reject(err));
}

async function getProduct(productId) {
    return await axios.get(`http://54.82.100.75/api/products/${productId}`)
        .then(res => res.data)
        .catch(err => Promise.reject(err));
}

async function getBrandProducts(brand) {
    return await axios.get(`http://54.82.100.75/api/products/brand/${brand}`)
        .then(res => res.data)
        .catch(err => Promise.reject(err));
}
async function getTypeProducts(type) {
    return await axios.get(`http://54.82.100.75/api/products/type/${type}`)
        .then(res => res.data)
        .catch(err => Promise.reject(err));
}

async function getProductsByCategory(category) {
    return await axios.get(`http://54.82.100.75/api/products/category/${category}`)
        .then(res => res.data)
        .catch(err => Promise.reject(err));
}

export { getProducts, getProduct, getBrandProducts, getTypeProducts, getProductsByCategory };


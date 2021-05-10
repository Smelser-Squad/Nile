import axios from 'axios'

async function getOrders() {
    const charges = await axios.get('http://54.82.100.75/api/charges')
    const data = charges.data;
    return (data);

}


async function getOrderDetailsById(chargeId) {
    const charge = await axios.get(`http://54.82.100.75/api/charge/${chargeId}`)
    const data = charge.data;

    return (data);
}

async function getCartProducts(cartId) {
    const charges = await axios.get(`http://54.82.100.75/api/carts/products/${cartId}`)
    const data = charges.data;
    return (data);

}

export { getOrders, getOrderDetailsById, getCartProducts }

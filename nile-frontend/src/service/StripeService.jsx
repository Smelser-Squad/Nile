import axios from 'axios'

async function getOrders() {
    const charges = await axios.get('http://localhost:80/api/charges')
    const data = charges.data;

    return (data);

}

async function getOrderDetailsById(chargeId) {
    const charge = await axios.get(`http://localhost:80/api/charge/${chargeId}`)
    const data = charge.data;

    return (data);
}
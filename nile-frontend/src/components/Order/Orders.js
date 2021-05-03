import React, { useState } from 'react';
import { getOrders } from '../../service/StripeService';
import './Orders.css';
import SingleOrder from './SingleOrder';

function Orders() {
    const [orders, setOrders] = useState([]);
    const orderList = [];

    if (orders.length === 0) {
        getOrders().then((list) => {
            list.map((item) =>
                orderList.push(item)
            );
            const orders = orderList.map((order) =>
                <SingleOrder
                    key={order.chargeId}
                    chargeId={order.chargeId}
                    token={order.token}
                    amount={order.amount}
                    email={order.email}
                    orderDate={order.orderDate}
                />
            );
            setOrders(orders);
            console.log(orderList)
        }
        );
    }
    return (
        <div className='order'>
            <div className="home_container">
                {orders}
            </div >
        </div>
    )
}

export default Orders;
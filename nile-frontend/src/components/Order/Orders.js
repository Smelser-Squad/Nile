import React, { useState } from 'react';
import { getOrders } from '../../service/StripeService';
import Order from './Order';
import './Orders.css';

function Orders() {
    const [orders, setOrders] = useState([]);

    const orderList = [];

    if (orders.length === 0) {
        getOrders().then((list) => {
            list.map((item) =>
                orderList.push(item)
            );
            const orders = orderList.map((order) =>
                <Order
                    key={order.chargeId}
                    chargeId={order.chargeId}
                    confirmationId={order.confirmationId}
                    amount={order.amount}
                    email={order.email}
                />
            );
            setOrders(orders);
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
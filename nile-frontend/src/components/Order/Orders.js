import React, { useEffect, useState } from 'react';
import { getOrders } from '../../service/StripeService';
import './Orders.css';
import SingleOrder from './SingleOrder';

function Orders() {
    const [orders, setOrders] = useState([]);
    useEffect(() => {
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
                        cart={order.cart}
                    />
                );

                setOrders(orders);
                console.log(orders);
            }
            );
        }

    }, []);

    return (
        <div className='orders'>
            <h1> Your Orders </h1>
            <div className="orders_order" > {orders} </div>
        </div>
    )
}

export default Orders;
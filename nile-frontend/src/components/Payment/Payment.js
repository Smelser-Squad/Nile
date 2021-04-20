import react from 'react';
import CartProduct from '../Cart/CartProduct/CartProduct';
import { useStateValue } from "../../StateProvider";
import { Link } from "react-router-dom";

import './Payment.css';

function Payment() {
    const [{ cart }, dispatch] = useStateValue();

    return (
        <div className='payment'>

            <div className='payment_container'>
                <h1>
                    Checkout(
                        <Link to='checkout'>{cart?.length} items</Link>
                    )
                </h1>
                <div className='payment_section'>

                    <div className='payment_title'>
                        <h3>
                            Delivery Address
                        </h3>
                        <div className='payment_address'>
                            <p>User Email </p>
                            <p> 1234 Talent Drive</p>
                            <p> Washington, D.C.</p>
                        </div>

                    </div>
                </div>

                <div className='payment_section'>
                    <div className='payment_title'>
                        <h3>
                            Review items and delivery
                        </h3>
                    </div>
                    <div className='payment_item'>
                        {
                            cart.map(product => (
                                <CartProduct
                                    productId={product.productId}
                                    name={product.name}
                                    price={product.price}
                                    image={product.image}
                                    rating={product.rating}
                                />
                            ))
                        }
                    </div>

                </div>

                <div className='payment_section'>
                    <div className='payment_title'>
                        <h3>
                            Payment Method
                    </h3>
                    </div>
                    <div className='payment_details'>
                        {/* Stripe */}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Payment;
import React, { useState, useEffect } from 'react';
import CartProduct from '../Cart/CartProduct/CartProduct';
import { useStateValue } from "../../StateProvider";
import { Link } from "react-router-dom";

import './Payment.css';
import { CardElement } from '@stripe/react-stripe-js';
import CurrencyFormat from 'react-currency-format';
import { getCartTotal } from '../../reducer';

function Payment() {
    const [{ cart }] = useStateValue();


    const [disabled, setDisabled] = useState(true);
    const [succeeded, setSucceeded] = useState(false);
    const [processing, setProcessing] = useState("");


    const handleSubmit = async (event) => {



    }
    const handleChange = event => {


    }
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

                        <form onSubmit={handleSubmit}>
                            <CardElement onChange={handleChange} />

                            <div className='payment_priceContainer'>
                                <CurrencyFormat
                                    renderText={(value) => (
                                        <h3> Order Total: {value} </h3>
                                    )}
                                    decimalScale={2}
                                    value={getCartTotal(cart)}
                                    displayType={"text"}
                                    thousandSeperator={true}
                                    prefix={"$"}
                                />
                                <button disabled={processing || disabled || succeeded}>
                                    <span>{processing ? <p> Processing </p> : "Shop Now"} </span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Payment;
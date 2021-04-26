import React from 'react';
import CartProduct from '../Cart/CartProduct/CartProduct';
import { useStateValue } from "../../StateProvider";
import { Link } from "react-router-dom";
import StripeCheckout from "react-stripe-checkout";
import { loadStripe } from '@stripe/stripe-js';


import './Payment.css';
import CurrencyFormat from 'react-currency-format';
import { getCartTotal } from '../../reducer';
import axios from 'axios';
import { Elements, CardElement, useStripe, useElements } from '@stripe/react-stripe-js';


function Payment() {
    const stripe = useStripe();
    const elements = useElements();
    const [{ cart }] = useStateValue();


    async function handleToken(token, addresses) {
        console.log({ token, addresses });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const { error, paymentMethod } = await stripe.createPaymentMethod({
            type: 'card',
            card: elements.getElement(CardElement)
        });
        if (!error) {
            console.log(paymentMethod)
            const { id } = paymentMethod;

            try {

                const { data } = await axios.post("/api/charge", { id, amout: 1099 });
                console.log(data);
            }
            catch (error) {
                console.log(error);
            }
        }
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
                            {/* <p>User Email </p> */}
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
                        </div>

                        <form className="payment_form" onSubmit={handleSubmit}>


                            <CardElement />
                            <button type="submit" disabled={!stripe}>
                                Pay
                            </button>
                        </form>

                    </div>
                </div>




                {/* <div className='payment_section'>
                    <div className='payment_title'>
                        <h3>
                            Payment Method
                    </h3>
                    </div>
                    <div className='payment_details'>
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
                        </div>
                        <StripeCheckout


                            stripeKey="pk_test_4TbuO6qAW2XPuce1Q6ywrGP200NrDZ2233"
                            token={handleToken}
                            amount={getCartTotal * 100}
                            billingAddress
                            shippingAddress
                        />
                    </div>
                </div> */}



            </div>
        </div>
    )
}

export default Payment;
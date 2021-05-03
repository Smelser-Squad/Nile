import React from 'react';
import CartProduct from '../Cart/CartProduct/CartProduct';
import { useStateValue } from "../../StateProvider";
import { Link, useHistory } from "react-router-dom";
import StripeCheckout from "react-stripe-checkout";
import './Payment.css';
import CurrencyFormat from 'react-currency-format';
import { getCartTotal } from '../../reducer';
import axios from 'axios';
import { getCurrentUser } from '../../util/APIUtils';

function Payment() {
    const [{ cart }] = useStateValue();
    const publishableStripeKey = 'pk_test_51IiMSjC3X35blG5onbHeR4PRYxKLDXpSIYunN4jmZKM3Z5lXDrZ5P9v1pS9rzwH4JUokfAnOl3gojKJtd6fFsEKE00CYlgul7y';
    const totalCartPrice = getCartTotal(cart) * 100;
    // const currentuser = getCurrentUser();
    // console.log(currentuser);

    const history = useHistory();
    async function handleToken(token) {
        axios.post('http://localhost:80/api/create-charge', {
            token: token.card.id,
            amount: totalCartPrice,
            email: token.email,

        }).then((response) => {
            history.replace('/orders')

            // alert('Payment success')
        }).catch((error) => {
            alert('Payment failed')
        })

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
                        <StripeCheckout

                            stripeKey={publishableStripeKey}
                            amount={totalCartPrice}
                            billingAddress
                            shippingAddress
                            token={handleToken}
                            currency="USD"
                            label="Pay Now"
                            panelLabel="Pay Now"
                        // email={getCurrentUser}

                        />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Payment;
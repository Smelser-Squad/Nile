import axios from 'axios';
import React from 'react';
import CurrencyFormat from 'react-currency-format';
import { Link, useHistory } from "react-router-dom";
import StripeCheckout from "react-stripe-checkout";
import { getCartTotal } from '../../reducer';
import { useStateValue } from "../../StateProvider";
import CartProduct from '../Cart/CartProduct/CartProduct';
import './Payment.css';

function Payment() {
    const [{ cart }, dispatch] = useStateValue();
    const publishableStripeKey = 'pk_test_51IiMSjC3X35blG5onbHeR4PRYxKLDXpSIYunN4jmZKM3Z5lXDrZ5P9v1pS9rzwH4JUokfAnOl3gojKJtd6fFsEKE00CYlgul7y';
    const totalCartPrice = getCartTotal(cart) * 100;
    const history = useHistory();

    const cartProductId = [];
    console.log(cart)

    function getProductId() {
        cart.forEach((currentValue) => {
            cartProductId.push(currentValue.productId)
            console.log(cartProductId)
            console.log(typeof cartProductId)
            console.log(typeof currentValue)
        })
    }

    getProductId();
    console.log(cartProductId)
    async function handleToken(token) {
        axios.post('http://54.82.100.75/api/create-charge', {
            // cartProductId: cartProductId,
            token: token.card.id,
            amount: totalCartPrice,
            email: token.email,
            cartProductId: cartProductId
        }


        ).then((response) => {

            dispatch({
                type: 'EMPTY_CART'
            })
            history.replace('/orders')
        }).catch((error) => {
            alert('Payment failed')
        })

    }

    return (<div className='payment' >

        <div className='payment_container' >
            <h1 >
                Checkout( <Link to='checkout' > {cart.length}items </Link>
        ) </h1>
            <div className='payment_section' >
                <div className='payment_title' >
                    <h3 >
                        Delivery Address
             </h3>
                    <div className='payment_address' > { /* <p>User Email </p> */}
                        <p> 1234 Talent Drive </p>
                        <p> Washington, D.C. </p>
                    </div>
                </div>
            </div>
            <div className='payment_section' >
                <div className='payment_title' >
                    <h3 >
                        Review items and delivery </h3> </div>
                <div className='payment_item' > {
                    cart.map(product => (<
                        CartProduct productId={product.productId}
                        name={product.name}
                        price={product.price}
                        image={product.image}
                        rating={product.rating}
                        
                    />
                    ))
                } </div>
            </div>

            <div className='payment_section' >
                <div className='payment_title' >
                    <h3 >
                        Payment Method </h3> </div> <div className='payment_details' >
                    <div className='payment_priceContainer' >
                        <CurrencyFormat renderText={
                            (value) => (<
                                h4 > Order Total({ cart.length}
                    items): { value} </h4>
                            )
                        }
                            decimalScale={2}
                            value={getCartTotal(cart)}
                            displayType={"text"}
                            thousandSeperator={true}
                            prefix={"$"}
                        /> </div> <StripeCheckout stripeKey={publishableStripeKey}
                            amount={totalCartPrice}
                            billingAddress shippingAddress
                            token={handleToken}
                            currency="USD"
                            label="Pay Now"
                            panelLabel="Pay Now" />
                </div>
            </div>
        </div>
    </div>
    )
}

export default Payment;
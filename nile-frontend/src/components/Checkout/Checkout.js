import React from 'react'
import './Checkout.css'
import Subtotal from '../Cart/Subtotal/Subtotal'

function Checkout() {
    return (
        <div className='checkout' >
            <div className="checkout_left">


                <h2 className="checkout_title">
                    Shopping Cart
                </h2>

                {/* TODO: show added items to the cart */}
                {/* Product*/}

            </div>

            <div className="checkout_right">
                <Subtotal />
            </div>
        </div>
    )
}


export default Checkout;

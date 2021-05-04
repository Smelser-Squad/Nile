import React from 'react'
import { useStateValue } from '../../StateProvider'
import CartProduct from '../Cart/CartProduct/CartProduct'
import Subtotal from '../Cart/Subtotal/Subtotal'
import './Checkout.css'

function Checkout() {
    const [{ cart }] = useStateValue();
    return (
        <div className='checkout' >
            <div className="checkout_left">


                <h2 className="checkout_title">
                    Shopping Cart
                </h2>
                {cart.map(product => (
                    <CartProduct
                        productId={product.productId}
                        name={product.name}
                        image={product.image}
                        price={product.price}
                        rating={product.rating}
                    />

                ))}

            </div>

            <div className="checkout_right">
                <Subtotal />
            </div>
        </div>
    )
}


export default Checkout;

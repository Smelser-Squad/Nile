import React, { useState } from 'react'
import './Checkout.css'
import Subtotal from '../Cart/Subtotal/Subtotal'
import CartProduct from '../Cart/CartProduct/CartProduct'
import { useStateValue } from '../../StateProvider'
import './Checkout.css'

function Checkout() {
    const [{ cart }] = useStateValue();
    const [SingleProductPrice, setProductPrice] = useState('');

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
                <Subtotal/>
            </div>
        </div>
    )
}


export default Checkout;

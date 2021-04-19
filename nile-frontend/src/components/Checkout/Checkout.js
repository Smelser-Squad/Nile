import React from 'react'
import './Checkout.css'
import Subtotal from '../Cart/Subtotal/Subtotal'
import CartProduct from '../Cart/CartProduct/CartProduct'
import { useStateValue } from '../../StateProvider'

function Checkout() {
    const [{ cart }] = useStateValue();
    return (
        <div className='checkout' >
            <div className="checkout_left">


                <h2 className="checkout_title">
                    Shopping Cart
                </h2>

                {/* <CartProduct

                    productId="12321341"
                    name="Gift for Mom"
                    price={80.99}
                    rating={4}
                    image="https://i.etsystatic.com/5584756/r/il/7d61bd/2957120029/il_1588xN.2957120029_kefx.jpg"
                /> */}

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

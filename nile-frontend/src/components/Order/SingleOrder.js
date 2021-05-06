import CurrencyFormat from "react-currency-format";
import './SingleOrder.css';
import CartProducts from './CartProducts'
import { useState, useEffect } from "react";
import { getCartProducts } from '../../service/StripeService';

function SingleOrder({ token, amount, email, orderDate, cart }) {


    const [cart_products, setCartProducts] = useState([]);
    useEffect(() => {
        const cartProductList = [];

        if (cartProductList.length === 0) {
            getCartProducts(cart.cartId).then((list) => {
                console.log(list)
                list.map((item) =>
                    cartProductList.push(item)
                );
                const cart_products = cartProductList.map((cart_product) =>
                    <CartProducts
                        photos={cart_product.photos[0].imageSrc}
                        name={cart_product.name}
                        price={cart_product.price}

                    />
                );

                setCartProducts(cart_products);
                console.log(cart_products);

            }
            );
        }

    }, []);



    return (
        <div className='order' >
            <h3 > Order Placed </h3>
            <p > {orderDate} </p>
            <p > {email} </p>
            <p className="order_id" >

                <small > ORDER# {token} </small>
            </p>

            <div className='cart_products'>
                <div>
                    {cart_products}
                </div>
            </div>


            <CurrencyFormat renderText={
                (value) => (
                    <h3 className="order_total" > Order Total: { value} </h3>
                )
            }
                decimalScale={2}
                value={amount / 100}
                displayType={"text"}
                thousandSeparator={true}
                prefix={"$"}
            />
        </div>
    )
}


export default SingleOrder;
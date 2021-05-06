import React, { useState } from 'react';
import ReactStars from "react-rating-stars-component";
import { getCartTotal } from '../../../reducer';
import { useStateValue } from '../../../StateProvider';
import '../CartProduct/CartProduct.css';


function CartProduct({ productId, image, name, price, rating, key, setProductPrice, quantity }) {
    const [{ cart }, dispatch] = useStateValue();

    const [setQuantity] = useState(quantity);
    const [cartPrice, setTotalPrice] = useState(price);

    // console.log(quantity)

    const removeFromCart = () => {
        dispatch({
            type: 'REMOVE_FROM_CART',
            productId: productId,
            key: productId

        })
    };

    function incrementQuantity() {
        const cartPrice = getCartTotal(cart);
        setTotalPrice(price + cartPrice);
        setProductPrice(price * (quantity + 1));
        setQuantity(quantity + 1);

    }

    function decrementQuantity() {
        // setQuantity(prevQuantity => prevQuantity - 1);
        setProductPrice(price * (quantity - 1));
        setQuantity(quantity - 1);
    }



    return (
        <div className='cartProduct'>
            <img className='cartProduct_image' alt="" src={image} />

            <div className='cartProduct_info'>
                <p className='cartProduct_name'>{name} </p>
                <p className='cartProduct_price'>
                    <small>$</small>
                    <strong>{price}</strong>
                </p>

                <div className='cartProduct_rating'>
                    <ReactStars
                        count={5}
                        edit={false}
                        value={rating}
                        activeColor="#FFA41C"
                        size={15}
                    />

                </div>

                <div className="quantity_container">
                    <div className="quantity">Quantity {quantity} </div>
                    <div className="buttons">
                        <button className="btn" onClick={incrementQuantity}>+</button>
                        <button className="btn" onClick={decrementQuantity}>-</button>
                    </div>


                </div>
                <button onClick={removeFromCart}> Remove from Cart</button>


            </div>
        </div>



    )
};

export default CartProduct;
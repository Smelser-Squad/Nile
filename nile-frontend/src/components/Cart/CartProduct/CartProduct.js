import React from 'react';
import '../CartProduct/CartProduct.css';
import ReactStars from "react-rating-stars-component";
import { useStateValue } from '../../../StateProvider';

function CartProduct({ productId, image, name, price, rating }) {
    const [{ cart }, dispatch] = useStateValue();
    const removeFromCart = () => {
        dispatch({
            type: 'REMOVE_FROM_CART',
            productId: productId,
        })
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
                <button onClick={removeFromCart}> Remove from Cart</button>


            </div>
        </div>



    )
};

export default CartProduct;
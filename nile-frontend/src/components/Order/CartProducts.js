import './CartProducts.css';
function CartProducts({ photos, name, price }) {
    return (
        <div>
            <div className="cart_products_left">
                <img className='cartProduct_image' alt="" src={photos} />
            </div>
            <div className='cart_products_right'>
                <p className="right_name">{name} </p>
                <p className="right_price">
                    <small>$</small>
                    <strong>{price}</strong>
                </p>
            </div>
        </div>
    )
}
export default CartProducts;
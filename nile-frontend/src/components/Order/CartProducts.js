function CartProducts({ photos, name, price }) {

    return (
        <div className='cartProducts'>
            <img className='cartProduct_image' alt="" src={photos} />

            <div className='cartProducts_info'>
                <p>{name} </p>
                <p>
                    <small>$</small>
                    <strong>{price}</strong>
                </p>
            </div>
        </div>
    )

}

export default CartProducts;
import './SingleOrder.css ';

function SingleOrder({ chargeId, token, amount, email, orderDate }) {
    amount = amount / 100;

    return (
        <div className='order'>

            <p className="order_token" >Confirmation: {token}</p>
            <p className="order_amount">Order Total: {amount} </p>
            <p className="order_email">User Information: {email}</p>
            <p className="order_date">Order Placed: {orderDate}</p>





        </div>
    );
}

export default SingleOrder;
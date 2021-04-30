import './Order.css ';

function Order({ chargeId, confirmationId, amount, email }) {
    amount = amount / 100;

    return (
        <div className='order'>

            <p className="order_token" >{confirmationId}</p>
            <p className="order_amount">{amount} </p>
            <p className="order_email">{email}</p>




        </div>
    );
}

export default Order;
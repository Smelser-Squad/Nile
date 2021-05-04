import CurrencyFormat from "react-currency-format";
import './SingleOrder.css';

function SingleOrder({ token, amount, email, orderDate, cart }) {

    return ( <
        div className = 'order' >
        <
        h3 > Order Placed < /h3> <
        p > { orderDate } < /p> <
        p > { email } < /p> <
        p className = "order_id" >
        <
        small > ORDER# { token } < /small> <
        /p> <
        ul > { cart.cartProducts } <
        /ul> <
        CurrencyFormat renderText = {
            (value) => ( <
                h3 className = "order_total" > Order Total: { value } < /h3>
            )
        }
        decimalScale = { 2 }
        value = { amount / 100 }
        displayType = { "text" }
        thousandSeparator = { true }
        prefix = { "$" }
        /> <
        /div>
    )
}

export default SingleOrder;
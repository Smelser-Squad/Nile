import React from 'react'
import './Subtotal.css'
import CurrencyFormat from "react-currency-format";
import { useStateValue } from "../.././../StateProvider";
import { getCartTotal } from '../../../reducer';
import { useHistory } from 'react-router';

function Subtotal({quan}) {
    const [{ cart }] = useStateValue();
    const history = useHistory();

function getTotal(){
    return quan;
}
    return (
        <div className='subtotal' >

            <CurrencyFormat
                renderText={(value) => (
                    <>
                        <p>
                            Subtotal ({cart.length} items) <strong>{value}</strong>
                        </p>
                        <small className="subtotal_gift">
                            <input type="checkbox" />
                            This order contains a gift
                        </small>
                    </>
                )}
                decimalScale={2}
                value={getCartTotal(cart)} //get the total price of all the products //TODO: function getTotal 
                displayType={"text"}
                thousandSeperator={true}
                prefix={"$"}
            />
            <button onClick={e => history.push('/payment')}>Proceed to Checkout</button>
        </div>
    )
}

export default Subtotal;

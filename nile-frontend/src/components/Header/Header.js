import React from 'react'
import './Header.css'
import SearchIcon from '@material-ui/icons/Search';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import { Link } from "react-router-dom";
import { useStateValue } from "../../StateProvider";
function Header() {
    const [{ cart }] = useStateValue();
    return (
        <div className='nav_header' >
            <Link to="/">
                <img className="header_logo" alt="logo"
                    src="https://cdn10.bigcommerce.com/s-yhxhf/products/20469/images/75383/STMTD028_4x4__62255.1535837059.1080.1080.jpg?c=2" />

            </Link>


            <div className="header_search" >
                <input className="hearder_searchInput" type="text" />
                <SearchIcon className="hearder_searchIcon" />

            </div>

            <div className="header_nav" >

                <Link to="/signin">
                    <div className="header_option" >
                        <span className="header_optionOne" > Hello Guest </span>
                        <span className="header_optionTwo" > Sign In </span>
                    </div >
                </Link>

                <Link to="/Orders">

                    <div className="header_option" >
                        <span className="header_optionOne" > Returns </span>
                        <span className="header_optionTwo" > & Orders </span>
                    </div >
                </Link>
                <div className="header_option" >
                    <span className="header_optionOne" > Your </span>
                    <span className="header_optionTwo" > Prime </span>
                </div >

                <Link to="/checkout">
                    <div class="header_cart">
                        <ShoppingCartIcon />
                        <span className="header_optionTwo header_cartCount" > {cart?.length} </span>
                    </div>
                </Link>

            </div>
        </div >
    )
}

export default Header

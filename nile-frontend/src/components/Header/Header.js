import SearchIcon from '@material-ui/icons/Search';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import React from 'react';
import { Link } from "react-router-dom";
import { useStateValue } from "../../StateProvider";
import UserMenu from '../UserMenu';
import './Header.css';
import { useState } from 'react';
import { filterProductByName } from '../../reducer';

function Header() {
    const [{ cart, products }, dispatch] = useStateValue();
    const [search, setSearch] = useState("");


    const handleClick = () => {
        const filtername = filterProductByName(search, products);
        dispatch({
            type: "FILTER_PRODUCT",
            filteredProducts: filtername
        })
    }



    const handleChange = (event) => {
        setSearch(event.target.value.toLowerCase());
    }


    return (
        <div className='nav_header' >
            <Link onClick={() => window.location.href = "/"}>
                <img className="header_logo" alt="logo"
                    src="https://cdn10.bigcommerce.com/s-yhxhf/products/20469/images/75383/STMTD028_4x4__62255.1535837059.1080.1080.jpg?c=2" />

            </Link>
            <div className="header_search" >
                <input className="hearder_searchInput" type="text" onChange={handleChange} />
                <SearchIcon className="hearder_searchIcon" onClick={handleClick} />
            </div>
            <div className="header_nav" >
                <UserMenu />
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

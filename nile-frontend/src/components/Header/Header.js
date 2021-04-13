import React from 'react'
import './Header.css'
import SearchIcon from '@material-ui/icons/Search';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
function Header() {
    return (
        <div className='nav_header' >

            <img className="header_logo"
                src="https://cdn10.bigcommerce.com/s-yhxhf/products/20469/images/75383/STMTD028_4x4__62255.1535837059.1080.1080.jpg?c=2" />

            <div className="header_search" >
                <input className="hearder_searchInput" type="text" />
                <SearchIcon className="hearder_searchIcon" />

            </div>

            <div className="header_nav" >

                <div className="header_option" >
                    <span className="header_optionOne" > Hello Guest </span>
                    <span className="header_optionTwo" > Sign In </span>
                </div >

                <div className="header_option" >
                    <span className="header_optionOne" > Returns </span>
                    <span className="header_optionTwo" > & Orders </span>
                </div >

                <div className="header_option" >
                    <span className="header_optionOne" > Your </span>
                    <span className="header_optionTwo" > Prime </span>
                </div >

                <div class="header_cart">
                    <ShoppingCartIcon />
                    <span className="header_optionTwo header_cartCount" > 0 </span>

                </div>
            </div>
        </div >
    )
}

export default Header

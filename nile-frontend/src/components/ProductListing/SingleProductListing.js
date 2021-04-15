import './SingleProductListing.css';
import React from 'react'
import MoreProducts from '../MoreProducts/MoreProducts.js';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';

function SingleProductListing() {
    return(
        <div className="SingleProductListing">
            <h2>PRODUCT NAME HERE</h2>
            <h2>PRODUCT DESCRIPTION HERE</h2>
            <ProductPhotos/>
            <MoreProducts/>

        </div>
    )
}

export default SingleProductListing
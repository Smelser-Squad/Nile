import './SingleProductListing.css';
import React from 'react'
import MoreProducts from '../MoreProducts/MoreProducts.js';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';
import QuestionAnswer from '../QA/QA.jsx';

function SingleProductListing() {
    return(
        <div className="SingleProductListing">
            <h2>PRODUCT NAME HERE</h2>
            <h2>PRODUCT DESCRIPTION HERE</h2>
            <ProductPhotos/>
            <MoreProducts/>
            <QuestionAnswer />

        </div>
    )
}

export default SingleProductListing
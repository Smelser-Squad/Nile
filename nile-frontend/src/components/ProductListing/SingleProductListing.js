import './SingleProductListing.css';

import React from 'react'
import MoreProducts from '../MoreProducts/MoreProducts.js';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';
import QuestionAnswer from '../QA/QA.jsx';
import Reviews from '../Reviews/Reviews.js';

function SingleProductListing() {
    return(
        <div className="SingleProductListing">
            <h2>WORLD'S #1 T SHIRT!!!!!!</h2>
            <h3>THIS T SHIRT IS THE BEST SHIRT EVER, NO REGRETS, BUY NOW, YOURS FOREVER, NO STAINS GUARANTEE OR YOUR MONEY BACK!</h3>
            <ProductPhotos/>
            <Reviews/>
            <MoreProducts/>
            <QuestionAnswer />

        </div>
    )
}

export default SingleProductListing
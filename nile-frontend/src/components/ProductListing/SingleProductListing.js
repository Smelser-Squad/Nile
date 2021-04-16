import './SingleProductListing.css';

import React from 'react'
import MoreProducts from '../MoreProducts/MoreProducts.js';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';
<<<<<<< HEAD
import QuestionAnswer from '../QA/QA.jsx';
=======
import Reviews from '../Reviews/Reviews.js';
>>>>>>> 1a44d21fa84ebe550451638dd2c3c2475bd02e70

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
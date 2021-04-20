import './SingleProductListing.css';

import React from 'react'
import MoreProducts from '../MoreProducts/MoreProducts';
import ProductPhotos from '../ProductPhotos/ProductPhotos.jsx';
import Reviews from '../Reviews/Reviews.js';
import { ProductColorSelector } from '../ProductColorSelector/ProductColorSelector';
import ReviewSummary from '../ReviewSummary/ReviewSummary';
import Tag from '../ReviewTag/Tag';

function SingleProductListing() {
    return(
        <div className="SingleProductListing">
            <h2>WORLD'S #1 T SHIRT!!!!!!</h2>
            <h3>THIS T SHIRT IS THE BEST SHIRT EVER, NO REGRETS, BUY NOW, YOURS FOREVER, NO STAINS GUARANTEE OR YOUR MONEY BACK!</h3>
            <ProductPhotos/>
            <ProductColorSelector/>
            <MoreProducts/>
            <ReviewSummary />
            <Reviews/>
            <Tag/>

        </div>
    )
}

export default SingleProductListing
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getReviewPhotosForProduct } from '../../../service/PhotoService';
import PhotoTile from './PhotoTile';

import "./ReviewPhotos.css"

export default function ReviewPhotos() {
    const { productId } = useParams();
    const [photos, setPhotos] = useState([]);

    useEffect(() => {
        getReviewPhotosForProduct(productId)
            .then(data => setPhotos(data))
            .catch(err => console.log(err));
    }, []);

    let photoElements = photos.map(p => (<PhotoTile photo={p} />))

    return (
        <div className="review-photo-section">
            <span>Reviews With Images</span>
            <div className="review-photo-container">
                {photoElements}
            </div>
        </div>
    )
}

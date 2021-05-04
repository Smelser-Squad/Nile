import React from 'react'

function PhotoTile({ photo }) {
    return (
        <div className="review-photo-tile">
            <img className="review-photo" src={photo.imageSrc} alt="Review" />
        </div>
    );
}

export default PhotoTile

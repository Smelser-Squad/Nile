import React from 'react'

function PhotoTile({ photo, classes }) {
    return (
        <div className={classes.review_photo_tile}>
            <img className={classes.review_photo} src={photo.imageSrc} alt="Review" />
        </div>
    );
}

export default PhotoTile

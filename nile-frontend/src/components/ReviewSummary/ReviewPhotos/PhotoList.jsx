import React from 'react';
import PhotoTile from './PhotoTile';

function PhotoList({ photos, classes }) {
    let photoElements = photos.map(p => (<PhotoTile photo={p} classes={classes}/>));

    return (
        <div className={classes.review_photo_container}>
            {photoElements}
        </div>
    )
}

export default PhotoList

import { Link, makeStyles, Modal } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getReviewPhotosForProduct } from '../../../service/PhotoService';
import PhotoList from './PhotoList';

const useStyles = makeStyles((theme) => ({
    review_photo_container: {
        width: "80%",
        display: "flex",
        flexDirection: "row",
        justifycontent: "start",
    },
    review_photo_tile: {
        maxWidth: "23%",
        marginRight: "2%",
    },
    review_photo: {
        width: "100%",
        height: "auto",
    },
    review_photo_modal: {
        backgroundColor: "white",
        position: "absolute",
        top: "50%",
        left: "50%",
        transform: `translate(-50%, -50%)`,
        width: "600px",
        height: "400px",
        padding: "3%"
    },
    modal_header: {
        position: "absolute",
        top: 0,
        left: 0,
        backgroundColor: "#dddddd",
        width: "100%",
        height: "5%"
    }
}));

export default function ReviewPhotos() {
    const classes = useStyles();
    const { productId } = useParams();
    const [photos, setPhotos] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);

    const handleOpen = () => setModalOpen(true);
    const handleClose = () => setModalOpen(false);

    useEffect(() => {
        getReviewPhotosForProduct(productId)
            .then(data => setPhotos(data))
            .catch(err => console.log(err));
    }, []);

    return (
        <div className="review_photo_section">
            <h2>Reviews With Images</h2>
            <PhotoList photos={photos.slice(0, 4)} classes={classes} />
            <Link component="button" variant="body2" onClick={handleOpen}>
                See all Customer Images
            </Link>
            <Modal open={modalOpen} onClose={handleClose}>
                <div className={classes.review_photo_modal}>
                    <div className={classes.modal_header}>
                        <span
                            style={{ position: "absolute", right: 0, paddingRight: "2%", cursor: "pointer" }}
                            onClick={handleClose}
                        >
                            X
                        </span>
                    </div>
                    <PhotoList photos={photos} classes={classes} />
                </div>
            </Modal>
        </div>
    )
}

import { useState } from 'react';
import './ProductPhotos.css';
import { getPhotos } from '../../service/PhotoService'


function ProductPhotos() {

    const [data, setData] = useState([]);
    const [photoSrc, setPhoto] = useState([])
    const [modalSrc, setModal] = useState([])

    const PhotoList = [];

    if (data.length === 0) {
        getPhotos().then((list) => {
            list.map((item) =>
                PhotoList.push(item)
            );
            const data = PhotoList.map((photo) =>
                <li className="listedPhoto"
                    onClick={() => updatePhoto(photo.imageSrc)}>
                    <img
                        id="altPhoto"
                        alt=""
                        src={photo.imageSrc} >
                    </img>
                </li>

            );
            const photoSrc = PhotoList.map((photo) =>
                <img id="productImg"
                    onMouseEnter={displayModal}
                    onMouseLeave={hideModal}
                    alt="product" src={photo.imageSrc}></img>)
            const modalSrc = PhotoList.map((photo) =>
                <img id="modalImage"
                    alt=""
                    src={photo.imageSrc}></img>)
            setData(data);
            setPhoto(photoSrc);
            setModal(modalSrc);
        }
        );
    }




    return (
        console.log(photoSrc),
        <div className="PhotoContainer">
            <ul className="altPhotoList">
                {data}
            </ul>
            {photoSrc[0]}

            <div id="myModal" class="modal">
                {modalSrc[0]}
            </div>

        </div>

    );
    function displayModal() {
        let modal = document.getElementById("myModal");
        modal.style.display = "block";
    }

    function hideModal() {
        let modal = document.getElementById("myModal");
        modal.style.display = "none";
    }

    function updatePhoto(newSrc) {
        document.getElementById("productImg").src = newSrc;
        document.getElementById("modalImage").src = newSrc;
    }

}




ProductPhotos.defaultProps = {

}


export default ProductPhotos;

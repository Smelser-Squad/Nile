import { useState } from 'react';
import './ProductPhotos.css';
import {getPhotos} from '../../service/PhotoService'


function ProductPhotos() {

    const [data, setData] = useState([]);
    const [photoSrc, setPhoto] = useState([])
    const [modalSrc, setModal] = useState([])

    const PhotoList=[];

    if(data.length===0){
        getPhotos().then((list)=>
        {
            list.map((item)=>
            PhotoList.push(item)
            );
            const data=PhotoList.map((photo)=>
                <li className="listedPhoto" 
                onClick={() => updatePhoto(photo.imageSrc)}>
                    <img  
                    id="altPhoto" 
                    alt="" 
                    src={photo.imageSrc} >
                        </img>
                </li>
                
                );
            const photoSrc=PhotoList.map((photo)=>
                <img id="productImg"
                onMouseEnter={displayModal}
                onMouseLeave={hideModal}
                alt="product"src={photo.imageSrc}></img>)
            const modalSrc = PhotoList.map((photo)=>
                <img id="modalImage"
                alt=""
                src={photo.imageSrc}></img>)
        setData(data);
        setPhoto(photoSrc);
        setModal(modalSrc);
    }
    );
}
    

    

    return(
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
    id: 1,
    mainImgSrc: "https://media.reformclothing.com/design_lab/ysd-products/C1005_T-Shirt/C1005_T_Shirt_Front_A_800.png",
    imgSrc: "https://media.reformclothing.com/design_lab/ysd-products/C1005_T-Shirt/C1005_T_Shirt_Front_A_800.png",
    imgSrc2: "https://media.gq.com/photos/5e839e80cb478c00085df9a2/master/w_2000,h_1333,c_limit/Buck-Mason-white-slub-curved-hem-tee.jpg",
    imgSrc3: "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/white-t-shirt-1584031440.jpg?crop=1.00xw:1.00xh;0,0&resize=1200:*",
    imgSrc4: "https://image.shutterstock.com/image-vector/white-t-shirt-vector-illustration-260nw-1660504495.jpg",
    imgSrc5: "https://cdn.thewirecutter.com/wp-content/media/2021/01/whitetshirts-2048px-0311.jpg?auto=webp&quality=75&width=1024"
}


export default ProductPhotos;

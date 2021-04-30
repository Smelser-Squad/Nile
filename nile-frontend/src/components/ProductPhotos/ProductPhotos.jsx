import { useState } from 'react';
import './ProductPhotos.css';
import {getPhotos} from '../../service/PhotoService'
import { useParams } from 'react-router';
import  {SideBySideMagnifier} from "react-image-magnifiers";

export function ProductPhotos({color}) {
    const [data, setData] = useState([]);
    const [magnifier, setMag] = useState([])
    const { productId } = useParams();
    const [currColor, setCurrColor] = useState(color);
    const PhotoList = [];
    if(data.length===0 || currColor !== color){
        getPhotos(productId, color).then((list)=>
        {
            list.map((item)=>
            PhotoList.push(item),
            
            );
            const data = PhotoList.map((photo) =>
                <li className="listedPhoto"
                    onClick={() => updatePhoto(photo.imageSrc)}>
                    <img
                        className="altPhoto"
                        id= {photo.photoId}
                        alt=""
                        src={photo.imageSrc} >
                    </img>
                </li>
            );
        
            const magnifier = PhotoList.map((photo) =>
                <SideBySideMagnifier className="mag" style={{ height: "500px", width: "500px", display: "inline-block" }} imageSrc={photo.imageSrc} fillAvailableSpace={false}/>)
            setData(data);
            setMag(magnifier);
            setCurrColor(color);
        }
        );
       
    }
    
    return(
        <div className="PhotoContainer">
            <div>
                <ul className="altPhotoList">
                {data}
                </ul>
                {magnifier[0]}
            </div>
        
        </div>
    
    );
    function updatePhoto(newSrc) {
       document.getElementsByClassName("mag")[0].getElementsByTagName("img")[0].setAttribute("src", newSrc);
       document.getElementsByClassName("mag")[0].getElementsByTagName("img")[1].setAttribute("src", newSrc);
    }
}
export default ProductPhotos;
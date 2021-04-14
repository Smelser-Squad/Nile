import './ProductPhotos.css';

function ProductPhotos(props) {

    return(
        <div className="PhotoContainer">
            <img id="myImg" src={props.imgSrc} 

                onMouseEnter={displayModal}
                onMouseLeave={hideModal}
                alt="product">
            </img>
            
            <div id="myModal" class="modal">
                <img class="modal-content" id="img01" alt="" src={props.imgSrc}></img>    
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
    
    }


  

ProductPhotos.defaultProps = {
    imgSrc: "https://media.reformclothing.com/design_lab/ysd-products/C1005_T-Shirt/C1005_T_Shirt_Front_A_800.png"
}

export default ProductPhotos;

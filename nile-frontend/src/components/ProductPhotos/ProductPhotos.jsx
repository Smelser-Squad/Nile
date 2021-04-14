function ProductPhotos() {
    
    
    return(
        <div>
            <img id="myImg" src="https://media.reformclothing.com/design_lab/ysd-products/C1005_T-Shirt/C1005_T_Shirt_Front_A_800.png" onmouseover="displayModal"></img>
            <div id="myModal" class="modal">
                <img class="modal-content" id="img01"></img>    
            </div>
        </div>
    );
    let modal = document.getElementById("myModal");
    let img = document.getElementById('myImg');
    let modalImg = document.getElementById("img01");
    img.onClick = function() {
        modal.style.display = "block";
        modalImg.src = this.src;

    

    }
}

export default ProductPhotos;
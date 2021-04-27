import {useState} from 'react'
import { useParams } from 'react-router-dom'
import { getBrandProducts } from '../../service/ProductService';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";

import './BrandProducts.css'

function BrandProducts(){

    const [Product, setProduct] = useState([]);
    const{brand}=useParams();
    const ProductList = [];

    if (Product.length === 0) {
        getBrandProducts(brand).then((list) => {
            console.log(list)
            list.map((item) =>
                ProductList.push(item)
            );
            const cards = ProductList.map((product) =>
            <div className="BrandProduct">
            <Link to={`/singleProductListing/${product.productId}`}>{product.name} | {product.photos[0].color}</Link>
            <img id="product-image" src={product.photos[0].imageSrc}></img>
            <div className="BrandProduct_info">
            <h2>${product.price}</h2>
            <div class="row">
            <ReactStars
                    count={5}
                    edit={false}
                    isHalf={true}
                    value={calcRating(product)}
                    activeColor="#FFA41C"
                    size={15}
                />
          
            <p id="review-count">{product.reviews.length}</p>
            </div>
            </div>
        </div>
            );
            setProduct(cards);
        }
        );
    }
    function calcRating(product)  {
        let sum = 0;
        for(let i = 0; i < product.reviews.length; i++) {
            sum += product.reviews[i].rating;
        }
        const avgRating = sum / product.reviews.length;
        return avgRating;
    }
   
return(
    <div>
    {Product}
    </div>
)
}
export default BrandProducts;
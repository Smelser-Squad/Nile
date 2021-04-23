import './Product.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";

function PrimeLogo(props) {
    const primeEligible = props.primeEligible;
    if(primeEligible) {
        return (
            <Link to={`/singleProductListing/${props.productId}`}>
                <img id="prime-img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"></img>
            </Link>
        );
    }
    else {
        return <div></div>
    }
}

function Product({ productId, image, name, price, avgRating, reviewCount, primeEligible}) {
    return (
        <div class="products-container">
            <div class="row">
                <Link to={`/singleProductListing/${productId}`}>
                    <img class="image" id="product-image" src={image} alt="product_image"></img>
                </Link>
            </div>
            <div class="row">
                <Link to={`/singleProductListing/${productId}`} style={{ textDecoration: 'none' }}>
                    <p id="product-name">{name}</p>
                </Link>
            </div>
            <div class="row">
                <ReactStars
                    count={5}
                    edit={false}
                    isHalf={true}
                    value={avgRating}
                    activeColor="#FFA41C"
                    size={15}
                />
                <Link to={`/singleProductListing/${productId}`} style={{ textDecoration: 'none' }}>
                    <p id="review-count">{reviewCount}</p>
                </Link>
            </div>
            <div class="row">
                <Link to={`/singleProductListing/${productId}`} style={{ textDecoration: 'none' }}>
                    <p id="price-tag">${price}</p>
                </Link>
                <PrimeLogo primeEligible={primeEligible} productId={productId}/>
            </div>
        </div>
    );
}
export default Product;
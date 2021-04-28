import './Product.css';
import ReactStars from "react-rating-stars-component";

function PrimeLogo(props) {
    const primeEligible = props.primeEligible;
    if (primeEligible) {
        return (
            <img id="prime-img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"
                onClick={() => { window.location.href = `/singleProductListing/${props.productId}` }}

            ></img>

        );
    }
    else {
        return <div></div>
    }
}

function Product({ productId, image, name, price, avgRating, reviewCount, primeEligible }) {
    return (
        <div class="products-container">
            <div class="row">
                <img class="image" id="single-product-image" onClick={() => { window.location.href = `/singleProductListing/${productId}` }} src={image}></img>
            </div>
            <div class="body">
                <div class="row">
                    <p id="product-name" onClick={() => { window.location.href = `/singleProductListing/${productId}` }} >{name}</p>
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
                    <p id="review-count" onClick={() => { window.location.href = `/singleProductListing/${productId}` }} >{reviewCount}</p>
                </div>
                <div class="row">

                    <p id="price-tag" onClick={() => { window.location.href = `/singleProductListing/${productId}` }}>${price}
                    </p>
                    <PrimeLogo primeEligible={primeEligible} productId={productId} />
                </div>
            </div>
        </div>
    );
}
export default Product;
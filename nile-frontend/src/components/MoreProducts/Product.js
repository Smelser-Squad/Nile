import './Product.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";

function Product() {
    return(
        <div class="products-container">
                <div class="row">
                    <Link to="/">
                        <img class="image" id="product-image" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdna.lystit.com%2Fphotos%2Fmacys%2F6f2f2c3b%2Fchampion-Black-Vapor-Select-Training-Pants.jpeg&f=1&nofb=1" alt="product_image"></img>
                    </Link>
                </div>

                <div class="row">
                    <a id="product-name" href="#productPage">Product name here</a>
                </div>

                <div class="row">
                    <ReactStars
                        count={5}
                        edit={false}
                        value={4}
                        activeColor="#FFA41C"
                        size="40px"
                    />
                    <a id="review-count" href="#productPage">10</a>
                </div>

                <div class="row">
                    <a id="price-tag" href="#productPage">$10.00</a>

                    <Link to="/">
                        <img id="prime-img" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"></img>
                    </Link>
                </div>

            </div>
    );
}

export default Product;
import './Product.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";

function Product() {
    return(
        <div class="products-container">
                <div class="row">
                    <Link to="/singleProductListing">
                        <img class="image" id="product-image" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdna.lystit.com%2Fphotos%2Fmacys%2F6f2f2c3b%2Fchampion-Black-Vapor-Select-Training-Pants.jpeg&f=1&nofb=1" alt="product_image"></img>
                    </Link>
                </div>

                <div class="row">
                    <Link to="/singleProductListing">
                        <p id="product-name">Product name here</p>
                    </Link>
                </div>

                <div class="row">
                    <ReactStars
                        count={5}
                        edit={false}
                        value={4}
                        activeColor="#FFA41C"
                        size={15}
                    />

                    <Link to="/singleProductListing">
                        <p id="review-count">10</p>
                    </Link>
                </div>

                <div class="row">
                    <Link to="/singleProductListing">
                        <p id="price-tag">$10.00</p>
                    </Link>

                    <Link to="/singleProductListing">
                        <img id="prime-img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"></img>
                    </Link>
                </div>

            </div>
    );
}

export default Product;
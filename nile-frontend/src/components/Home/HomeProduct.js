import './HomeProduct.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";

function HomeProduct() {
    return (
        <div className="product">
            <div className="product_info"></div>
            <Link to="/singleProductListing">

                <p id="product_name">Product name here</p>
            </Link>
            <Link to="/singleProductListing">
                <img class="image" id="product-image" src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdna.lystit.com%2Fphotos%2Fmacys%2F6f2f2c3b%2Fchampion-Black-Vapor-Select-Training-Pants.jpeg&f=1&nofb=1" alt="product_image"></img>
            </Link>

            <Link to="/singleProductListing">
                <p id="price_tag">
                    <small> $ </small>
                    <strong>10.00</strong>
                </p>
            </Link>

            <div className="product_rating">
                <ReactStars
                    count={5}
                    edit={false}
                    value={4}
                    activeColor="#FFA41C"
                    size={15}
                />
            </div>

            <Link to="/singleProductListing">
                <img id="prime_img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1" />
            </Link>
            <button>Add to Cart</button>

        </div >
    );
}

export default HomeProduct;
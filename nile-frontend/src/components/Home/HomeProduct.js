import './HomeProduct.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";
import { useStateValue } from "../../StateProvider";


function HomeProduct({ productId, image, name, price }) {
    const [{ cart }, dispatch] = useStateValue();
    console.log(cart);

    const addToCart = () => {
        // dispatch the item into the data layer
        dispatch({
            type: "ADD_TO_CART",
            product: {
                productId: productId,
                name: name,
                image: image,
                price: price,
                // rating: rating,
            },
        });
    };

    return (
        <div className="product">
            <div className="product_info"></div>

            <Link to="/singleProductListing">
                <p id="product_name">{name}</p>
            </Link>

            <Link to="/singleProductListing">
                <img id="product-image" src={image} alt="" />
            </Link>

            <Link to="/singleProductListing">
                <p id="price_tag">
                    <small>$</small>
                    <strong>{price}</strong>

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
            <button onClick={addToCart}>Add to Cart</button>

        </div >
    );
}

export default HomeProduct;
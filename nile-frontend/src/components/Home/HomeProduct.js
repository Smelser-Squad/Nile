import './HomeProduct.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";
import { useStateValue } from "../../StateProvider";


function HomeProduct({ productId, image, name, price, rating, description, vendor }) {
    const [{ cart }, dispatch] = useStateValue();
    const addToCart = () => {
        // dispatch the item into the data layer
        dispatch({
            type: "ADD_TO_CART",
            product: {
                productId: productId,
                name: name,
                image: image,
                price: price,
                rating: rating,
                description: description,
                vendor: vendor
            },
        });
    };

    return (
        <div className="product">
            <div className="product_info"></div>

            <Link to={`/singleProductListing/${productId}`} style={{ textDecoration: 'none' }}>
                <p id="product_name" >{name}</p>
            </Link>
            <p id="product_description"> {description} </p>
            <p id="product_description"> {vendor} </p>

            <Link to={`/singleProductListing/${productId}`} style={{ textDecoration: 'none' }}>
                <img id="product_image" src={image} alt="" />
            </Link>

            <Link to={`/singleProductListing/${productId}`} style={{ textDecoration: 'none' }}>
                <p id="price_tag">
                    <small>$</small>
                    <strong>{price}</strong>

                </p>
            </Link>
            <div className='product_rating'>
                <ReactStars
                    count={5}
                    edit={false}
                    value={rating}
                    activeColor="#FFA41C"
                    size={15}
                />
            </div>
            <Link to={`/singleProductListing/${productId}`}>
                <img id="prime-img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"></img>
            </Link>
            <button onClick={addToCart}>Add to Cart</button>
        </div >
    );
}

export default HomeProduct;
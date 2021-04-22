import './HomeProduct.css';
import ReactStars from "react-rating-stars-component";
import { Link } from "react-router-dom";
import { useStateValue } from "../../StateProvider";


function HomeProduct({ productId, image, name, price, rating, primeEligible }) {
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
                primeEligible:primeEligible
            },
        });
    };
    function PrimeLogo(props) {
        const primeEligible = props.primeEligible;
        if(primeEligible) {
            return (
                <Link to={`/singleProductListing/${productId}`}>
                    <img id="prime-img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"></img>
                </Link>
            );
        }
        else {
            return <div></div>
        }
    }
    return (
        <div className="product">
            <div className="product_info"></div>

            <Link to={`/singleProductListing/${productId}`}>
                <p id="product_name">{name}</p>
            </Link>

            <Link to={`/singleProductListing/${productId}`}>
                <img id="product-image" src={image} alt="" />
            </Link>

            <Link to={`/singleProductListing/${productId}`}>
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
            <PrimeLogo primeEligible={primeEligible}/>
           
            <button onClick={addToCart}>Add to Cart</button>

        </div >
    );
}

export default HomeProduct;
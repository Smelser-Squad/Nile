import ReactStars from "react-rating-stars-component";
import './BrandProducts.css';

function PrimeLogo(props) {
        
    const primeEligible = props.primeEligible;
    if (primeEligible) {
        return (
            <img id="prime-img" alt="prime" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"
            
            ></img>

        );
    }
    else {
        return <div></div>
    }
}
function BrandProduct({key,name,price,rating,image,color, primeEligible, reviews}){
    return(

            <div className="BrandProduct">
            <h1 id="name"  onClick={() => { window.location.href = `/singleProductListing/${key}` }}>{name} - {color} </h1>
            <img id="product-image" src={image} alt=""></img>
             <div className="BrandProduct_info">
             <div class="row">
            <ReactStars
                    count={5}
                    edit={false}
                     isHalf={true}
                    value={rating}
                    activeColor="#FFA41C"
                    size={15}
                 />
          

            <p id="review-count">{reviews}</p>
             </div>
            <h2>${price}</h2>
             <PrimeLogo primeEligible={primeEligible} productId={key} />
             </div>
       </div>
        
    )
   
}
export default BrandProduct;
import {useState, useEffect} from 'react'
import { useParams } from 'react-router-dom'
import { getBrandProducts } from '../../service/ProductService';
import Pagination from "../../common/Pagination";
import BrandProduct from './BrandProduct';
import ReactStars from "react-rating-stars-component";


function AllBrandProducts(){

    const [allProducts, setallProducts] = useState([]);
    const[sorted,setSortedProducts] = useState([]);
    const{brand}=useParams();
    const[itemsPerPage]=useState(3);
    const[currPage, setCurrPage]=useState(1);    
    
    const ProductList=[];

    useEffect(() => {

    if (allProducts.length === 0) {
        getBrandProducts(brand).then((list) => {
            list.map((item) =>
            ProductList.push(item));
            const cards = ProductList.map((product) =>
            <BrandProduct
            key={product.productId}
            productId={product.productId}
            name={product.name}
            price={product.price}
            rating={calcRating(product)}
            image={product.photos[0].imageSrc}
            color={product.photos[0].color}
            primeEligible={product.primeEligible}
            reviews={product.reviews.length}
            />
            );

            setallProducts(cards);
          
        })
    }
 
},[]);



const indexOfLastProduct = currPage * itemsPerPage;
const indexOfFirstProduct = indexOfLastProduct - itemsPerPage;
const currentProducts = allProducts.slice(indexOfFirstProduct, indexOfLastProduct);

 const paginate = pageNumber =>{ setCurrPage(pageNumber)}
    
    function calcRating(product)  {
        let sum = 0;
        for(let i = 0; i < product.reviews.length; i++) {
            sum += product.reviews[i].rating;
        }
        const avgRating = sum / product.reviews.length;
        return avgRating;
    }
    
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
   function Sort(){

    var selected=document.getElementById("sort").value
    
    for(let i = 0; i < allProducts.length;i++)
    {
        setallProducts(allProducts)
        
     
            ProductList.push(allProducts[i].props)

           
    }
   
        if(selected==="low to high"){

            ProductList.sort((a,b)=>{return a.price - b.price});
            
        }
        else if(selected==="high to low"){
            ProductList.sort((a,b)=>{return b.price - a.price});
        
        }
       
      const filtered = ProductList.map((product)=>

      <div className="BrandProduct">
      <h1 id="name"  onClick={() => { window.location.href = `/singleProductListing/${product.productId}` }}>{product.name} - {product.color} </h1>
      <img id="product-image" src={product.image} alt="" onClick={() => { window.location.href = `/singleProductListing/${product.productId}` }}></img>
       <div className="BrandProduct_info">
       <div class="row">
      <ReactStars
              count={5}
              edit={false}
               isHalf={true}
              value={product.rating}
              activeColor="#FFA41C"
              size={15}
           />
    

      <p id="review-count">{product.reviews}</p>
       </div>
      <h2>${product.price}</h2>
       <PrimeLogo primeEligible={product.primeEligible} productId={product.productId} />
       </div>
       </div>
            );
            setallProducts(filtered);
           
     }
    
   
   
return(
    <div>
        <h1>{allProducts.length} results for "{brand}"</h1>

        <select id="sort" onChange={()=>Sort()}>
        <option selected disabled hidden >Sort By</option>
        <option value="high to low">Price:High to Low</option>
        <option value="low to high"> Price:Low to High</option>
        </select>

         {currentProducts}

            <Pagination 
            postsPerPage={itemsPerPage} 
            totalPosts={allProducts.length} 
            paginate={paginate}></Pagination>
     
    
    
      
    </div>
    
)
}
export default AllBrandProducts;

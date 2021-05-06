import {useState, useEffect} from 'react'
import { useParams } from 'react-router-dom'
import { getBrandProducts } from '../../service/ProductService';
import Pagination from "../../common/Pagination";
import BrandProduct from './BrandProduct';



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
            setSortedProducts(cards)
        })
    }
    console.log(ProductList);
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
   
   function Sort(){

    var selected=document.getElementById("sort").value
    for(let i = 0; i < allProducts.length;i++)
    {
        setallProducts(allProducts)
        
      
            ProductList.push(allProducts[i].props)}

            console.log(ProductList);
    
   
        // if(selected==="low to high"){

        //     console.log(ProductList.sort((a,b)=>{return a.price - b.price}));
        // }
        // else if(selected==="high to low"){
        //     console.log(ProductList.sort((a,b)=>{return b.price - a.price}));
        // }


    //   const filtered = ProductList.map((product)=>
    //         <BrandProduct
    //         key={product.productId}
    //         productId={product.productId}
    //         name={product.name}
    //         price={product.price}
    //         rating={calcRating(product)}
    //         image={product.photos[0].imageSrc}
    //         color={product.photos[0].color}
    //         primeEligible={product.primeEligible}
    //         reviews={product.reviews.length}
    //         />
    //         );
    //         setallProducts(filtered);
    //         setSortedProducts(filtered)
    }
    
   
   
return(
    <div>
        <h1>{allProducts.length} results for "{brand}"</h1>

        {/* <select id="sort" onChange={()=>Sort()}>
        <option selected disabled hidden >Sort By</option>
        <option value="high to low">Price:High to Low</option>
        <option value="low to high"> Price:Low to High</option>
        </select> */}

         {currentProducts}

            <Pagination 
            postsPerPage={itemsPerPage} 
            totalPosts={allProducts.length} 
            paginate={paginate}></Pagination>
     
    
    
      
    </div>
    
)
}
export default AllBrandProducts;

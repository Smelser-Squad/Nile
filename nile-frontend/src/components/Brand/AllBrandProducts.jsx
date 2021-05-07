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

    function compareValues(key,order='asc'){
        return function innerSort(a,b){
            if(!a.hasOwnProperty(key)|| !b.hasOwnProperty(key)) return 0;
            const varA=(typeof a[key]==='string') ? a[key].toUpperCase():a[key];
            const varB=(typeof b[key]==='string') ? b[key].toUpperCase():b[key];
    
            let compare=0;
            if(varA>varB){
                compare=1;
            } else if(varA < varB){
                compare=-1;
            }
            return(
                (order==='desc') ? (compare * -1) : compare
            );
    
        };
       }
       function Sort(){
           let list;
        var selected=document.getElementById("sort").value
    
        const FilterArray=(Array.from(allProducts));
    
        if(selected=="low to high"){
             list=FilterArray.sort(compareValues('price'));
        }
        else{
            list=FilterArray.sort(compareValues('price','desc'));
        }
    
    
           let cards = list.map((product) =>
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
            />);
            setallProducts(cards);
            //     <div className="BrandProduct">
            //     <h1 id="name"  onClick={() => { window.location.href = `/singleProductListing/${product.productId}` }}>{product.name} - {product.photos[0].color} </h1>
            //     <img id="product-image" src={product.photos[0].imageSrc} alt=""></img>
            //     <div className="BrandProduct_info">
            //     <div class="row">
            //     <ReactStars
            //             count={5}
            //             edit={false}
            //             isHalf={true}
            //             value={calcRating(product)}
            //             activeColor="#FFA41C"
            //             size={15}
            //         />
    
            //     <p id="review-count">{product.reviews.length}</p>
            //     </div>
            //     <h2>${product.price}</h2>
            //     <PrimeLogo primeEligible={product.primeEligible} productId={product.productId} />
            //     </div>
            // </div>
            //     );
            //     setProduct(cards);
   
//    function Sort(){

//     var selected=document.getElementById("sort").value
//     for(let i = 0; i < allProducts.length;i++)
//     {
//         setallProducts(allProducts)
        
      
//             ProductList.push(allProducts[i].props)}

//             console.log(ProductList);
    
   
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

import {useState, useEffect} from 'react'
import { useParams } from 'react-router-dom'
import { getBrandProducts } from '../../service/ProductService';
import ReactStars from "react-rating-stars-component";
import Pagination from '@material-ui/lab/Pagination';
import { makeStyles } from '@material-ui/core/styles';
import './BrandProducts.css'



function BrandProducts(){

   const useStyles = makeStyles((theme) => ({
        root: {
          '& > *': {
            marginTop: theme.spacing(2),
          },
        },
      }));
    const classes = useStyles();
    const [Product, setProduct] = useState([]);
    const{brand}=useParams();
    const[FilteredList,setList]=useState();
    const ProductList =[];

   
      
    useEffect(() => {

    if (Product.length === 0) {
        getBrandProducts(brand).then((list) => {
            list.map((item) =>
            ProductList.push(item)
            );
            setList(ProductList);
            // console.log(ProductList.sort(compareValues('price')));

            let cards = ProductList.map((product) =>
            <div className="BrandProduct">
            <h1 id="name"  onClick={() => { window.location.href = `/singleProductListing/${product.productId}` }}>{product.name} - {product.photos[0].color} </h1>
            <img id="product-image" src={product.photos[0].imageSrc} alt=""></img>
            <div className="BrandProduct_info">
            <div class="row">
            <ReactStars
                    count={5}
                    edit={false}
                    isHalf={true}
                    value={calcRating(product)}
                    activeColor="#FFA41C"
                    size={15}
                />
          
            <p id="review-count">{product.reviews.length}</p>
            </div>
            <h2>${product.price}</h2>
            <PrimeLogo primeEligible={product.primeEligible} productId={product.productId} />
            </div>
        </div>
            );
      setProduct(cards);

        }
        );
        
    }
    
}, []);

    function PrimeLogo(props) {
        // console.log(ProductList)
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
  
    const FiliterArray=(Array.from(FilteredList));

    if(selected=="low to high"){
         list=FiliterArray.sort(compareValues('price'));
    }
    else{
        list=FiliterArray.sort(compareValues('price','desc'));
    }


       let cards = list.map((product) =>
            <div className="BrandProduct">
            <h1 id="name"  onClick={() => { window.location.href = `/singleProductListing/${product.productId}` }}>{product.name} - {product.photos[0].color} </h1>
            <img id="product-image" src={product.photos[0].imageSrc} alt=""></img>
            <div className="BrandProduct_info">
            <div class="row">
            <ReactStars
                    count={5}
                    edit={false}
                    isHalf={true}
                    value={calcRating(product)}
                    activeColor="#FFA41C"
                    size={15}
                />
          
            <p id="review-count">{product.reviews.length}</p>
            </div>
            <h2>${product.price}</h2>
            <PrimeLogo primeEligible={product.primeEligible} productId={product.productId} />
            </div>
        </div>
            );
            setProduct(cards);
   
}
   
return(
    <div>
        <h1>{Product.length} results for "{brand}"</h1>

        <select id="sort" onChange={()=>Sort()}>
        <option selected disabled hidden >Sort By</option>
        <option value="high to low">Price:High to Low</option>
        <option value="low to high"> Price:Low to High</option>
        </select>
        
    {Product}
    <div id ="pages" className={classes.root}>
     
      <Pagination count={11} defaultPage={6} boundaryCount={2} />
    </div>
    </div>
)
}
export default BrandProducts;
import './MoreProducts.css';
import Product from "./Product";
import {Grid} from '@material-ui/core';
import { useState } from 'react';
import {getProducts} from '../../service/ProductService'
import ScrollMenu from 'react-horizontal-scrolling-menu';


function calcRating(product)  {
    let sum = 0;
    for(let i = 0; i < product.reviews.length; i++) {
        sum += product.reviews[i].rating;
    }
    const avgRating = sum / product.reviews.length;
    return avgRating;
}

function MoreProducts() {

    const [products, setProducts] = useState([]);
    const AllProducts = [];

    if(products.length === 0) {
    getProducts().then((list)=>
        {
            list.map((item)=>
            AllProducts.push(item)
            );
            const products = AllProducts.map((product)=>
                <Grid item md={2} className="menu-item">
                    <Product
                        productId={product.productId}
                        name={product.name}
                        price={product.price}
                        avgRating={calcRating(product)}
                        image= {product.photos[0].imageSrc}
                        reviewCount={product.reviews.length} 
                        primeEligible={product.primeEligible}
                    />
                </Grid>

            );
            setProducts(products);
        }
        );
    }

    const pages = Math.ceil((products.length / 5));

    const Arrow = ({ text, className }) => {
        return (
            <div className={className}>
                {text}
            </div>
        );
    };

    const ArrowLeft = Arrow({ text: '<', className: 'arrow-prev' });
    const ArrowRight = Arrow({ text: '>', className: 'arrow-next' });

    return (
        <div class="container">
            <hr/>
            

            <Grid container justify="center" alignItems = "center">
                <Grid item xs={12} md={12}>
                    <div class="header">
                        <span>
                            <h3 id="customer-bought">Customers also bought these products</h3>
                            <p class="pages">Page 1 of {pages}</p>
                        </span>
                    </div>
                </Grid>

                <ScrollMenu
                    alignCenter={true}
                    alignOnResize={true}
                    data={products}
                    arrowLeft={ArrowLeft}
                    arrowRight={ArrowRight}
                />
            </Grid>
            <hr />
        </div>
    );
}

export default MoreProducts;
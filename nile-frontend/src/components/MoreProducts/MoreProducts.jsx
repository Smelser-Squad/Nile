import './MoreProducts.css';
import Product from "./Product";
import {Grid} from '@material-ui/core';
import { useState } from 'react';
import {getProducts} from '../../service/ProductService'

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
                <Grid item md={2}>
                    <Product
                        productId={product.productId}
                        name={product.name}
                        price={product.price}
                        rating={4}
                        avgRating={4}
                        image= "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fclipground.com%2Fimages%2Fsweatpants-png-4.png&f=1&nofb=1"
                        reviewCount={16} 
                    />
                </Grid>

            );
            setProducts(products);
        }
        );
    }

    return (
        <div class="container">
            <hr/>
            <Grid container justify="center" alignItems = "center">
                <Grid item xs={12} md={12}>
                    <div class="header">
                        <span>
                            <h3 id="customer-bought">Customers also bought these products</h3>
                            <p class="pages">Page 1 of 2</p>
                        </span>
                    </div>
                </Grid>

                <Grid item md={1}>
                    <button class="nav-button" id="left-nav">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z" />
                        </svg>
                    </button>
                </Grid>
            
                {products}

                <Grid item md={1}>
                    <button class="nav-button" id="right-nav">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z" />
                        </svg>
                    </button>
                </Grid>
            </Grid>
            <hr />
        </div>
    );
}

export default MoreProducts;
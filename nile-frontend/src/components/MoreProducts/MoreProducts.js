import './MoreProducts.css';
import Product from "./Product.js";
import {Grid, Paper} from '@material-ui/core';

function MoreProducts() {
    return (
        <div class="container">
            <hr/>
            <Grid container justify="center">
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
                            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
                        </svg>
                    </button>
                </Grid>
                

                <Grid item md={2}>
                    <Product/>
                </Grid>
                <Grid item md={2}>
                    <Product/>
                </Grid>
                <Grid item md={2}>
                    <Product/>
                </Grid>
                <Grid item md={2}>
                    <Product/>
                </Grid>
                <Grid item md={2}>
                    <Product/>
                </Grid>
                

                <Grid item md={1}>
                    <button class="nav-button" id="right-nav">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                    </button>
                </Grid>
            </Grid>
            <hr/>
        </div>
    );
}

export default MoreProducts;
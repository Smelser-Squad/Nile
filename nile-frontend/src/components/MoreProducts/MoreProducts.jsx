import './MoreProducts.css';
import Product from "./Product";
import {Grid} from '@material-ui/core';
import { useState, useEffect } from 'react';
import axios from 'axios';

function MoreProducts() {

    const [data, setData] = useState([]);

    const getProduct=async()=>{
        await axios.get('http://localhost:80/api/products')
        .then(res=>{
            const products = res.data;
            if(res.status === 200){
                setData(products)
            };
        })
        
    };

    useEffect(()=>{
            getProduct();
            console.log(data);
        },
        []
    );
    
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


                <Grid item md={2}>
                    <Product
                    productId="12321341"
                    name="Awesome Sweat Pants"
                    price={20.00}
                    avgRating={4}
                    image="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fclipground.com%2Fimages%2Fsweatpants-png-4.png&f=1&nofb=1"
                    reviewCount={16} />
                </Grid>
                <Grid item md={2}>
                    <Product
                    productId="12321342"
                    name="Awesome T-Shirt"
                    price={15.00}
                    avgRating={3}
                    image="https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.pngall.com%2Fwp-content%2Fuploads%2F2016%2F04%2FT-Shirt-PNG-File.png&f=1&nofb=1"
                    reviewCount={12}/>
                </Grid>
                <Grid item md={2}>
                    <Product
                    productId="12321343"
                    name="Awesome Tennis Shoes"
                    price={60.00}
                    avgRating={4.5}
                    image="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.tension24.com.au%2Fmedia%2Fcatalog%2Fproduct%2Fcache%2F1%2Fimage%2Fab687bdc54b01ee9b7e0fb822647627d%2Fc%2Fm%2Fcm7819_ftw_virtual_side-lateral_transparent.png&f=1&nofb=1"
                    reviewCount={23}/>
                </Grid>
                <Grid item md={2}>
                    <Product
                    productId="12321344"
                    name="Awesome Shorts"
                    price={13.00}
                    avgRating={4}
                    image="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.pro-amkits.co.uk%2Fmedia%2Fcatalog%2Fproduct%2Fcache%2F1%2Fimage%2F1200x1395%2F9df78eab33525d08d6e5fb8d27136e95%2F7%2F2%2F725881-010-pv_2.png&f=1&nofb=1"
                    reviewCount={5}/>
                </Grid>
                <Grid item md={2}>
                    <Product
                    productId="12321345"
                    name="Funky Socks"
                    price={10.00}
                    avgRating={5}
                    image="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fdtmxsocks.cz%2Fwp-content%2Fuploads%2F2018%2F02%2FRocketSlider-background-2A.png&f=1&nofb=1"
                    reviewCount={100}/>
                </Grid>


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
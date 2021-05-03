import './MoreProducts.css';
import Product from "./Product";
import { useState, useEffect } from 'react';
import { getProduct, getProductsByCategory } from '../../service/ProductService'
import ScrollMenu from 'react-horizontal-scrolling-menu';
import { useParams } from 'react-router';

function calcRating(product) {
    let sum = 0;
    for (let i = 0; i < product.reviews.length; i++) {
        sum += product.reviews[i].rating;
    }
    const avgRating = sum / product.reviews.length;
    return avgRating;
}

function MoreProducts() {
    const [products, setProducts] = useState([]);
    const [category, setCategory] = useState([]);
    const AllProducts = [];

    const { productId } = useParams();

    useEffect(() => {
        getProduct(productId)
            .then((category) => {
                setCategory(category.category.name)
            }
            );
    }, [productId]);

    if (products.length === 0) {
        getProductsByCategory(category).then((list) => {
            for(let item of list) {
                if (item.productId !== parseInt(productId)) {
                    AllProducts.push(item);
                }
            }
            
            const products = AllProducts.map((product) =>
                <Product
                    key={product.productId}
                    productId={product.productId}
                    name={product.name}
                    price={product.price}
                    avgRating={calcRating(product)}
                    image={product.photos[0].imageSrc}
                    reviewCount={product.reviews.length}
                    primeEligible={product.primeEligible}
                />
            );
            setProducts(products);
        }
        );
    }

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
            <hr />

            <div class="header">
                <span>
                    <h3 id="customer-bought">Customers also bought these products</h3>
                </span>
            </div>

            <ScrollMenu
                data={products}
                arrowLeft={ArrowLeft}
                arrowRight={ArrowRight}
                dragging={false}
                wheel={false}
            />

            <hr />
        </div>
    );
}

export default MoreProducts;
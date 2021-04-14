import './MoreProducts.css';
import ReactStars from "react-rating-stars-component";

function MoreProducts() {
    return (
        <div class="container">
            <div class="header">
                <h2>Customers also bought these products</h2>
                <h3 id="customer-bought">Customers also bought these products</h3>
                <p class="pages">Page 1 of 2</p>
            </div>

            <div class="left-nav">
                <button class="nav-button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
                    </svg>
                </button>
            </div>
            

            <div class="products-container">
                <div class="image-container">
                    <img class="image" src="" alt="product_image"></img>
                </div>

                <div class="link">
                        <div class="name-container">
                            <a id="product-name" href="#productPage">Product name here</a>
                        </div>

                        <div class="rating-container">
                            <ReactStars
                                count={5}
                                edit={false}
                                value={4}
                            />
                        </div>

                        <div class="price-container">
                            <p id="price-tag">$10.00</p>
                        </div>

                        <div class="prime-eligible-container">
                            <img id="prime-img" src="https://external-content.duckduckgo.com/iu/?u=https://curlydavenport.com/wp-content/uploads/2018/05/Amazon-Prime-Logo-Curly-D-Pink-Coco.png&f=1&nofb=1"></img>
                        </div>
                </div>

            </div>

            
            <div class="right-nav">
                <button class="nav-button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                    </svg>
                </button>
            </div>
        </div>
    );
}

export default MoreProducts;
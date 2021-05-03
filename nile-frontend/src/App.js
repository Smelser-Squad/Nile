import { Elements } from '@stripe/react-stripe-js';
import { loadStripe } from '@stripe/stripe-js';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import BrandProducts from './components/Brand/BrandProducts';
import Checkout from './components/Checkout/Checkout';
import Header from './components/Header/Header.js';
import Home from './components/Home/Home.js';
import SingleOrder from './components/Order/SingleOrder';
import Payment from './components/Payment/Payment';
import SingleProductListing from './components/ProductListing/SingleProductListing';
import CreateReview from './components/Reviews/CreateReview';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import ShowAllReviews from './components/Reviews/ShowAllReviews';
import Orders from './components/Order/Orders'

const stripekey = loadStripe('pk_test_51IiMSjC3X35blG5onbHeR4PRYxKLDXpSIYunN4jmZKM3Z5lXDrZ5P9v1pS9rzwH4JUokfAnOl3gojKJtd6fFsEKE00CYlgul7y');

function App() {
    return (
        // eslint-disable-next-line
        <Router>
            <div className="App" >
                <Header />

                <Switch>
                    <Route exact path="/singleProductListing/:productId">
                        <SingleProductListing />
                    </Route>
                    <Route exact path="/products/brand/:brand">
                        <BrandProducts />
                    </Route>

                    <Route exact path="/checkout">
                        <Checkout />
                    </Route>

                    <Route exact path="/signin" component={SignIn}></Route>
                    <Route exact path="/signup" component={SignUp}></Route>

                    <Route exact path="/payment">
                        <Elements stripe={stripekey}>

                            <Payment />

                        </Elements>
                    </Route>
                    <Route exact path="/">
                        <Home />

                    </Route>

                    <Route exact path="/createReview/:productId">
                        <CreateReview />
                    </Route>
                    <Route exact path="/singleProductListing/all-product-reviews/:productId">
                        <ShowAllReviews />
                    </Route>

                    <Route exact path="/orders">
                        <Orders />
                    </Route>
                    <Route exact path="/SingleOrder/:chargeId">
                        <SingleOrder />
                    </Route>
                </Switch>
            </div>
        </Router>
    );
}

export default App;

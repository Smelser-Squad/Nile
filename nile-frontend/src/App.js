import './App.css';
import Tag from './components/ReviewTag/Tag.jsx';
import Header from './components/Header/Header.js';
import Home from './components/Home/Home.js';
import Checkout from './components/Checkout/Checkout';
import SingleProductListing from './components/ProductListing/SingleProductListing';
import CreateReview from './components/Reviews/CreateReview'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Payment from './components/Payment/Payment';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';

const stripekey = loadStripe('pk_test_51IiMSjC3X35blG5onbHeR4PRYxKLDXpSIYunN4jmZKM3Z5lXDrZ5P9v1pS9rzwH4JUokfAnOl3gojKJtd6fFsEKE00CYlgul7y');

function App() {
    return (
        // eslint-disable-next-line
        <Router>
            <div className="App" >
                <Header />

                <Switch>
                    <Route exact path="/singleProductListing/:productId">
                        <SingleProductListing/>
                    </Route>

                    <Route exact path="/checkout">
                        <Checkout />
                    </Route>

                    <Route exact path="/payment">
                        <Elements stripe={stripekey}>

                            <Payment />

                        </Elements>
                    </Route>
                    <Route exact path="/">
                        <Home />
              
                    </Route>

                    <Route exact path="/createReview">
                        <CreateReview/>
                    </Route>


                </Switch>
            </div>
        </Router>
        
    );
}

export default App;

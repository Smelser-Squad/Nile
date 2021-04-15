import './App.css';
import Header from './components/Header/Header.js';
import Home from './components/Home/Home.js';
import Checkout from './components/Cart/Checkout/Checkout';
import SingleProductListing from './components/ProductListing/SingleProductListing';

import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import ProductListing from './components/ProductListing/ProductListing';

function App() {
    return (
        // eslint-disable-next-line
        <Router>
            <div className="App" >
                <Header />

                <Switch>
                    <Route path="/singleProductListing">
                        <SingleProductListing />
                    </Route>

                    <Route path="/checkout">
                        <Checkout />
                    </Route>

                    <Route path="/">
                        <Home />
                    </Route>

                </Switch>

            </div>
        </Router>
    );
}

export default App;
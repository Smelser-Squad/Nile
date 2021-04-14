import './App.css';
import Header from './components/Header/Header.js';
import Home from './components/Home/Home.js';
<<<<<<< HEAD
import Checkout from './components/Checkout/Checkout';
import SingleProductListing from './components/ProductListing/SingleProductListing';
=======
import Checkout from './components/Cart/Checkout/Checkout';
>>>>>>> d010af38ef15c6cf0608c5403e65dbb1b57fdc3a

import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
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
import './App.css';
import Tag from './components/ReviewTag/Tag.js';
import Header from './components/Header/Header.js';
import Home from './components/Home/Home.js';
import Checkout from './components/Cart/Checkout/Checkout';
import SingleProductListing from './components/ProductListing/SingleProductListing';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'


function App() {
    return (
        // eslint-disable-next-line
        <Router>
            <div className="App" >
                <Header />

                <Switch>
                    <Route exact path="/singleProductListing">
                        <SingleProductListing />
                        <Tag />
                    </Route>

                    <Route exact path="/checkout">
                        <Checkout />
                    </Route>

                    <Route exact path="/">
                        <Home />
              
                    </Route>

                </Switch>
            </div>
        </Router>
        
    );
}

export default App;

import './App.css';
import Header from './components/Header/Header.js';
import Home from './components/Home/Home.js';
import Checkout from './components/Cart/Checkout/Checkout';

import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
function App() {
    return (
        // eslint-disable-next-line
        <Router>
            <div className="App" >
                <Header />

                <Switch>
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
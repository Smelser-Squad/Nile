import { ACCESS_TOKEN } from './constants';

export const initialState = {
    cart: [],
    currentUser: null,
    isAuthenticated: false,
    products: [],
    filteredProducts: []


};


export const getCartTotal = (cart) =>
    cart?.reduce((amount, product) => product.price + amount, 0);

export const filterProductByName = (search, products) => {
    const productsCopy = [...products]
    return productsCopy.filter(product => {
        return product.name.toLowerCase().includes(search);
    })


}



const reducer = (state, action) => {
    switch (action.type) {
        case "ADD_TO_CART":
            return {
                ...state,
                cart: [...state.cart, action.product],
            };
        case "REMOVE_FROM_CART":
            const index = state.cart.findIndex((cartProduct) =>
                cartProduct.productId === action.productId);

            let newCart = [...state.cart];

            if (index >= 0) {
                newCart.splice(index, 1);
            }
            else {
                console.warn('cant remove from the cart')
            }
            return {
                ...state,
                cart: newCart
            };
        case "EMPTY_CART":
            return {
                ...state,
                cart: []
            };
        case "USER_SIGN_IN":
            return {
                ...state,
                currentUser: action.currentUser,
                isAuthenticated: true
            }

        case "USER_SIGN_OUT":
            sessionStorage.removeItem(ACCESS_TOKEN);
            return {
                ...state,
                currentUser: null,
                isAuthenticated: false
            };
        case "FILTER_PRODUCT":
            return {
                ...state,
                filteredProducts: action.filteredProducts
            }
        case "GET_PRODUCTS":
            return {
                ...state,
                products: action.products,
                filteredProducts: action.products
            }
        default:
            return state;
    }
};

export default reducer;

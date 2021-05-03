export const initialState = {
    cart: [],
    currentUser: null,
    isAuthenticated: false
};

export const getCartTotal = (cart) =>
    cart?.reduce((amount, product) => product.price + amount, 0);



const reducer = (state, action) => {
    // console.log(action);
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
            }

        case "USER_SIGN_IN":
            return {
                ...state,
                currentUser: action.currentUser,
                isAuthenticated: action.isAuthenticated
            }
        
        case "USER_SIGN_OUT":
            return {
                ...state,
                currentUser: null,
                isAuthenticated: false
            }
        default:
            return state;
    }
};

export default reducer;

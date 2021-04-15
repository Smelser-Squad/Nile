import './SingleReview.css';


function SingleReview() {
    return (
        <div class="single-review-container">
            {/* set up logic to only show apporiate number of stars from rating API call */}
            <div id="rating-stars">
                <svg xmlns="http://www.w3.org/2000/svg" stroke="orange" fill="gold" width="15" height="15" viewBox="0 0 24 24"><path d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"/></svg>
                <svg xmlns="http://www.w3.org/2000/svg" stroke="orange" fill="gold" width="15" height="15" viewBox="0 0 24 24"><path d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"/></svg>
                <svg xmlns="http://www.w3.org/2000/svg" stroke="orange" fill="gold" width="15" height="15" viewBox="0 0 24 24"><path d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"/></svg>
                <svg xmlns="http://www.w3.org/2000/svg" stroke="orange" fill="gold" width="15" height="15" viewBox="0 0 24 24"><path d="M12 .587l3.668 7.568 8.332 1.151-6.064 5.828 1.48 8.279-7.416-3.967-7.417 3.967 1.481-8.279-6.064-5.828 8.332-1.151z"/></svg>
                <svg xmlns="http://www.w3.org/2000/svg" fill="gold" width="15" height="15" viewBox="0 0 24 24"><path d="M12 5.173l2.335 4.817 5.305.732-3.861 3.71.942 5.27-4.721-2.524-4.721 2.525.942-5.27-3.861-3.71 5.305-.733 2.335-4.817zm0-4.586l-3.668 7.568-8.332 1.151 6.064 5.828-1.48 8.279 7.416-3.967 7.416 3.966-1.48-8.279 6.064-5.827-8.332-1.15-3.668-7.569z"/></svg>
            </div>
            <h4 id="title"> what an amazing shirt!</h4>
            <h6>Reviewed in the United States on (LOCALDATE)</h6>
            <span>aslkjdhfkljashdfkjlahsdfkljhasdkljfhaskldfhkalsjdhfkjasdhfkjlashdflkjashdflkajshdflkahs</span>
            

        </div>
    )
}

// RATING
//REVIEW DATE
//SUMMARY
//TITLE

//REVIEW_ID
//FEATURE_ID
//PRODUCT_ID
//USER_ID

export default SingleReview;
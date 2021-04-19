import './SingleReview.css';
import ReactStars from "react-rating-stars-component";



function SingleReview({helpful,rating,reviewDate,summary,title}) {
    return (
        <div class="single-review-container">
            <ReactStars
                    count={5}
                    edit={false}
                    value={5}
                    activeColor="#FFA41C"
                    size={15}
                />
            <h4 id="title"> what an amazing shirt!</h4>
            <h6>Reviewed in the United States on (LOCALDATE)</h6>
            <span>This is where the full text of the review will go.</span>
            

        </div>
    )
}

//RATING
//REVIEW DATE
//SUMMARY
//TITLE

//REVIEW_ID
//FEATURE_ID
//PRODUCT_ID
//USER_ID

export default SingleReview;
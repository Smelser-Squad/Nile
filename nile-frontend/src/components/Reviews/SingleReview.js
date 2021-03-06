import './SingleReview.css';
import ReactStars from "react-rating-stars-component";



function SingleReview({helpful,rating,reviewDate,summary,title}) {
    return (
        <div class="single-review-container">
            <ReactStars
                    count={5}
                    edit={false}
                    value={rating}
                    activeColor="#FFA41C"
                    size={15}
                />
            <h4 id="title"> {title}</h4>
            <h6>{reviewDate}</h6>
            <span>{summary}</span>
            

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
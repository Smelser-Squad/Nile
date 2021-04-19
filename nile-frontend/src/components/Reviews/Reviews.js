import './Reviews.css';
import SingleReview from './SingleReview';
import {getReviews} from './ReviewService';

function Reviews() {

    const reviewList = [];

    getReviews().then((list)=>
    {
        list.map((item)=>
        reviewList.push(item)
        );
        console.log(reviewList);
    }
    )

    return (
        <div class="reviews-container">

        {reviewList}
        <SingleReview/>






        </div>
    )
}

export default Reviews;
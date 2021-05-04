import './ReviewSummary.css';
import React, { Suspense } from 'react';
import axios from 'axios';
import LinearProgress from '@material-ui/core/LinearProgress'
import { withStyles } from '@material-ui/core';
import { useParams } from 'react-router'
import { Link } from "react-router-dom";
import { useState, useEffect } from 'react';
import ReviewPhotos from './ReviewPhotos/ReviewPhotos';

const BorderLinearProgress = withStyles(() => ({
    root: {
        height: 20,
        borderRadius: 4,
        maxWidth: 200,
    },
    colorPrimary: {
        backgroundColor: 'lightgray',
    },
    bar: {
        borderRadius: 5,
        backgroundColor: '#FFA41C',
    },
}))(LinearProgress);

function ReviewSummary() {

    const { productId } = useParams();

    const [reviewAvg, getAvgReviews] = useState();
    const [totalReviews, getTotalReviews] = useState();

    const [fiveStarPercent, getFiveStars] = useState();
    const [fourStarPercent, getFourStars] = useState();
    const [threeStarPercent, getThreeStars] = useState();
    const [twoStarPercent, getTwoStars] = useState();
    const [oneStarPercent, getOneStars] = useState();

    const [featureOneName, getFeatureOne] = useState();
    const [featureTwoName, getFeatureTwo] = useState();
    const [featureThreeName, getFeatureThree] = useState();
    const [featureFourName, getFeatureFour] = useState();
    const [featureFiveName, getFeatureFive] = useState();
    const [featureSixName, getFeatureSix] = useState();

    const [featureOneRating, getFeatureOneRating] = useState();
    const [featureTwoRating, getFeatureTwoRating] = useState();
    const [featureThreeRating, getFeatureThreeRating] = useState();
    const [featureFourRating, getFeatureFourRating] = useState();
    const [featureFiveRating, getFeatureFiveRating] = useState();
    const [featureSixRating, getFeatureSixRating] = useState();

    let numFiveStar = 0;
    let numFourStar = 0;
    let numThreeStar = 0;
    let numTwoStar = 0;
    let numOneStar = 0;

    const getFeaturesByProductId = async () => {
        await axios.get(`http://localhost:80/api/features/byproduct/${productId}`)
            .then(res => {
                for (let i in res.data) {
                    if (parseInt(i) === 0) {
                        let sum = 0;
                        let count = 0;
                        for (let j in res.data[i].featureRatingList) {
                            sum += res.data[i].featureRatingList[j].rating;
                            count++;
                        }
                        getFeatureOneRating(sum / count);
                        getFeatureOne(res.data[i].name);
                    }
                    else if (parseInt(i) === 1) {
                        let sum = 0;
                        let count = 0;
                        for (let j in res.data[i].featureRatingList) {
                            sum += res.data[i].featureRatingList[j].rating;
                            count++;
                        }
                        getFeatureTwoRating(sum / count);
                        getFeatureTwo(res.data[i].name);
                    }
                    else if (parseInt(i) === 2) {
                        let sum = 0;
                        let count = 0;
                        for (let j in res.data[i].featureRatingList) {
                            sum += res.data[i].featureRatingList[j].rating;
                            count++;
                        }
                        getFeatureThreeRating(sum / count);
                        getFeatureThree(res.data[i].name);
                    }
                    else if (parseInt(i) === 3) {
                        let sum = 0;
                        let count = 0;
                        for (let j in res.data[i].featureRatingList) {
                            sum += res.data[i].featureRatingList[j].rating;
                            count++;
                        }
                        getFeatureFourRating(sum / count);
                        getFeatureFour(res.data[i].name);
                    }
                    else if (parseInt(i) === 4) {
                        let sum = 0;
                        let count = 0;
                        for (let j in res.data[i].featureRatingList) {
                            sum += res.data[i].featureRatingList[j].rating;
                            count++;
                        }
                        getFeatureFiveRating(sum / count);
                        getFeatureFive(res.data[i].name);
                    }
                    else if (parseInt(i) === 5) {
                        let sum = 0;
                        let count = 0;
                        for (let j in res.data[i].featureRatingList) {
                            sum += res.data[i].featureRatingList[j].rating;
                            count++;
                        }
                        getFeatureSixRating(sum / count);
                        getFeatureSix(res.data[i].name);
                    }
                }
            })
    }

    const getAllReviewsByProductId = async () => {
        await axios.get(`http://localhost:80/api/reviews/byproduct/${productId}`)
            .then(res => {
                for (let i in res.data) {
                    if (res.data[i].rating === 5) {
                        numFiveStar++;
                    }
                    else if (res.data[i].rating === 4) {
                        numFourStar++;
                    }
                    else if (res.data[i].rating === 3) {
                        numThreeStar++;
                    }
                    else if (res.data[i].rating === 2) {
                        numTwoStar++;
                    }
                    else if (res.data[i].rating === 1) {
                        numOneStar++;
                    }
                }

                const totalReviews = numFiveStar + numFourStar + numThreeStar + numTwoStar + numOneStar;

                const avgReview = (((numFiveStar * 5) + (numFourStar * 4)
                    + (numThreeStar * 3) + (numTwoStar * 2) + numOneStar) / totalReviews).toFixed(1);

                const parseAvg = parseFloat(avgReview);

                const fiveStar = (numFiveStar / totalReviews) * 100;
                const fourStar = (numFourStar / totalReviews) * 100;
                const threeStar = (numThreeStar / totalReviews) * 100;
                const twoStar = (numTwoStar / totalReviews) * 100;
                const oneStar = (numOneStar / totalReviews) * 100;

                getFiveStars(parseInt(fiveStar));
                getFourStars(parseInt(fourStar));
                getThreeStars(parseInt(threeStar));
                getTwoStars(parseInt(twoStar));
                getOneStars(parseInt(oneStar));
                getTotalReviews(totalReviews);
                getAvgReviews(parseAvg);

            })
    }

    useEffect(() => {
        getAllReviewsByProductId();
        getFeaturesByProductId();
    }, [productId]);

    function displaySeeMore() {
        var x = document.getElementById("seeMore");
        var y = document.getElementById("moreID");
        if (x.style.display === "none") {
            x.style.display = "block";
            y.innerHTML = "&#8679; See less";
        } else {
            x.style.display = "none";
            y.innerHTML = "&#8681; See more";
        }
    }

    function displayRatingsQ() {
        var x = document.getElementById("ratingsQ");
        var y = document.getElementById("buttonID");
        if (x.style.display === "none") {
            x.style.display = "block";
            y.innerHTML = "&#8679; How are ratings calculated?";
        } else {
            x.style.display = "none";
            y.innerHTML = "&#8681; How are ratings calculated?";
        }
    }

    const ReactStars = React.lazy(() => import('react-rating-stars-component'));

    return (
        <Suspense fallback={<div>Loading...</div>}>
            <div className="reviewSummary_container">
                <div className="review_row">
                    <div className="review-ratings">
                        <br />
                        <h2>Customer reviews</h2>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={reviewAvg}
                            activeColor="#FFA41C"
                            size={15}
                            isHalf={true}
                        />
                        <p className="head_rating">
                            {reviewAvg} out of 5
                </p>
                        <p className="global_rating">
                            {totalReviews} global ratings
                </p>
                        <br />

                        <span>5 star -  {fiveStarPercent}%</span>
                        <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={fiveStarPercent} /></span>

                        <span>4 star -  {fourStarPercent}%</span>
                        <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={fourStarPercent} /></span>

                        <span>3 star -  {threeStarPercent}%</span>
                        <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={threeStarPercent} /></span>

                        <span>2 star -  {twoStarPercent}%</span>
                        <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={twoStarPercent} /></span>

                        <span>1 star -  {oneStarPercent}%</span>
                        <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={oneStarPercent} /></span>

                        <br />

                        <span><button id="buttonID"
                            onClick={displayRatingsQ}
                            className="calc_ratings">&#8681; How are ratings calculated?</button></span>

                        <div id="ratingsQ">
                            <p>To calculate the overall star rating and <br />
                    percentage breakdown by star, we don’t use <br />
                    a simple average. Instead, our system <br />
                    considers things like how recent a review is <br />
                    and if the reviewer bought the item on <br />
                    Amazon. It also analyzes reviews to verify <br />
                    trustworthiness.</p>
                        </div>
                    </div>

                    <ReviewPhotos/>
                </div>

                <hr className="light" style={{clear: "both"}}/>

                <br />

                <div className="by_feature">
                    <h3>By feature</h3>
                    <span>{featureOneName}
                        <ReactStars
                            count={5}
                            edit={false}
                            value={featureOneRating}
                            activeColor="#FFA41C"
                            size={15}
                        /></span>

                    <span>{featureTwoName}</span>
                    <ReactStars
                        count={5}
                        edit={false}
                        value={featureTwoRating}
                        activeColor="#FFA41C"
                        size={15}
                    />

                    <span>{featureThreeName}</span>
                    <ReactStars
                        count={5}
                        edit={false}
                        value={featureThreeRating}
                        activeColor="#FFA41C"
                        size={15}
                    />

                    <div id="seeMore">
                        <span>{featureFourName}</span>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={featureFourRating}
                            activeColor="#FFA41C"
                            size={15}
                        />

                        <span>{featureFiveName}</span>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={featureFiveRating}
                            activeColor="#FFA41C"
                            size={15}
                        />

                        <span>{featureSixName}</span>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={featureSixRating}
                            activeColor="#FFA41C"
                            size={15}
                        />
                    </div>

                    <br />

                    <span><button id="moreID" className="see_more"
                        onClick={displaySeeMore}>&#8681; See more</button></span>

                </div>

                <hr className="light" />

                <br />

                <div className="write_a_review">
                    <h3>Review this product</h3>
                    <br />
                    <span className="share_thoughts">
                        Share your thoughts with other customers
                </span>
                    <br />
                    <br />
                    <Link to={`/createReview/${productId}`}>
                        <button className="writeReview">Write a customer review</button>
                    </Link>
                    <br />
                </div>

                <hr className="light" />

            </div>
        </Suspense>
    );
}

export default ReviewSummary;
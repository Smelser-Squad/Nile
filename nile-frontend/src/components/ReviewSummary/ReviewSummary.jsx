import './ReviewSummary.css';
import React, { Suspense } from 'react';
import axios from 'axios';
import LinearProgress from '@material-ui/core/LinearProgress'
import { withStyles } from '@material-ui/core';
import { useState, useEffect } from 'react';

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

function ReviewSummary (starAvg, productId) {

    productId = document.URL.substring(43);

    const [reviewAvg, getAvgReviews] = useState();

    const [totalReviews, getTotalReviews] = useState();

    const [fiveStarPercent, getFiveStars] = useState();

    const [fourStarPercent, getFourStars] = useState();

    const [threeStarPercent, getThreeStars] = useState();

    const [twoStarPercent, getTwoStars] = useState();

    const [oneStarPercent, getOneStars] = useState();

    let numFiveStar = 0;
    let numFourStar = 0;
    let numThreeStar = 0;
    let numTwoStar = 0;
    let numOneStar = 0;

    async function getAllReviewsByProductId() {
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

                const parseAvg = parseInt(avgReview);

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
    }, []);

    starAvg = reviewAvg;

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
                    <br />
                    <h2>Customer reviews</h2>
                    <ReactStars
                        count={5}
                        edit={false}
                        value={starAvg}
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
                {/* <div className="5star_row">
                    <BorderLinearProgress variant="determinate" value={80} />
                </div>
                <div className="4star_row">
                    <BorderLinearProgress variant="determinate" value={70} />
                </div>
                <div className="3star_row">
                    <BorderLinearProgress variant="determinate" value={60} />
                </div>
                <div className="2star_row">
                    <BorderLinearProgress variant="determinate" value={50} />
                </div>
                <div className="star_row">
                    <span>1 star <BorderLinearProgress variant="determinate" value={40} /> 40%</span>
                </div> */}

                <hr className="light" />

                <br />

                <div className="by_feature">
                    <h3>By feature</h3>
                    <span>Absorbency
                <ReactStars
                            count={5}
                            edit={false}
                            value={4}
                            activeColor="#FFA41C"
                            size={15}
                        /></span>

                    <span>Fabric texture</span>
                    <ReactStars
                        count={5}
                        edit={false}
                        value={2}
                        activeColor="#FFA41C"
                        size={15}
                    />

                    <span>Fit true to size</span>
                    <ReactStars
                        count={5}
                        edit={false}
                        value={4}
                        activeColor="#FFA41C"
                        size={15}
                    />

                    <div id="seeMore">
                        <span>More feature #1</span>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={1}
                            activeColor="#FFA41C"
                            size={15}
                        />

                        <span>More feature #2</span>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={2}
                            activeColor="#FFA41C"
                            size={15}
                        />

                        <span>More feature #3</span>
                        <ReactStars
                            count={5}
                            edit={false}
                            value={3}
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
                    <button className="writeReview">Write a customer review</button>
                    <br />
                </div>

                <hr className="light" />

            </div>
        </Suspense>
    );
}

export default ReviewSummary;
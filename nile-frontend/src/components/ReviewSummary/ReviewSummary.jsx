import './ReviewSummary.css';
import axios from 'axios';
import ReactStars from 'react-rating-stars-component';
import React from 'react';
import LinearProgress from '@material-ui/core/LinearProgress'
import { withStyles } from '@material-ui/core';

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

    function displaySeeMore() {
        var x = document.getElementById("seeMore");
        var y  = document.getElementById("moreID");
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

    return (
        <div className="reviewSummary_container">
            <div className="review_row">
                <br />
                <h2>Customer reviews</h2>
                <ReactStars
                    count={5}
                    edit={false}
                    value={4.7}
                    activeColor="#FFA41C"
                    size={15}
                />
                <p className="head_rating">
                    4.7 out of 5
                </p>
                <p className="global_rating">
                    6,205 global ratings
                </p>
                <br />

                <span>5 star -  43%</span>
                <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={43} /></span>

                <span>4 star -  30%</span>
                <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={30} /></span>

                <span>3 star -  7%</span>
                <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={7} /></span>

                <span>2 star -  10%</span>
                <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={10} /></span>

                <span>1 star -  10%</span>
                <span class="bar"><BorderLinearProgress className="ratings_bar" variant="determinate" value={10} /></span>

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
                        value={5}
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
                {/* In the future will pass in product ID so review gets associated correctly */}
                {/* <Link to="/CreateReview">Write a customer review</Link> */}
                <br />
                <button className="writeReview">Write a customer review</button>
                <br />
            </div>

            <hr className="light" />

        </div>
    );
}

export default ReviewSummary;
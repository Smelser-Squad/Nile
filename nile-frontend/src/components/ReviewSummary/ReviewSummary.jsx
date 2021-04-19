import './ReviewSummary.css';
import axios from 'axios';
import ReactStars from 'react-rating-stars-component';
import React from 'react';
import LinearProgress from '@material-ui/core/LinearProgress'
import { withStyles } from '@material-ui/core';
import { Link } from "react-router-dom";


const BorderLinearProgress = withStyles((theme) => ({
    root: {
        height: 10,
        borderRadius: 5,
    },
    colorPrimary: {
        backgroundColor: theme.palette.grey[theme.palette.type === 'light' ? 200 : 700],
    },
    bar: {
        borderRadius: 5,
        backgroundColor: '#FFA41C',
    },
}))(LinearProgress);

function showSeeMore() {
    var x = document.getElementById("seeMore");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function showRatingsQuestion() {
    var x = document.getElementById("ratingsQ");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function ReviewSummary() {
    return (
        <div className="reviewSummary_container">
            <h2>Customer reviews</h2>
            <div className="review_row">
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

            </div>

            <span>1 star </span>
            <span><BorderLinearProgress variant="determinate" value={40} /></span>
            <span> 40%</span>

            <br />

            <button onClick={"showRatingsQuestion"}><span>&#709; How are ratings calculated?</span></button>

            <div id = "ratings_calc">

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

            <br />

            <hr className="light" />

            <br />

            <div className="by_feature">
                <h3>By feature</h3>
                <span>Absorbency</span>
                <ReactStars
                    count={5}
                    edit={false}
                    value={5}
                    activeColor="#FFA41C"
                    size={15}
                />

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

                <button onClick={"showSeeMore"}><span>&#709; See more</span></button>

            </div>

            <br />

            <hr className="light" />

            <br />

            <div className="write_a_review">
                <h3>Review this product</h3>
                <br />
                <span className="share_thoughts">
                    Share your thoughts with other customers
                </span>
                <br />
                <Link to="/Reviews">Write a customer review</Link>
                <br />
            </div>

            <br />

            <hr className="light" />

        </div>
    );
}

export default ReviewSummary;
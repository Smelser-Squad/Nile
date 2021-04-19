import './ReviewSummary.css';
import axios from 'axios';
import ReactStars from 'react-rating-stars-component';
import React from 'react';
import LinearProgress from '@material-ui/core/LinearProgress'
import { withStyles } from '@material-ui/core';


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
        <div class="reviewSummary_container">
            <h2>Customer reviews</h2>
            <br />
            <div class="review_row">
                <ReactStars
                    count={5}
                    edit={false}
                    value={4.7}
                    activeColor="#FFA41C"
                    size={15}
                />
                <p style="font-size: 18px">
                    4.7 out of 5
                </p>
                <br />
                <p style="font-size: 14px; color: #565959 !important">
                    6,205 global ratings
                </p>
                <br />

                <div class="5star_row">
                    <span style="font-size: 14px ;color: #007185"> 5 star
                    <BorderLinearProgress variant="determinate" value={80} /> 80%
                    </span>
                </div>
                <div class="4star_row">
                    <span style="font-size: 14px; color: #007185"> 4 star
                    <BorderLinearProgress variant="determinate" value={70} /> 70%
                    </span>
                </div>
                <div class="3star_row">
                    <span style="font-size: 14px; color: #007185"> 3 star
                    <BorderLinearProgress variant="determinate" value={60} /> 60%
                    </span>
                </div>
                <div class="2star_row">
                    <span style="font-size: 14px; color: #007185"> 2 star
                    <BorderLinearProgress variant="determinate" value={50} /> 50%
                    </span>
                </div>
                <div class="1star_row">
                    <span style="font-size: 14px; color: #007185"> 1 star
                    <BorderLinearProgress variant="determinate" value={40} /> 40%
                    </span>
                </div>
                <div class="ratings_question" style="display: inline-block">
                    <button onClick="showRatingsQuestion()" style="color: #007185 !important">&#9662;</button>
                    <span style="font-size: 14px; color: #007185"> How are ratings calculated?</span>
                    <br />
                    <div id ="ratingsQ">
                        <p>More ratings here</p>
                    </div>
                </div>
            </div>

            <hr class="light" />

            <div class="by_feature">
                <h3>By feature</h3>
                <span style="font-size: 14px; color: #0F1111; display: inline-block">
                    Feature 1
                    <ReactStars
                        count={5}
                        edit={false}
                        value={4.7}
                        activeColor="#FFA41C"
                        size={15}
                    />
                </span>
                <span style="font-size: 14px; color: #0F1111; display: inline-block">
                    Feature 2
                    <ReactStars
                        count={5}
                        edit={false}
                        value={4.7}
                        activeColor="#FFA41C"
                        size={15}
                    />
                </span>
                <span style="font-size: 14px; color: #0F1111; display: inline-block">
                    Feature 3
                    <ReactStars
                        count={5}
                        edit={false}
                        value={4.7}
                        activeColor="#FFA41C"
                        size={15}
                    />
                </span>
                <div class="see_more" style="display: inline-block">
                    <button onClick="showSeeMore()" style="color: #007185 !important">&#9662;</button>
                    <span style="font-size: 14px; color: #007185"> See more</span>
                    <br />
                    <div id ="seeMore">
                        <p>The Info</p>
                    </div>
                </div>
            </div>

            <hr class="light" />

            <div class="write_a_review">
                <h3>Review this product</h3>
                <br />
                <span style="font-size: 14px; color: #0F1111; display: inline-block">
                    Share your thoughts with other customers
                </span>
                <br />
                <a href="https://google.com" class="button">Write a customer review</a>
                <br />
            </div>

            <hr class="light" />

        </div>
    );
}

export default ReviewSummary;
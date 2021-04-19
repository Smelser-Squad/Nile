import './ReviewSummary.css';
import axios from 'axios';
import ReactStars from 'react-rating-stars-component';
import React from 'react';
import LinearProgress from '@material-ui/core/LinearProgress'

const BorderLinearProgress = withStyl((theme) => ({
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
                <p style="font-size: 14px; color: #565959">
                    6,205 globat ratings
                </p>
                <br />

                <div class="5star_row">
                    <BorderLinearProgress variant="determinate" value={80} />
                </div>
                <div class="4star_row">
                    <BorderLinearProgress variant="determinate" value={70} />
                </div>
                <div class="3star_row">
                    <BorderLinearProgress variant="determinate" value={60} />
                </div>
                <div class="2star_row">
                    <BorderLinearProgress variant="determinate" value={50} />
                </div>
                <div class="1star_row">
                    <span><BorderLinearProgress variant="determinate" value={40} /></span>
                </div>
            </div>
        </div>
    );
}

export default ReviewSummary;
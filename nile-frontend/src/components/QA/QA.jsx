import './QA.css';
import Grid from '@material-ui/core/Grid';
import axios from 'axios';
import { useState, useEffect } from 'react';

function QuestionAnswer() {
    const productId = 1;

    useEffect(()=>{
        getQuestions();
    })

    const getQuestions=async()=>{
        await axios.get(`http://localhost:80/api/qa/questions/${productId}`)
        .then(res=>{
            const questions = res.data;
            console.log(questions[0]);
            });
    }

    return(
        <div className="section">
            <h2>Customer questions & answers</h2>
            <input type="text" className="search" placeholder="Have a question? Search for answers"></input>
            <Grid container spacing={3}>
                <Grid item xs={1}>
                <img className="toggleVoteUp" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/upvote-512.png" onClick={upVote} ></img><br></br>
                    <div className="buttonText">
                        22{getQuestions().questions[0].votes}<br></br>
                        votes<br></br>
                    </div>
                    <img className="toggleVoteDown" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/downvote-512.png" onClick={downVote}></img>
                </Grid>
                <Grid item xs={6}>
                <div className="queAndAns">
                    <div className="left">
                        Question: <br></br>
                        Answer: 
                    </div>
                    <div className="right">
                        Some question asked?!<br></br>
                        Some answer given.. <br></br>
                        by userOne on 04/19/2020<br></br>
                        {3>1 ? <button className="borderlessButton" onClick={expandAnswer}> See more answers</button> : null}
                    </div>
                    <br></br>
                </div>
                </Grid>
            </Grid>
        </div>
        
    );
}
    function expandAnswer() {
        console.log("expand");
    }
    function viewAllQuestions(){

    }
    function search(){

    }
    function upVote(){
        console.log("upvote");
    }
    function downVote(){
        console.log("downvote");
    }
    function postAnswer(){

    }
    function postQuestion(){

    }


export default QuestionAnswer;
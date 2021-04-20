import './QA.css';
import Grid from '@material-ui/core/Grid';
import axios from 'axios';
import './QA.jsx';
import Answers from './Answers';

export default function Questions(props) {
    const displayQuestions = (props) => {
        const {questions} = props;
        const productId = 1;


        if(questions.length > 0){
            return(
                questions.map((question, index) => {
                    console.log(question);
                    return(
                        <Grid container spacing={3}>
                        <Grid item xs={1}>
                        <img className="toggleVoteUp" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/upvote-512.png" onClick={upVote} ></img><br></br>
                            <div className="buttonText">
                                {question.votes}<br></br>
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
                                {question.question}<br></br>
                                <Answers answers={question.answers} />
                            </div>
                            <br></br>
                        </div>
                        </Grid>
                    </Grid>
                    )
                })
                
                );
    }}

    return (<div>
        {displayQuestions(props)}
        </div>
        )
    
}

function upVote(){
    console.log("upvote");
}
function downVote(){
    console.log("downvote");
}

function expandAnswer() {
    console.log("expand");
}




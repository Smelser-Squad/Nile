import Grid from '@material-ui/core/Grid';
import axios from 'axios';
import './QA.jsx';
import Answers from './Answers';
import {useState} from 'react';
import PopUp from './addAnswerPop';

export default function Question(props) {

    const [seen, setSeen] = useState(false);
    

    function useForceUpdate(){
        const [value, setValue] = useState(0); // integer state
        return () => setValue(value => value + 1); // update the state to force render
    }
    const refresh = useForceUpdate();

    function upVote(props){
        props.question.votes++;
        axios.post(`http://localhost:80/api/qa/updateVotes/${props.question.questionId}/${props.question.votes}`, {})
            .then(res=>{        
        });
        refresh();
    }

    function downVote(props){
        props.question.votes--;
        axios.post(`http://localhost:80/api/qa/updateVotes/${props.question.questionId}/${props.question.votes}`, {})
            .then(res=>{        
        });
        refresh();
    }

    const togglePop = () => {
        setSeen(!seen);
        refresh();
    };
    
    const displayQuestions = (props) => {
        const productId = 1;
        // if(seen){
            return(<div>
                <Grid container spacing={3}>
                <Grid item xs={1}>
                <img className="toggleVoteUp" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/upvote-512.png" onClick={() => upVote(props)} ></img><br></br>
                    <div className="buttonText">                                                
                        {props.question.votes}<br></br>
                        votes<br></br>
                    </div>
                    <img className="toggleVoteDown" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/downvote-512.png" onClick={() => downVote(props)}></img>
                </Grid>
                <Grid item xs={1}>
                    <div className="queAndAns">
                        <div className="left">
                            Question: <br></br><br></br>
                            Answer: 
                        </div>
                    </div>
                </Grid>
                <Grid item xs={6}>
                    <div className="queAndAns">
                        <div className="right">
                            <div className="btn" onClick={togglePop}>
                                <span className="btnLink">{props.question.question}</span><br></br><br></br>
                            </div>
                            {seen ? <PopUp toggle={togglePop} question={props.question} /> : null}
                            <Answers answers={props.question.answers} /> 
                        </div>
                        <br></br>
                    </div>
                </Grid>
            </Grid>
            
            </div>
            )
        }
    //     else{
            
    //     }
    // }
    return (
        
    displayQuestions(props)
    )

}

    

    
    





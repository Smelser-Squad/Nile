import './QA.css';
import axios from 'axios';
import './QA.jsx';
import { useState } from 'react';

export default function PopUp(props) {
        const productId = 1;
        const [answer, setAnswer] = useState("");
        const currDate = new Date();
        const sendAbleDate = currDate.getDay() + "/" + currDate.getUTCDate() + "/" + currDate.getFullYear();
        const handleClick = () => {
            props.toggle();
        }

        function submitAnswer(props){
            var userId = 1;
            console.log(answer)
            axios.post(`http://54.82.100.75/api/qa/addAnswer/${props.question.questionId}/${userId}`, { "answer": answer, "date": sendAbleDate})
                .then(res=>{        
            });
        }

        const answerInput = (event) => {
            setAnswer(event.target.value);
        }

        return(
            <div className="modal">
                <div className="modal_content">
                    <span className="close" onClick={handleClick}> &times;</span>
                    <h2 className="answer">Question: {props.question.question}</h2>
                    <form>
                        <textarea className="post" name={answer} onChange={answerInput} placeholder="Type your answer here."></textarea>
                        <button className="subPost" onClick={() => submitAnswer(props)} >Submit Answer</button>
                    </form>
                </div>
            </div>
        );
}
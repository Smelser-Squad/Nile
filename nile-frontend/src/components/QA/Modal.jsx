import './QA.css';
import axios from 'axios';
import './QA.jsx';
import { useState } from 'react';

export default function PopUp(props) {
        const productId = 1;
        const [answer, setAnswer] = useState("");

        const handleClick = () => {
            props.toggle();
        }

        function submitAnswer(props){
            var userId = 1;
            console.log(answer)
            axios.post(`http://localhost:80/api/qa/addAnswer/${props.question.questionId}/${userId}`, { "answer": answer})
                .then(res=>{        
            });
            //props.refresh();
        }

        const answerInput = (event) => {
            setAnswer(event.target.value);
            console.log(answer);
        }

        return(
            <div className="modal">
                <div className="modal_content">
                    <span className="close" onClick={handleClick}> &times;    </span>
                    <h2>Question: {props.question.question}</h2>
                    <form>
                        <textarea name={answer} onChange={answerInput} placeholder="Type your answer here."></textarea>
                        <button onClick={() => submitAnswer(props)} >Submit Answer</button>
                    </form>
                </div>
            </div>
        );
}
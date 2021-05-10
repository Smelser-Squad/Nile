import './QA.css';
import axios from 'axios';
import './QA.jsx';
import { useState } from 'react';

export default function PopUp(props) {
        const [question, setQuestion] = useState("");

        const handleClick = () => {
            props.toggle();
        }

        function submitQuestion(props){
            axios.post(`http://54.82.100.75/api/qa/addQuestion/${props.productId}`, { "question": question, "votes": 0})
                .then(res=>{        
            });
            props.refresh();
        }

        const QuestionInput = (event) => {
            setQuestion(event.target.value);
        }

        return(
            <div className="modal">
                <div className="modal_content">
                    <span className="close" onClick={handleClick}> &times;</span>
                    <h2>Post your question</h2>
                    <form>
                        <textarea className="post" name={question} onChange={QuestionInput} placeholder="Type your question here."></textarea><br></br>
                        Your question might be answered by sellers, manufacturers, or customers who bought this product.
                        <button className="subPost" onClick={() => submitQuestion(props)} >Post</button>
                    </form>
                </div>
            </div>
        );
}
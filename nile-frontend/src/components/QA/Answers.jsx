import './QA.css';
import './QA.jsx';
import { useState, useEffect } from 'react';

export default function Answers(props) {

    const displayAnswers = (props) => {
        const {answers} = props;
        const productId = 1;

        if(answers.length > 0){
            return(
                answers.map((answer, index) => {
                    return(
                        <div>
                            <span className="answer">{answer.answer} </span><br></br>
                            by Anon{/*answer.userName*/} on 0{answer.date}<br></br>
                            {/* {3>1 ? <button className="borderlessButton" onClick={expandAnswer}> See more answers</button> : null} */}
                            <br></br>
                        </div>
                    )
                })
            );
        }
    }

    return (<div>
        {displayAnswers(props)}
        </div>
        )
    
}
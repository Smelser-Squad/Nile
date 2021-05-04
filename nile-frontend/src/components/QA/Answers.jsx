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
                            {answer.answer}<br></br>
                            by {answer.userName} on 04/19/2020<br></br>
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
import './QA.css';
import Grid from '@material-ui/core/Grid';
import axios from 'axios';
import './QA.jsx';

export default function Answers(props) {
    
    const displayAnswers = (props) => {
        const {answers} = props;
        const productId = 1;
        console.log(answers)


        if(answers.length > 0){
            return(
                answers.map((answer, index) => {
                    return(
                        <>
                            {answer.answer} <br></br>
                            by userOne on 04/19/2020<br></br>
                            {/* {3>1 ? <button className="borderlessButton" onClick={expandAnswer}> See more answers</button> : null} */}
                        </>
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
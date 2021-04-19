import './QA.css';

function QuestionAnswer() {

    return(
        <div className="section">
            <h2>Customer questions & answers</h2>
            <input type="text" className="search" placeholder="Have a question? Search for answers"></input>
            <div className="questions">
                <div className="button">
                    <img className="toggleVoteUp" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/upvote-512.png"></img><br></br>
                    <div className="buttonText">
                        45<br></br>
                        votes<br></br>
                    </div>
                    <img className="toggleVoteDown" src="https://cdn4.iconfinder.com/data/icons/neutro-award/32/downvote-512.png"></img>
                </div>
            </div>
        </div>
        
    );
    // function expandAnswer() {
        
    //     }
    // function viewAllQuestions(){

    // }
    // function search(){

    // }
}

export default QuestionAnswer;
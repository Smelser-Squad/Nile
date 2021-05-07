import './QA.css';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Questions from './Questions.jsx';
import PopUp from './addQuestionPop';

export default function QuestionAnswer(props) {
    const productId = props.productId;
    const [questions, getQuestions] = useState('');
    const [isLoading, setLoading] = useState(true);
    const [search, setSearch] = useState("");
    const [searchResults, setSearchResults] = useState('');
    const [seen, setSeen] = useState(false);

    useEffect(() => {
        axios.get(`http://localhost:80/api/qa/questions/${productId}`)
            .then(res => {
                const quest = res.data;
                getQuestions(quest);
                setLoading(false);
            });
    }, []);

    // const getAllQuestions = async()=>{
    //     return await axios.get(`http://localhost:80/api/qa/questions/${productId}`)
    //     .then(res=>{
    //         const quest = res.data;
    //         getQuestions(quest);
    //         setLoading(false);
    //         });
    // }

    const searchInput = (event) => {
        setSearch(event.target.value.toLowerCase());
        searchThrough(event.target.value.toLowerCase());
    }

    const searchThrough = (toSearch) => {
        var listAvailable = [];
        questions.map((question, index) => {

            if (question.question.includes(toSearch)) {
                listAvailable.push(question);
            }
            else {
                if (question.answers.length > 0) {
                    question.answers.map((answer, index) => {
                        if (answer.answer.includes(toSearch)) {
                            if (!listAvailable.includes(question)) {
                                listAvailable.push(question);
                            }
                        }
                    });
                }
            }
        });
        setSearchResults(listAvailable);

    }

    const togglePop = () => {
        setSeen(!seen);
        refresh();
    };

    function useForceUpdate() {
        const [value, setValue] = useState(0); // integer state
        return () => setValue(value => value + 1); // update the state to force render
    }
    const refresh = useForceUpdate();

    if (isLoading) {
        return <div className="section">
            <h2>Customer questions & answers</h2>
            <input type="text" name={search} className="search" placeholder="Have a question? Search for answers" onChange={searchInput} ></input>
        </div>
    }

    return (
        <div className="section">
            <h2>Customer questions & answers</h2>
            <input type="text" name={search} className="search" placeholder="Have a question? Search for answers" onChange={searchInput} ></input>
            <br></br>
            {search === "" ? <Questions questions={questions} /> : <>
                {searchResults.length > 0 ? <Questions questions={searchResults} /> : null}
                <div className="addQuestionBtn">
                    Don't see the answer you're looking for?
                        <button className="toggleQuestion" onClick={togglePop}>Post your question</button>
                    {seen ? <PopUp toggle={togglePop} productId={productId} /> : null}
                </div>
            </>}
        </div>
    );
}

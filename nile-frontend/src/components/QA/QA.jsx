import './QA.css';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Questions from './Questions.jsx';
import Question from './SingleQuestion';

export default function QuestionAnswer() {
    const productId = 1;
    const [questions, getQuestions] = useState('');
    const [isLoading, setLoading] = useState(true);
    const [search, setSearch] = useState("");

    useEffect(()=>{
        axios.get(`http://localhost:80/api/qa/questions/${productId}`)
        .then(res=>{
            const quest = res.data;
            getQuestions(quest);
            setLoading(false);
            });
    },[]);

    // const getAllQuestions = async()=>{
    //     return await axios.get(`http://localhost:80/api/qa/questions/${productId}`)
    //     .then(res=>{
    //         const quest = res.data;
    //         getQuestions(quest);
    //         setLoading(false);
    //         });
    // }
    
    const searchInput = (event) => {
        setSearch(event.target.value);
        console.log(search);
    }

    if(isLoading){
        return <div className="section">
        <h2>Customer questions & answers</h2>
        <input type="text" name={search} className="search" placeholder="Have a question? Search for answers" onChange={searchInput} ></input>
    </div>
    }

    return(
        <div className="section">
            <h2>Customer questions & answers</h2>
            <input type="text" name={search} className="search" placeholder="Have a question? Search for answers" onChange={searchInput} ></input>
            <button onClick={()=> console.log(search)}>check</button>
            <Questions questions={questions} />
        </div>
        );
}

    
    function viewAllQuestions(){

    }
    function search(){

    }
    
    function postAnswer(){

    }
    function postQuestion(){

    }

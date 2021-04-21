import './QA.css';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Questions from './Questions.jsx';

export default function QuestionAnswer() {
    const productId = 1;
    const [questions, getQuestions] = useState('');

    useEffect(()=>{
        getAllQuestions();
    },[]);

    const getAllQuestions = async()=>{
        return await axios.get(`http://localhost:80/api/qa/questions/${productId}`)
        .then(res=>{
            const quest = res.data;
            console.log(quest);
            getQuestions(quest);
            });
    }

    return(
        <div className="section">
            <h2>Customer questions & answers</h2>
            <input type="text" className="search" placeholder="Have a question? Search for answers"></input>
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

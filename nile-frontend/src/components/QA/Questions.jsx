import './QA.css';
import './QA.jsx';
import Question from "./SingleQuestion";

export default function Questions(props) {
        console.log(props);
        const {questions} = props;
        console.log(questions);
        const quest = questions.map((question, index) => {
            return( <Question question={question} />)
        });
        return quest;
}



function expandAnswer() {
    console.log("expand");
}




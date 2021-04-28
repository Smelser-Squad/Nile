import './QA.css';
import './QA.jsx';
import Question from "./SingleQuestion";

export default function Questions(props) {
        const {questions} = props;
        const quest = questions.map((question, index) => {
            return( <Question question={question} />)
        });
        return quest;
}



function expandAnswer() {
    console.log("expand");
}




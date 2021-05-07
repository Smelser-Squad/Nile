import './Comparison.jsx';
import Table from './Table.jsx';

export default function PopUp(props) {
  const handleClick = () => {
    props.toggle();
  }

  return (
    <div className="popup">
      <div className="popup_content">
        <div className="popup_header">
          <h2>Compare {props.type} Products</h2>
        </div>
        <Table columns={props["columns"]} data={props["data"]} numReviews={props["numReviews"]} ids={props["ids"]} />
        <button onClick={handleClick}>Close</button>
      </div>
    </div>
  )
}
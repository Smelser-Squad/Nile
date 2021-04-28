  import './ProductColorSelector.css'
  import {useState} from 'react'
  import { getListColors } from '../../service/PhotoService';
  import Radio from '@material-ui/core/Radio';
  import FormControlLabel from '@material-ui/core/FormControlLabel';
  import { useParams } from 'react-router';
  

  

  export function ProductColorSelector({setProductColor}){
    
    const [color, setColor] = useState('');
    const {productId}=useParams();
    const [cards,setCards]=useState([]);


    const onRadioClick=(color)=>{
      setProductColor(color.colorName);
      // setColor(color.colorName);
      console.log(color);
      console.log(color.colorName);
    }

  
    
    getListColors(productId).then((name)=>{
      console.log(name[0].colorName)
      setProductColor(name[0]);
      setColor(name[0]);
      
     const cards = name.map((colorName) =>
        <div className="color_selector">
            
             <FormControlLabel value={colorName} control={<Radio name="color" checked={color.colorName} onClick={()=>onRadioClick({colorName})}/>} label={colorName}/>
       
        </div>
      )
       setCards(cards);
    });
    
    return(<div>
      Color: {color}
      <br/>
      {cards}
     
      </div>);

  }
  
 
 


  




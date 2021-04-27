  import './ProductColorSelector.css'
  import {useState, useEffect} from 'react'
  import { getPhotos } from '../../service/PhotoService';
  import Radio from '@material-ui/core/Radio';
  import FormControlLabel from '@material-ui/core/FormControlLabel';
  import { useParams } from 'react-router';
  import axios from 'axios'

  

  export function ProductColorSelector(){
    
    const [color, setColor] = useState([]);
    const {productId}=useParams();
    const ColorList=[];
    const [cards,setCards]=useState([]);

    
    getPhotos(productId).then((list)=>{
      list.map((item)=>{
        ColorList.push(item.color)
      })

        
      
     const cards = ColorList.map((colorName) =>
        <div className="color_selector">
            
             <FormControlLabel value={colorName} control={<Radio name="color" checked={color===colorName} onChange={()=>setColor(colorName)}/>} label={colorName}/>
       
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
  
 
 


  




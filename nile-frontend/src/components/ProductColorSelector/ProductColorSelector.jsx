  import './ProductColorSelector.css'
  import {useState, useEffect} from 'react'
  import { getListColors } from '../../service/PhotoService';
  import Radio from '@material-ui/core/Radio';
  import FormControlLabel from '@material-ui/core/FormControlLabel';
  import { useParams } from 'react-router';
  import axios from 'axios'
import SingleProductListing from '../ProductListing/SingleProductListing';
import ProductPhotos from '../ProductPhotos/ProductPhotos';

  

  export function ProductColorSelector({setProductColor}){
    
    const [color, setColor] = useState('');
    const {productId}=useParams();
    const ColorList=[];
    const [cards,setCards]=useState([]);

    // console.log(color.colorName);

    const onRadioClick=(color)=>{
      setProductColor(color.colorName);
      console.log(color.colorName);
    }
   
    
    getListColors(productId).then((list)=>{
      list.map((item)=>{
        ColorList.push(item)
      })

   
    
      
     const cards = ColorList.map((colorName) =>
        <div className="color_selector">
            
             <FormControlLabel value={colorName} control={<Radio name="color" checked={color===colorName}  onClick={()=>onRadioClick({colorName})}/>} label={colorName}/>
       
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
  
 
 


  




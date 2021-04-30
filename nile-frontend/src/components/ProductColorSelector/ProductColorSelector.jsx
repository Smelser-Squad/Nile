import './ProductColorSelector.css'
 import {useEffect, useState} from 'react'
 import { getListColors } from '../../service/PhotoService';
 import Radio from '@material-ui/core/Radio';
 import FormControlLabel from '@material-ui/core/FormControlLabel';
 import { useParams } from 'react-router';
import {  RadioGroup } from '@material-ui/core';


 
 
 export function ProductColorSelector({setProductColor,defaultColor}){
 
    const [color, setColor] = useState('')
    const {productId}=useParams();
    const [button,setButton]=useState('');
    const colorList = [];
    
    const onRadioClick=(color)=>{
        setProductColor(color);
        
    }
 
const fetchColors= async ()=>{
      const apiCall=await fetch (`http://localhost:80/api/productPhotos/colors/${productId}`);
      const color=await apiCall.json();
      setColor(color);
      
     
}
 
if(color.length===0){
  getListColors(productId).then((list)=>
  {
    list.map((colorName)=>
    colorList.push(colorName),
    );
 const button = colorList.map((color) => 
  
<div className="color_selector">
            
            <FormControlLabel value={color} control={<Radio name="color" onClick={()=>onRadioClick(color)}/>} label={color}/>
      
       </div>
      // <label>
      //   <input type="radio" value={color} name="color" label={color} onClick={() => onRadioClick(color)}/> {color}
      //   </label>
  );
  setButton(button)
        }
  );
      }    
  
 useEffect(()=>{
  fetchColors();
}, [productId])
 return(<div>
 Color: {defaultColor}
 <br/>
  <RadioGroup>
      {button}
  </RadioGroup>
    
  
 
 
 </div>);
 
}
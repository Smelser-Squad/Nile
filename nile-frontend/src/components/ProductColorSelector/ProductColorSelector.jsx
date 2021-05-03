import './ProductColorSelector.css'
 import {useEffect, useState} from 'react'
 import { getListColors } from '../../service/PhotoService';
 import { useParams } from 'react-router';
import { RadioGroup } from '@material-ui/core';

 export function ProductColorSelector({setProductColor, setDefaultColor}){
 
  const [color, setColor] = useState('')
  const {productId}=useParams();
  const [button,setButton]=useState('');
  const colorList = [];

  setDefaultColor(color[0]);
  
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
    <label>
      <input type="radio" value={color} name="color" label={color} onClick={() => onRadioClick(color)}/> {color}
    </label>
  );

  setButton(button)
  });
}    
  

  useEffect(()=>{
    fetchColors();
  }, [productId])

 return(
  <div>
    Color: {color}
    <br/>
    <RadioGroup>
      {button}
    </RadioGroup>
  </div>
 );
 
}

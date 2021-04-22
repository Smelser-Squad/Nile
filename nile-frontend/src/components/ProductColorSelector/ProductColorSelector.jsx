import './ProductColorSelector.css'
import {useState} from 'react'
import { getPhotos } from '../../service/PhotoService';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import { useParams } from 'react-router';


export function ProductColorSelector(){
  
  const [color, setColor] = useState([]);
  const {productId}=useParams();
  const ColorList=[];

  const handleChange = (event) => {
    setColor(event.target.value);
  };

  getPhotos(productId).then((list)=>{
    list.map((item)=>{
      ColorList.push(item.color)
    })
  })
 
console.log(ColorList[0]);

    return(<div>
  
    <FormControl component="fieldset">
    <FormLabel component="legend">Color: {color}</FormLabel>
    <RadioGroup row aria-label="color" name="color" value={color} onChange={handleChange}>
    <FormControlLabel value="Female" control={<Radio />} label="Female" />
    <FormControlLabel value="Male" control={<Radio />} label="Male" />
    <FormControlLabel value="Other" control={<Radio />} label="Other" />
   
  </RadioGroup>
</FormControl>

    </div>)
}

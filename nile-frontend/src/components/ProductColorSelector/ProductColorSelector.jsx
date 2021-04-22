  import './ProductColorSelector.css'
  import {useState, useEffect} from 'react'
  import { getPhotos } from '../../service/PhotoService';
  import Radio from '@material-ui/core/Radio';
  import RadioGroup from '@material-ui/core/RadioGroup';
  import FormControlLabel from '@material-ui/core/FormControlLabel';
  import FormControl from '@material-ui/core/FormControl';
  import FormLabel from '@material-ui/core/FormLabel';
  import { useParams } from 'react-router';
  import axios from 'axios'
  import { colors } from '@material-ui/core';

  export function ProductColorSelector(){
    
    const [color, setColor] = useState([]);
    const {productId}=useParams();
    const ColorList=[];
    const [cards,setCards]=useState([]);

    const handleChange = (event) => {
      setColor(event.target.value);
    };
    
    getPhotos(productId).then((list)=>{
      list.map((item)=>{
        ColorList.push(item.color)
      })

        
      
     const cards = ColorList.map((colorName) =>
        <div>
        <FormLabel component="legend">Color: {colorName}</FormLabel>
            <RadioGroup row aria-label="color" name="color" value={colorName} onChange={handleChange}>
             <FormControlLabel value={colorName} control={<Radio />} label={colorName} />
            </RadioGroup>
       
        </div>
      )
      setCards(cards);
    });
    return(<div>
      
      {cards}</div>);
  }
  
 
  //   useEffect(() => {
  //     axios.get(`http://localhost:80/api/productPhotos/${productId}/${color}`)
  //         .then(res => {
  //             setColorPhotos(res.data);
  //             console.log(res.data);



  //         })
  // }, [])


  




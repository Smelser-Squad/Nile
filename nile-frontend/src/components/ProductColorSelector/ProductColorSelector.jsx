import './ProductColorSelector.css'
import {useState} from 'react'

export function ProductColorSelector(){
  
  const [color, setColor] = useState([]);
  const [ColorPhoto, setColorPhotos] = useState([]);
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
    cards = ColorList.map((color) =>
      <div>
        <p>Im Here!!!!!!</p>
        <FormControl component="fieldset">
          <FormLabel component="legend">Color: {color}</FormLabel>
          <RadioGroup row aria-label="color" name="color" value={color} onChange={handleChange}>
            <FormControlLabel value={color} control={<Radio />} label={color} />
          </RadioGroup>
        </FormControl>
      </div>
    )
    setCards(cards);
  });

}
  

import './ProductColorSelector.css'
import {useState} from 'react'

export function ProductColorSelector(){
  
  const [color, setColor] = useState([]);

    return(<div>
        <label>Color: {color}</label>
<ul class="radio color">
  <li class="red">
    <input type="radio" name="color" id="color_red" value="red" onClick={() => setColor('Red')} />
    <label for="color_red">Red</label>
  </li>
  <li class="green">
    <input type="radio" name="color" id="color_green" value="green" onClick={() => setColor('Green')} />
    <label for="color_green">Green</label>
  </li>
 
</ul>
    </div>)
}

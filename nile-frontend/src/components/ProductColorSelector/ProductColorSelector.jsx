import './ProductColorSelector.css'

export function ProductColorSelector(){
    
    return(<div>
        <label>Color</label>
<ul class="radio color">
  <li class="red">
    <input type="radio" name="color" id="color_red" value="red" />
    <label for="color_red">Red</label>
  </li>
  <li class="green">
    <input type="radio" name="color" id="color_green" value="green" />
    <label for="color_green">Green</label>
  </li>
 
</ul>
    </div>)
}

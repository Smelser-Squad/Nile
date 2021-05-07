import './ProductColorSelector.css'
import { useEffect, useState } from 'react'
import { getListColors } from '../../service/PhotoService';
import { useParams } from 'react-router';
import { RadioGroup } from '@material-ui/core';
import Radio from '@material-ui/core/Radio';
import FormControlLabel from '@material-ui/core/FormControlLabel';




export function ProductColorSelector({ setProductColor, setDefaultColor }) {

  const [color, setColor] = useState('')
  const { productId } = useParams();
  const [button, setButton] = useState('');
  const colorList = [];


  setDefaultColor(color[0]);

  const onRadioClick = (color) => {
    setProductColor(color);
  }

  const fetchColors = async () => {
    const apiCall = await fetch(`http://localhost:80/api/productPhotos/colors/${productId}`);
    const color = await apiCall.json();
    setColor(color);


  }

  if (color.length === 0) {
    getListColors(productId).then((list) => {
      list.map((colorName) =>
        colorList.push(colorName),
      );
      const button = colorList.map((color) =>

        <div className="color_selector">

          <FormControlLabel value={color} control={<Radio name="color" onClick={() => onRadioClick(color)} />} label={color} />

        </div>

      );

      setButton(button)
    });
  }


  useEffect(() => {
    fetchColors();
  }, [productId])

  return (
    <div className="color_selector_name">
      Color:
      <br />
      <RadioGroup>
        {button}
      </RadioGroup>
    </div>
  );

}

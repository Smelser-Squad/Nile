import React, {useEffect, useState} from 'react';
import axios from 'axios';

import './Comparison.css'

function Comparison(props) {

  async function getProductSpecsByType() {
    if (props.product.type != null) {
      await axios.get(`http://localhost:80/api/reviews/products/type/${props.product.type.typeName}`)
        .then(res => {
          console.log(res.data);
        });
      }
  }

  useEffect(() => {
    getProductSpecsByType();
  }, [])

  return (
    <div className='comparison'>
      <div className='comparison_container'>
        {props.product.productId}
      </div>
    </div>
  )
}

export default Comparison
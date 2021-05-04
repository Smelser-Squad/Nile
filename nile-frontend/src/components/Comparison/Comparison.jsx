import React, {useEffect, useState} from 'react';
import { getProduct, getTypeProducts } from '../../service/ProductService'
import { getProductSpecsById, getSpecById } from '../../service/SpecService'
import { getAverageReviewScore } from '../../service/ReviewService'
import './Comparison.css'
import Table from './Table'

async function createTableData(products, specIds) {
  let data = [];
  let columns = [];
  let numReviews = [];
  let tableData = {};

  let firstColumn = {
    Header: "",
    accessor: "spec"
  };
  columns.push(firstColumn);

  for (let i = 0; i < products.length; i++) {
    let currImage = {
      Header: props => <img src={products[i].photos[0].imageSrc}></img>,
      accessor: products[i].name + "_img",
      Cell: props => <img src={products[i].photos[0].imageSrc}></img>,
      columns: []
    }
    let currColumn = {
      Header: products[i].name,
      accessor: products[i].name
    }
    currImage.columns.push(currColumn);
    columns.push(currImage);
  }

  let priceRow = {};
  priceRow["spec"] = "Price";
  for (let i = 0; i < products.length; i++) {
    priceRow[products[i].name] = "From $"+products[i].price.toFixed(2);
  }
  data.push(priceRow);

  let ratingRow = {};
  ratingRow["spec"] = "Ratings";
  for (let i = 0; i < products.length; i++) {
    const currReviewInfo = await getAverageReviewScore(products[i].productId);
    ratingRow[products[i].name] = currReviewInfo.avg;
    numReviews[i] = currReviewInfo.length;
  }
  data.push(ratingRow);

  for (let i = 0; i < specIds.length; i++) {
    let currRow = {};
    for (let j = 0; j < products.length; j++) {
      let currProdSpec = await getProductSpecsById(products[j].productId, specIds[i])
      let currSpec = await getSpecById(specIds[i])

      if (currProdSpec != null) {
        currRow[products[j].name] = currProdSpec.value;
      } else {
        currRow[products[j].name] = "";
      }
      currRow["spec"] = currSpec.specName;
    }
    data.push(currRow);
  }
  
  tableData["data"] = data;
  tableData["columns"] = columns;
  tableData["numReviews"] = numReviews;
  return tableData;
}

function Comparison() {

  const [isLoading, setLoading] = useState(true);
  const [products, setProducts] = useState([]);
  const [specIds, setSpecIds] = useState([]);
  const [tableData, setTableData] = useState({});
  const productId = document.URL.substring(43);
  const typeProducts = [];
  const allSpecIds = [];
  const [columns, setColumns] = useState([]);

  if (products.length === 0) {
    getProduct(productId).then(curr => {
      getTypeProducts(curr.type.typeName).then(res => {
        res.map(prod => {
          typeProducts.push(prod);
        });
        setProducts(typeProducts);
        for (let i = 0; i < typeProducts.length; i++) {
          for (let j = 0; j < typeProducts[i].productSpecs.length; j++) {
            let currSpecId = typeProducts[i].productSpecs[j].id.specId;
            if (!allSpecIds.includes(currSpecId)) {
              allSpecIds.push(currSpecId);
            }
          }
        }
        createTableData(typeProducts, allSpecIds).then(res => {
          setTableData(res);
          setLoading(false);
        });
      })
    })
  }

  if (isLoading || tableData === {}) {
    return <div className='comparison'>Loading...</div>
  }

  return (
    <div className="Comparison">
      <Table columns={tableData["columns"]} data={tableData["data"]} numReviews={tableData["numReviews"]} />
    </div>
  )
}

export default Comparison
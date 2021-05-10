import React, { useEffect, useState } from 'react';
import { getProduct, getTypeProducts } from '../../service/ProductService'
import { getProductSpecsById, getSpecById } from '../../service/SpecService'
import { getAverageReviewScore } from '../../service/ReviewService'
import './Comparison.css'
import Table from './Table'
import { LocalConvenienceStoreOutlined, Refresh } from '@material-ui/icons';
import PopUp from './PopUp';

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
      Header: <p id="product-name" onClick={() => { window.location.href = `/singleProductListing/${products[i].productId}` }} >{products[i].name}</p>,
      accessor: products[i].name
    }
    currImage.columns.push(currColumn);
    columns.push(currImage);
  }

  let priceRow = {};
  priceRow["spec"] = "Price";
  for (let i = 0; i < products.length; i++) {
    priceRow[products[i].name] = "From $" + products[i].price.toFixed(2);
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
  const [ids, setIds] = useState([]);
  const [type, setType] = useState();
  const [tableData, setTableData] = useState({});
  const productId = document.URL.substring(43);
  const [seen, setSeen] = useState(false);

  function useForceUpdate() {
    const [value, setValue] = useState(0); // integer state
    return () => setValue(value => value + 1); // update the state to force render
  }
  const refresh = useForceUpdate();

  useEffect(() => {
    const typeProducts = [];
    const productIds = [];
    const allSpecIds = [];
    if (products.length === 0) {
      getProduct(productId).then(curr => {
        setType(curr.type.typeName);
        getTypeProducts(curr.type.typeName).then(res => {
          res.map(prod => {
            productIds.push(prod.productId);
            typeProducts.push(prod);
          });
          setIds(productIds);
          setProducts(typeProducts);
          if (typeProducts.length > 1) {
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
            });
          } else {
          }
          setLoading(false);
        })
      })
    }
  }, [])

  const togglePop = () => {
    console.log(seen);
    setSeen(!seen);
    refresh();
    console.log(seen);
  }

  if (products.length < 2) {
    return <div className='comparison'></div>
  }

  if (isLoading || tableData === {}) {
    return <div className='comparison'>Loading...</div>
  }

  return (
    <div className="comparison">
      {seen ? <PopUp toggle={togglePop} columns={tableData["columns"]} data={tableData["data"]} numReviews={tableData["numReviews"]} type={type} ids={ids} /> : null}
      <button className="comaprison_button" onClick={togglePop}>Compare Products</button>
    </div>
  )
}

export default Comparison
import React, {useEffect, useState} from 'react';
import { useTable } from 'react-table';
import { getProduct, getTypeProducts } from '../../service/ProductService'
import { getProductSpecsById } from '../../service/SpecService'
import './Comparison.css'
import Table from './Table'

async function createTableData(products, specIds) {
  let data = [];
  let columns = [];
  let tableData = {};
  console.log("products.length: " + products.length)
  console.log("Specs: " + specIds);
  for (let i = 0; i < specIds.length; i++) {
    let currRow = {};
    for (let j = 0; j < products.length; j++) {
      console.log(products[j]);
      getProductSpecsById(products[j].productId, specIds[i]).then(currProdSpec => {
        if (currProdSpec != null) {
          currRow[products[j].name] = currProdSpec.value;
        } else {
          currRow[products[j].name] = "N/A";
        }
        if (currRow["spec"] === undefined) {
          currRow["spec"] = currProdSpec.spec.specName;
        }
      });
    }
    console.log(JSON.stringify(currRow));
    data.push(currRow);
  }
  let firstColumn = {
    Header: "",
    accessor: "spec"
  };
  columns.push(firstColumn);
  for (let i = 0; i < products.length; i++) {
    let currColumn = {
      Header: products[i].name,
      accessor: products[i].name
    }
    columns.push(currColumn);
  }
  tableData["data"] = data;
  tableData["columns"] = columns;
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
  const [data, setData] = useState([]);

  if (products.length === 0) {
    getProduct(productId).then(curr => {
      getTypeProducts(curr.type.typeName).then(res => {
        res.map(prod => {
          typeProducts.push(prod);
        });
        setProducts(typeProducts);
        console.log("typeProducts: " + JSON.stringify(typeProducts[0]));
        for (let i = 0; i < typeProducts.length; i++) {
          console.log("Current length of products specs: " + typeProducts[i].productSpecs.length);
          for (let j = 0; j < typeProducts[i].productSpecs.length; j++) {
            console.log("Current product specs: " + JSON.stringify(typeProducts[i].productSpecs));
            if (!allSpecIds.includes(typeProducts[i].productsSpecs[j].id.specId)) {
              allSpecIds.push(typeProducts[i].productsSpecs[j].id.specId);
              console.log("ADDING SPEC...");
              console.log(allSpecIds);
            }
          }
        }
        createTableData(typeProducts, allSpecIds).then(res => {
          setTableData(res);
          console.log("Table data: " + JSON.stringify(tableData));
          setColumns(tableData['columns']);
          setData(tableData.data);
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
      Test
    </div>
  )
}

export default Comparison
import React, {useEffect, useState} from 'react';
import { useTable } from 'react-table';
import { getProduct, getTypeProducts } from '../../service/ProductService'
import { getProductSpecsById, getSpecById } from '../../service/SpecService'
import './Comparison.css'
import Table from './Table'

async function createTableData(products, specIds) {
  let data = [];
  let columns = [];
  let tableData = {};
  console.log("products.length: " + products.length)
  console.log("Specs: " + specIds);
  console.log("data: " + data);
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
  for (let i = 0; i < specIds.length; i++) {
    let currRow = {};
    for (let j = 0; j < products.length; j++) {
      console.log(products[j]);
      let currProdSpec = await getProductSpecsById(products[j].productId, specIds[i])
      let currSpec = await getSpecById(specIds[i])
      console.log("currSpec: " + JSON.stringify(currSpec))

      if (currProdSpec != null) {
        currRow[products[j].name] = currProdSpec.value;
      } else {
        currRow[products[j].name] = "N/A";
      }
      currRow["spec"] = currSpec.specName;
      console.log("currRow: " + JSON.stringify(currRow));
    }
    data.push(currRow);
    
    //data.push(currRow);
  }
  
  tableData["data"] = data;
  console.log("tableData: " + JSON.stringify(tableData));
  tableData["columns"] = columns;
  console.log("tableData: " + JSON.stringify(tableData));
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
            let currSpecId = typeProducts[i].productSpecs[j].id.specId;
            console.log("currSpecId: " + currSpecId);
            if (!allSpecIds.includes(currSpecId)) {
              allSpecIds.push(currSpecId);
              console.log("ADDING SPEC...");
              console.log(allSpecIds);
            }
          }
        }
        createTableData(typeProducts, allSpecIds).then(res => {
          console.log("createTable res: " + JSON.stringify(res));
          setTableData(res);
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
      <Table columns={tableData["columns"]} data={tableData["data"]} />
    </div>
  )
}

export default Comparison
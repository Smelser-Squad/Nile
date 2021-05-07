import React, { Suspense } from 'react';
import { useTable } from 'react-table';
import './Table.css'

export default function Table({ columns, data, numReviews, ids }) {
  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow
  } = useTable({ columns, data });

  const ReactStars = React.lazy(() => import('react-rating-stars-component'));

  let idCount = 0;
  let reviewCount = 0;

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <table {...getTableProps()}>
        <thead>
          {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(column => (
                <th {...column.getHeaderProps()}>{column.render("Header")}</th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.slice(0,1).map(row => {
              prepareRow(row);
              return (
                <tr {...row.getRowProps()}>
                  {row.cells.map(cell => {
                    return <td {...cell.getCellProps()}>{cell.render("Cell")}</td>;
                  })}
                </tr>
              );
          })}
          {rows.slice(1,2).map(row => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => {
                  if (cell.value === "Ratings") {
                    return <td {...cell.getCellProps()}>{cell.render("Cell")}</td>;
                  } else {
                    const currNumReviews = numReviews[reviewCount];
                    reviewCount++;
                    return <td {...cell.getCellProps()}>
                      <div class="stars">
                        <ReactStars
                            count={5}
                            edit={false}
                            value={parseInt(cell.value)}
                            activeColor="#FFA41C"
                            size={15}
                            isHalf={true}
                        />
                        <span class="numReviews">
                          ({currNumReviews})
                        </span>
                      </div>
                    </td>
                  }
                })}
              </tr>
            );
          })}
          {rows.slice(2).map((row, i) => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => {
                  return <td {...cell.getCellProps()}>{cell.render("Cell")}</td>;
                })}
              </tr>
            );
          })}
        </tbody>
      </table>
    </Suspense>
  )
}
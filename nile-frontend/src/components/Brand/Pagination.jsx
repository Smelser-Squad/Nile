import React from "react"
import {useParams} from 'react-router'

 

function Pagination ({postsPerPage, totalPosts, paginate}){
    const { productId } = useParams();
    const pageNums = [];
    for(let i = 1; i<=Math.ceil(totalPosts/postsPerPage);i++)
    {
        pageNums.push(i);
    }
    return (
        <nav>
            <ul className="pages">
                {pageNums.map(number=>(
                <li key={number} className="page-item">
                    <a onClick={()=> paginate(number)} href= "#" className="page-link">
                        {number}
                    </a>
                </li>
                ))}
            </ul>
        </nav>
    )
      {/* <Pagination onChange={()=>{handlePageChange()}} count={FilteredList.length/itemsPerPage} defaultPage={1} boundaryCount={2} /> */}

}
export default Pagination;
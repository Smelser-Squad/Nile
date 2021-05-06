import React from "react"
import "./Pagination.css"
import {useParams} from 'react-router'

const Pagination = ({postsPerPage, totalPosts, paginate})=>{

    const { productId } = useParams();
    const pageNums = [];

    for(let i = 1; i<=Math.ceil(totalPosts/postsPerPage);i++)
    {
        pageNums.push(i);
    }

    return (
        <nav class="page-nav">
            <ul className="pagination">
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
}

export default Pagination
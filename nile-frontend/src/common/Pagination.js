import React from "react"
import "./Pagination.css"

const Pagination = ({ postsPerPage, totalPosts, paginate }) => {
    const pageNums = [];

    for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
        pageNums.push(i);
    }

    return (
        <nav class="page-nav">
            <ul className="pagination">
                {pageNums.map(number => (
                    <li key={number} className="page-item">
                        <a onClick={() => paginate(number)} href="#" className="page-link">
                            {number}
                        </a>
                    </li>
                ))}
            </ul>
        </nav>
    )
}

export default Pagination
import { useEffect, useState } from "react";
// import Next from "./icon/Next";
// import Prev from "./icon/Prev";
// import DoubleNext from "./icon/DoubleNext";
// import DoublePrev from "./icon/DoublePrev";
import "./css/Pagenation.css";

const Pagenation = () => {
  const mockPage = {
    currentPage: 2,
    totalPages: 11,
    startPage: 1,
    endPage: 10,
    hasPrevious: false,
    hasNext: true,
  };
  const [pageInfo, setInfo] = useState(mockPage);
  const [curPage, setcurPage] = useState(1);
  const [pageRange, setPageRange] = useState([]);
  useEffect(() => {
    setPageRange(writePages(mockPage.startPage, mockPage.endPage));
  }, [curPage]);
  const writePages = (s, e) => {
    const tempRange = [];
    for (let i = s; i <= e; i++) {
      tempRange.push(i);
    }
    return tempRange;
  };
  const curPageHandler = (n) => {
    setcurPage(n);
    console.log(n);
  };
  return (
    <ul className="pagenation-content">
      {curPage > pageInfo.startPage && (
        <li>
          <button
            onClick={() => {
              curPageHandler(1);
            }}
            className="pagenation-icon-button"
          >
            {/* <DoublePrev /> */}
          </button>
        </li>
      )}
      {pageInfo.hasPrevious && (
        <li>
          <button
            onClick={() => {
              curPageHandler(curPage - 10);
            }}
            className="pagenation-icon-button"
          >
            {/* <Prev /> */}
          </button>
        </li>
      )}
      {pageRange.map((pageNum) => (
        <li key={pageNum}>
          <button
            onClick={() => {
              curPageHandler(pageNum);
            }}
            disabled={pageNum === curPage}
            className={
              pageNum === curPage ? "active" : "pagenation-icon-button"
            }
          >
            {pageNum}
          </button>
        </li>
      ))}
      {pageInfo.hasNext && (
        <li>
          <button
            onClick={() => {
              curPageHandler(curPage + 10);
            }}
            className="pagenation-icon-button"
          >
            {/* <Next /> */}
          </button>
        </li>
      )}

      {curPage < pageInfo.endPage && (
        <li>
          <button
            onClick={() => {
              curPageHandler(pageInfo.endPage);
            }}
            className="pagenation-icon-button"
          >
            {/* <DoubleNext /> */}
          </button>
        </li>
      )}
    </ul>
  );
};
export default Pagenation;

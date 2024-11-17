import { useEffect, useState } from "react";
import Next from "./iconFolder/Next";
import Prev from "./iconFolder/Prev";
import DoubleNext from "./iconFolder/DoubleNext";
import DoublePrev from "./iconFolder/DoublePrev";
import "./css/Pagenation.css";

const Pagenation = ({
  currentPage,
  totalPages,
  startPage,
  endPage,
  hasPrevious,
  hasNext,
  CallAxios,
}) => {
  const [pageInfo, setPageInfo] = useState({
    currentPage: currentPage,
    totalPages: totalPages,
    startPage: startPage,
    endPage: endPage,
    hasPrevious: hasPrevious,
    hasNext: hasNext,
  });

  const [pageRange, setPageRange] = useState([]);
  useEffect(() => {
    setPageInfo({
      currentPage: currentPage,
      totalPages: totalPages,
      startPage: startPage,
      endPage: endPage,
      hasPrevious: hasPrevious,
      hasNext: hasNext,
    });
    setPageRange(writePages(startPage, endPage));
  }, [currentPage, totalPages, startPage, endPage, hasPrevious, hasNext]);
  const writePages = (s, e) => {
    const tempRange = [];
    for (let i = s; i <= e; i++) {
      // console.log(i);
      tempRange.push(i);
    }
    return tempRange;
  };
  const curPageHandler = (n) => {
    CallAxios(n);
    console.log(n);
  };

  return (
    <ul className="pagenation-content">
      {pageInfo.currentPage > pageInfo.startPage && (
        <li>
          <button
            onClick={() => {
              curPageHandler(1);
            }}
            className="pagenation-icon-button"
          >
            <DoublePrev />
          </button>
        </li>
      )}
      {pageInfo.hasPrevious && (
        <li>
          <button
            onClick={() => {
              curPageHandler(startPage - 10);
            }}
            className="pagenation-icon-button"
          >
            <Prev />
          </button>
        </li>
      )}
      {pageRange.map((pageNum) => (
        <li key={pageNum}>
          <button
            onClick={() => {
              curPageHandler(pageNum);
            }}
            disabled={pageNum === pageInfo.currentPage}
            className={
              pageNum === pageInfo.currentPage
                ? "active"
                : "pagenation-icon-button"
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
              curPageHandler(startPage + 10);
            }}
            className="pagenation-icon-button"
          >
            <Next />
          </button>
        </li>
      )}

      {pageInfo.currentPage < pageInfo.endPage && (
        <li>
          <button
            onClick={() => {
              curPageHandler(totalPages);
            }}
            className="pagenation-icon-button"
          >
            <DoubleNext />
          </button>
        </li>
      )}
    </ul>
  );
};
export default Pagenation;

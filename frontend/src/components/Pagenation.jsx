import { useEffect, useState } from "react";
import Next from "./icon/Next";
import Prev from "./icon/Prev";
import DoubleNext from "./icon/DoubleNext";
import DoublePrev from "./icon/DoublePrev";
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
  const [pageRange, setPageRange] = useState([]);
  const [curPage, setcurPage] = useState(mockPage);

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

  return (
    <div className="pagenation-content">
      {/* <a className="pagenation-icon-button">
        <DoublePrev />
      </a>
       <a className="pagenation-icon-button">
        <Prev />
      </a> */}
      {/* <ul>{}</ul> */}
      {/* <a className="pagenation-icon-button">
        <Next />
      </a> */}
      <a href="" className="pagenation-icon-button">
        <DoubleNext />
      </a>
    </div>
  );
};
export default Pagenation;

import "./css/AsideRealTimeBoard.css";
import Info from "./iconFolder/Info";
import { Link } from "react-router-dom";
import axios from "axios";
import { useState, useRef, useEffect } from "react";
const AsideRealTimeBoard = () => {
  const [currentTop10, setCurrentTop10] = useState([]);

  const AxiosGetTop10 = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/board");
      //response 데이터를 받아서 currentTop10에 넣음

      //item당 받아야할 정보
      // boardName: item.boardTitle,
      // articlePk: item.articlePk,
      // articleTitle: item.articleTitle
    } catch {
      console.error();
    }
  };

  useEffect(() => {
    AxiosGetTop10();
    // setData(prevData => [...prevData, ...newData]);
    // setHasMore(newData.length > 0);
  }, []);

  return (
    <div className="realtimeboard-wrapper">
      <div className="realtimeboard-header">
        <h4>실시간 조회수 1위 글</h4>
      </div>
      <div className="realtimeboard-content">
        <ul className="board-content-wrapper">
          {currentTop10.map((item, index) => (
            <li className="content-item">
              <div className="item-order">{index + 1}</div>
              <div className="item-value">
                <Link
                  key={idRef.current++}
                  className="Nav-board-item"
                  to={`/board/${item.boardTitle}`}
                  state={{
                    boardName: item.boardTitle,
                    articlePk: item.articlePk,
                    kind: 0,
                  }}
                >
                  <li>{item.articleTitle}</li>
                </Link>
              </div>
            </li>
          ))}
        </ul>
      </div>
      <div className="realtimeboard-bottom">
        <Info />
        <div> 30분 단위 실시간 조회수 1위</div>
      </div>
    </div>
  );
};

export default AsideRealTimeBoard;

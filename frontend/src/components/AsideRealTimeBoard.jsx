import "./css/AsideRealTimeBoard.css";
// import Info from "./icon/Info";
import {Link} from "react-router-dom"

const AsideRealTimeBoard = () => {
  return (
    <div className="realtimeboard-wrapper">
      <div className="realtimeboard-header">
        <h4>실시간 조회수 1위 글</h4>
      </div>
      <div className="realtimeboard-content">
        <ul className="board-content-wrapper">
          <li className="content-item">
            <div className="item-order">1</div>
            <div className="item-value">
              <Link to="">wow</Link>
            </div>
          </li>


        </ul>
      </div>
      <div className="realtimeboard-bottom">
        {/* <Info /> */}
        <div> 30분 단위 실시간 조회수 1위</div>
      </div>
    </div>
  );
};

export default AsideRealTimeBoard;

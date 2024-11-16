import "./css/AsideRealTimeBoard.css";
// import Info from "./icon/Info";

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
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">2</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">3</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">4</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">5</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">6</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">7</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">8</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">9</div>
            <div className="item-value">
              <a href="">wow</a>
            </div>
          </li>
          <li className="content-item">
            <div className="item-order">10</div>
            <div className="item-value">
              <a href="">woasdfsdfsssdfsddfsgsdfgsdgsw</a>
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

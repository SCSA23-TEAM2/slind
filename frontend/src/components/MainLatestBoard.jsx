import "./css/MainLatestBoard.css";

import {Link} from "react-router-dom"
// import New from "./icon/New";
// import Like from "./icon/Like";
// import DisLike from "./icon/DisLike";
// import Comment from "./icon/Comment";
// import View from "./icon/View";
const MainLatestBoard = () => {
  const Mock = {
    articlePk: 1,
    boardPk: 1,
    boardName: "런닝 크루",
    title: "글 제목",
    viewCount: 100,
    like: 1000,
    dislike: 10000,
    commentCount: 10,
  };
  return (
    <div className="mainLatestBoard-wrapper">
      <div className="board-header">
        <div className="board-name">
          <div className="boardIcon">
            {/* <New /> */}
          </div>
          <h3>최신글</h3>
        </div>
      </div>
      <div className="board-item-wrapper">
        <ul>
          <li>
            <div className="board-item-content">
              <div className="item-board-name">
                <Link to={`/board/${Mock.boardName}`}>{Mock.boardName}</Link>
              </div>
              <div className="item-title">
                <Link to={`/board/${Mock.boardName}/Post/${Mock.title}`}>{Mock.title}</Link>
              </div>
              <div className="item-imoji-wrapper">
                <div className="item-imoji-content">
                  {/* <View /> */}
                  <div className="item-imoji-count">{Mock.viewCount}</div>
                </div>
                <div className="item-imoji-content">
                  {/* <Like /> */}
                  <div className="item-imoji-count">{Mock.like}</div>
                </div>
                <div className="item-imoji-content">
                  {/* <DisLike /> */}
                  <div className="item-imoji-count">{Mock.dislike}</div>
                </div>
                <div className="item-imoji-content">
                  {/* <Comment /> */}
                  <div className="item-imoji-count">{Mock.commentCount}</div>
                </div>
              </div>
            </div>
          </li>
          
        </ul>
      </div>
    </div>
  );
};

export default MainLatestBoard;

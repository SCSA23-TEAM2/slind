import "./css/GeneralBoard.css";

import { useState } from "react";
import BoardIcon from "./icon/BoardIcon";
import Like from "./icon/Like";
import DisLike from "./icon/DisLike";
import Comment from "./icon/Comment";
import View from "./icon/View";
import Pagenation from "./Pagenation";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const GeneralBoard = () => {
  const options = { 최신순: 0, 조회순: 1, 좋아요순: 2 };
  const [curOption, setCurOption] = useState("정렬기준");
  const Mock = {
    articlePk: 1,
    boardPk: 1,
    boardName: "런닝크루",
    title: "글 제목",
    viewCount: 100,
    like: 1000,
    dislike: 10000,
    commentCount: 10,
  };
  return (
    <div className="generalBoard-background">
      <div className="generalBoard-wrapper">
        <div className="board-header">
          <div className="board-name">
            <div className="boardIcon">
              <BoardIcon prop={40} />
            </div>
            <h1>{Mock.boardName}</h1>
          </div>

          <div className="boardButton">
            {/* 회원이 아니면 안보일 것 */}
            <div className="sort">
              <div className="sort-option">
                <Dropdown
                  options={Object.keys(options)}
                  onChange={(e) => {
                    console.log(options[e.value]);
                    setCurOption(e.value);
                  }}
                  value={curOption}
                  placeholder="정렬 기준"
                />
              </div>
              <div className="sort-button">
                <button>정렬</button>
              </div>
            </div>
            <button>소송하기</button>
            <button>글쓰기</button>
          </div>
        </div>
        <div className="board-item-wrapper">
          <ul>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like size={20} />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{10000}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{1}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{1}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{Mock.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div className="board-item-content">
                <div className="item-board-name">{Mock.boardName}</div>
                <div className="item-title">
                  <a href="">{Mock.title}</a>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    <View />
                    <div className="item-imoji-count">{Mock.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Like />
                    <div className="item-imoji-count">{Mock.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    <DisLike />
                    <div className="item-imoji-count">{1}</div>
                  </div>
                  <div className="item-imoji-content">
                    <Comment />
                    <div className="item-imoji-count">{Mock.commentCount}</div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div className="board-bottom-wrapper">
          <div className="board-pagenation">
            <Pagenation />
          </div>
        </div>
      </div>
    </div>
  );
};
// import { BiDislike } from "react-icons/bi";
// const Icon = () => {
//   return <BiDislike size="80" color="#f6f6ff" />;
// };
// export default Icon;
export default GeneralBoard;

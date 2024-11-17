import "./css/GeneralBoard.css";

import { useState,useEffect,useRef } from "react";
import { Link,useLocation } from "react-router-dom";
import axios from "axios";
// import BoardIcon from "./icon/BoardIcon";
// import Like from "./icon/Like";
// import DisLike from "./icon/DisLike";
// import Comment from "./icon/Comment";
// import View from "./icon/View";
import Pagenation from "./Pagenation";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const GeneralBoard = () => {
  const WhichBoard = useLocation();
  const infoBoard = WhichBoard.state;
  // console.log(infoBoard)
  const options = { 최신순: 0, 조회순: 1, 좋아요순: 2 };
  const [curOption, setCurOption] = useState("최신순");
  const idRef = useRef(0);
  const [BoardPosts, setLBoardPosts] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const AxiosGetapiArticleBoardPkSortPage = async (address) => {
    // console.log("여기다")
    // 요청보낼때 일반게시판 (kind=0) 은 infoBoard.pk 이용, 인민재판소는 (kind=1)은 필요없음
    try {
        const response = await axios.get(address);
        setLBoardPosts(response.data.list);
        console.log(response.data.list)
        setIsLoaded(true);
    } catch {
      console.error();
    }    
  }
  useEffect(() => {
    console.log(infoBoard.kind);
    if (infoBoard.kind) {
      AxiosGetapiArticleBoardPkSortPage("http://localhost:3000/apiJudgementSortPage");
    } else {
      AxiosGetapiArticleBoardPkSortPage("http://localhost:3000/apiArticleBoardPkSortPage");
    }
    
        // setData(prevData => [...prevData, ...newData]);
        // setHasMore(newData.length > 0);
  },[]);




  console.log(BoardPosts)
  return (
    <div className="generalBoard-background">
      <div className="generalBoard-wrapper">
        <div className="board-header">
          <div className="board-name">
            <div className="boardIcon">
              {/* <BoardIcon prop={40} /> */}
            </div>
            <h1>{infoBoard.kind == 1 ? "인민 재판소" : infoBoard.boardName}</h1>
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
                  // placeholder="정렬 기준"
                />
              </div>
              <div className="sort-button">
                <button>정렬</button>
              </div>
            </div>
            {infoBoard.kind == 0 &&<>
            <button>소송하기</button>
            <button>글쓰기</button></>}
          </div>
        </div>
        <div className="board-item-wrapper">
          <ul>
          {BoardPosts.map((item) => {
                return (
                  <li key={idRef.current++}>
              <div className="board-item-content">
                <div className="item-board-name">{item.boardName}</div>
                <div className="item-title">
                  <Link to={`/board/${item.boardName}/Post/${item.title}`}>{item.title}</Link>
                </div>
                <div className="item-imoji-wrapper">
                  <div className="item-imoji-content">
                    {/* <View /> */}
                    <div className="item-imoji-count">{item.viewCount}</div>
                  </div>
                  <div className="item-imoji-content">
                    {/* <Like size={20} /> */}
                    <div className="item-imoji-count">{item.like}</div>
                  </div>
                  <div className="item-imoji-content">
                    {/* <DisLike /> */}
                    <div className="item-imoji-count">{item.dislike}</div>
                  </div>
                  <div className="item-imoji-content">
                    {/* <Comment /> */}
                    {infoBoard.kind == 0 &&
                    <div className="item-imoji-count">{item.commentCount}</div>}
                  </div>
                </div>
              </div>
            </li>
                );
          })}
            
            
            
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

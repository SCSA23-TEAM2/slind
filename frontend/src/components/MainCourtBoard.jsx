import "./css/MainCourtBoard.css";
import { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import useAxios from "../useAxios";
import Court from "./iconFolder/Court";

import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
// import Comment from "./iconFolder/Comment";
import View from "./iconFolder/View";
const MainCourtBoard = () => {
  const axios = useAxios();
  const idRef = useRef(0);
  const [courtPosts, setCourtPosts] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const AxiosGetCourtPosts = async () => {
    // console.log("여기다")
    try {
      const response = await axios.get(
        "http://localhost:8080/api/judgement/0/1"
      );
      setCourtPosts(response.data.list);
      console.log(response.data);
      setIsLoaded(true);
    } catch {
      console.error();
    }
  };
  useEffect(() => {
    // console.log("wow");
    AxiosGetCourtPosts();
    // setData(prevData => [...prevData, ...newData]);
    // setHasMore(newData.length > 0);
  }, []);

  return (
    <div className="mainCourtBoard-wrapper">
      <div className="board-header">
        <div className="board-name">
          <div className="boardIcon">
            <Court />
          </div>
          <h3>인민 재판소</h3>
        </div>
        <div className="board-detail">
          <div>
            <Link
              to="/board/PeopleCourt"
              state={{
                kind: 1,
              }}
            >
              더보기
            </Link>
          </div>
        </div>
      </div>
      <div className="board-item-wrapper">
        <ul>
          {courtPosts.map((item) => {
            return (
              <li key={idRef.current++}>
                <div className="board-item-content">
                  <div className="item-board-name">
                    <div>{item.boardName ? "게시판" : "게시글"}</div>
                  </div>
                  <div className="item-title">
                    <Link
                      to={`/board/${item.boardName}/Post/${item.title}`}
                      state={{
                        judgementPk: item.judgementPk,
                        kind: 1,
                      }}
                    >
                      {item.title}
                    </Link>
                  </div>
                  <div className="item-imoji-wrapper">
                    <div className="item-imoji-content">
                      <View />
                      <div className="item-imoji-count">{item.viewCount}</div>
                    </div>
                    <div className="item-imoji-content">
                      <Like />
                      <div className="item-imoji-count">{item.like}</div>
                    </div>
                    <div className="item-imoji-content">
                      <DisLike />
                      <div className="item-imoji-count">{item.dislike}</div>
                    </div>
                    {/* <div className="item-imoji-content"> */}
                    {/* <Comment /> */}
                    {/* <div className="item-imoji-count">{Mock.commentCount}</div> */}
                    {/* </div> */}
                  </div>
                </div>
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
};

export default MainCourtBoard;

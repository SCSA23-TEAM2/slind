import "./css/GeneralBoard.css";

import { useState, useEffect, useRef } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import httpAxios from "../api/httpAxios";
;
import BoardIcon from "./iconFolder/BoardIcon";
import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
import Comment from "./iconFolder/Comment";
import View from "./iconFolder/View";
import Pagenation from "./Pagenation";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";
import { useAuth } from "../AuthContext"; // AuthContext 추가


const GeneralBoard = () => {
  const axios = httpAxios;
  const { accessToken } = useAuth(); // JWT 토큰 가져오기

  const navigate = useNavigate();
  const WhichBoard = useLocation();
  const infoBoard = WhichBoard.state;
  console.log(infoBoard);
  const options = { 최신순: 0, 조회순: 1, 좋아요순: 2 };
  const [curOption, setCurOption] = useState("최신순");
  const idRef = useRef(0);
  const [BoardPosts, setLBoardPosts] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [pageData, setPageData] = useState({});
  const AxiosGetapiArticleBoardPkSortPage = async (address) => {
    // console.log("여기다")
    // 요청보낼때 일반게시판 (kind=0) 은 infoBoard.pk 이용, 인민재판소는 (kind=1)은 필요없음
    try {
      const response = await httpAxios.get(address);
      const { list, ...paginationData } = response.data;
      setLBoardPosts(list);
      setPageData(paginationData);
      setIsLoaded(true);
      console.log(list);
    } catch {
      console.error();
    }
  };
  const CallAxios = (pagenum) => {
    // console.log(infoBoard.kind);
    // console.log(pagenum);
    // console.log(curOption);
    if (infoBoard.kind) {
      AxiosGetapiArticleBoardPkSortPage(
        `/api/judgement/${options[curOption]}/${pagenum}`
      );
    } else {
      console.log("wowow");
      AxiosGetapiArticleBoardPkSortPage(
        `/api/article/${infoBoard.boardPk}/${options[curOption]}/${pagenum}`
      );
    }
  };
  useEffect(() => {
    CallAxios(1);

    // setData(prevData => [...prevData, ...newData]);
    // setHasMore(newData.length > 0);
  }, [axios, WhichBoard]);

  const SortPage = () => {
    CallAxios(1);
  };
  // console.log(BoardPosts)
  // console.log(pageData)

  // console.log(BoardPosts)
  const gotoPostForm = () => {
    if (!accessToken) {
      // JWT 토큰이 없으면 로그인 페이지로 이동
      alert("로그인이 필요합니다.");
      navigate("/Login");
      return;
    }
    navigate(`/board/${infoBoard.boardName}/write`, {
      state: {
        pk: infoBoard.boardPk,
        Name: infoBoard.boardName,
        kind: 0,
        title: "",
        articleContent: "",
      },
    });
  };

  const gotoSuitForm = () => {
    if (!accessToken) {
      // JWT 토큰이 없으면 로그인 페이지로 이동
      alert("로그인이 필요합니다.");
      navigate("/Login");
      return;
    }
    navigate(`/board/PeopleCourt/write`, {
      state: {
        pk: infoBoard.boardPk,
        Name: infoBoard.boardName,
        kind: 3,
        title: "",
        articleContent: "",
      },
    });
  };

  return (
    <div className="generalBoard-background">
      <div className="generalBoard-wrapper">
        <div className="board-header">
          <div className="board-name">
            <div className="boardIcon">
              <BoardIcon prop={40} />
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
                <button onClick={SortPage}>정렬</button>
              </div>
            </div>
            {infoBoard.kind == 0 && (
              <>
                <button onClick={gotoSuitForm}>소송하기</button>
                <button onClick={gotoPostForm}>글쓰기</button>
              </>
            )}
          </div>
        </div>
        <div className="board-item-wrapper">
          <ul>
            {BoardPosts.map((item) => {
              return (
                <li key={idRef.current++}>
                  <div className="board-item-content">
                    <div className="item-board-name">
                      {infoBoard.kind == 0
                        ? item.boardTitle
                        : item.boardName
                        ? "게시판"
                        : "게시글"}
                    </div>
                    <div className="item-title">
                      <Link
                        to={`/board/${item.boardName}/Post/${item.title}`}
                        state={
                          infoBoard.kind == 0
                            ? {
                                boardName: item.boardTitle,
                                articlePk: item.articlePk,

                                kind: 0, //kind: 0 -> 일반 게시판, kind: 1 -> 재판게시판
                              }
                            : {
                                judgementPk: item.judgementPk,
                                kind: 1,
                              }
                        }
                      >
                        {item.title ? item.title : item.articleTitle}
                      </Link>
                    </div>
                    <div className="item-imoji-wrapper">
                      <div className="item-imoji-content">
                        <View />
                        <div className="item-imoji-count">{item.viewCount}</div>
                      </div>
                      <div className="item-imoji-content">
                        <Like size={20} />
                        <div className="item-imoji-count">{item.likeCount}</div>
                      </div>
                      <div className="item-imoji-content">
                        <DisLike />
                        <div className="item-imoji-count">
                          {item.dislikeCount}
                        </div>
                      </div>
                      {infoBoard.kind == 0 && (
                        <div className="item-imoji-content">
                          <Comment />
                          <div className="item-imoji-count">
                            {item.commentCount}
                          </div>
                        </div>
                      )}
                    </div>
                  </div>
                </li>
              );
            })}
          </ul>
        </div>
        <div className="board-bottom-wrapper">
          <div className="board-pagenation">
            <Pagenation {...pageData} CallAxios={CallAxios} />
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

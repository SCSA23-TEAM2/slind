import "./css/MyContentItem.css";
import { Link } from "react-router-dom";
import axios from "axios";
import { useState } from "react";

const MyContentItem = ({ pageNum, index, item, onDelete }) => {
  // 날짜 포맷 함수
  const formatDate = (dateString) => {
    if (!dateString) return "Invalid Date";

    const date = new Date(dateString);

    // 년, 월, 일, 시간, 분 추출
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 +1 필요
    const day = String(date.getDate()).padStart(2, "0");
    const hours = String(date.getHours()).padStart(2, "0");
    const minutes = String(date.getMinutes()).padStart(2, "0");

    return `${year}. ${month}. ${day}. ${hours}:${minutes}`;
  };

  // 삭제 버튼 클릭 핸들러
  const handleDelete = async () => {
    if (!window.confirm("삭제하시겠습니까?")) {
      return; // 사용자가 취소를 선택하면 중단
    }

    let url = "";

    // URL 설정
    switch (pageNum) {
      case 3:
        url = `http://localhost:8080/api/board/auth/${item.boardPk}`;
        break;
      case 4:
        url = `http://localhost:8080/api/article/auth/${item.articlePk}`;
        break;
      case 5:
        url = `http://localhost:8080/api/comment/auth/${item.commentPk}`;
        break;
      default:
        console.error("Invalid pageNum");
        return;
    }

    try {
      // 삭제 요청
      await axios.delete(url);
      alert("삭제가 완료되었습니다.");
      onDelete(item); // 삭제 성공 후 부모 컴포넌트에 삭제된 항목 전달
    } catch (error) {
      // 에러 처리
      if (error.response) {
        switch (error.response.status) {
          case 400:
            alert(pageNum === 4 ? "존재하지 않는 게시글입니다." : "잘못된 요청입니다.");
            break;
          case 401:
            alert(
              pageNum === 3
                ? "내가 만든 게시판이 아닙니다."
                : pageNum === 4
                ? "내가 작성한 게시글이 아닙니다."
                : "내가 작성한 댓글이 아닙니다."
            );
            break;
          case 404:
            alert(
              pageNum === 3
                ? "존재하지 않는 게시판입니다."
                : pageNum === 5
                ? "존재하지 않는 댓글입니다."
                : "존재하지 않는 리소스입니다."
            );
            break;
          default:
            alert("오류가 발생했습니다. 다시 시도해주세요.");
        }
      } else {
        console.error("Error: ", error);
        alert("네트워크 오류가 발생했습니다.");
      }
    }
  };

  // 렌더링
  const { boardPk, createdDttm } = item;

  if (pageNum === 3) {
    const { boardTitle } = item;
    return (
      <li>
        <div className="MyContentList-content">
          <div className="MyContentList-content-boardName">
            <Link to={`/board/${boardTitle}`}  state={{boardPk: boardPk, 
                          boardName: boardTitle,
                          kind: 0}}>{boardTitle || "NO title"}</Link>
          </div>

          <div className="MyContentList-content-date">{formatDate(createdDttm)}</div>
          <div className="MyContentList-content-delete">
            <button onClick={handleDelete}>삭제</button>
          </div>
        </div>
      </li>
    );
  }

  if (pageNum === 4) {
    const { articlePk, articleTitle, boardTitle } = item;
    return (
      <li>
        <div className="MyContentList-content">
          <div className="MyContentList-content-boardName">
            <Link to={`/board/${boardTitle}`}  state={{boardPk: boardPk, 
                          boardName: boardTitle,
                          kind: 0}}>{boardTitle}</Link>
          </div>
          <div className="MyContentList-content-title">
            <Link to={`/board/${boardTitle}/Post/${articleTitle}`} 
            state={{articlePk:articlePk, kind:0}}>{articleTitle}</Link>
          </div>
          <div className="MyContentList-content-date">{formatDate(createdDttm)}</div>
          <div className="MyContentList-content-delete">
            <button onClick={handleDelete}>삭제</button>
          </div>
        </div>
      </li>
    );
  }

  if (pageNum === 5) {
    const { boardTitle, articlePk, articleTitle, commentPk, commentContent } = item;
    return (
      <li>
        <div className="MyContentList-content">
          <div className="MyContentList-content-boardName">
            <Link to={`/board/${boardTitle}/Post/${articleTitle}`}
                  state={{articlePk: articlePk, 
                          kind: 0}}>{articleTitle}</Link>
          </div>
          <div className="MyContentList-content-title">
            <Link to={`/board/${boardTitle}/Post/${articleTitle}`}
            state={{articlePk:articlePk, kind:0}}>{commentContent}</Link>
          </div>
          <div className="MyContentList-content-date">{formatDate(createdDttm)}</div>
          <div className="MyContentList-content-delete">
            <button onClick={handleDelete}>삭제</button>
          </div>
        </div>
      </li>
    );
  }
  if (pageNum === 6) {
    const { boardTitle, articlePk, articleTitle, judgementPk, title } = item;
  
    // article일 때
    if (articlePk) {
      return (
        <li>
          <div className="MyContentList-content">
            <div className="MyContentList-content-boardName">
              <Link to={`/board/${boardTitle}/Post/${articleTitle}` }
              >
                {articleTitle || "Untitled Article"}
              </Link>
            </div>
            <div className="MyContentList-content-title">
              <Link to={`/board/PeopleCourt/Post/${title}`} state={{judgementPk:judgementPk, kind:1}}>
                {title || "Untitled Judgement"}
              </Link>
            </div>
            <div className="MyContentList-content-date">
              {formatDate(createdDttm || "Invalid Date")}
            </div>
          </div>
        </li>
      );
    }
  
    // 내가 소송한 재판 board일 때
    return (
      <li>
        <div className="MyContentList-content">
          <div className="MyContentList-content-boardName">
            <Link to={`/board/${boardTitle}`} state={{boardPk: boardPk, 
                          boardName: boardTitle,
                          kind: 0}}>
              {boardTitle || "Untitled Board"}
            </Link>
          </div>
          <div className="MyContentList-content-title">
            {boardPk ? (
              <Link to={`/board/PeopleCourt/Post/${title}`}
              state={{judgementPk: judgementPk, kind:1}}>
                {boardTitle || "Untitled Article"}
              </Link>
            ) : (
              "No Related Article"
            )}
          </div>
          <div className="MyContentList-content-date">
            {formatDate(createdDttm || "Invalid Date")}
          </div>
        </div>
      </li>
    );
  }
  

  
};

export default MyContentItem;

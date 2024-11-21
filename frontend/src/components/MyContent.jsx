import "./css/MyContent.css";
import MyContentItem from "./MyContentItem";
// import axios from "axios";
import { useState, useEffect } from "react";
import { useInView } from "react-intersection-observer";
import useAxios from "../api/useAxios";

const MyContent = ({ pageNum }) => {
  const axios = useAxios();
  // const [page, setPage] = useState(1); // 페이지 번호
  const [data, setData] = useState([]); // 데이터 리스트
  const [hasNext, setHasNext] = useState(true);
  const [lastId, setLastId] = useState(null);
  const [isLoading, setIsLoading] = useState(false); // 로딩 상태 관리

  // const refObj = useRef(0);

  // 옵저버 옵션 설정
  const { ref, inView } = useInView({
    threshold: 0.5, // 요소가 10% 보일 때 트리거
  });

  const fetchUrlByPageNum = () => {
    switch (pageNum) {
      case 3: // 내가 만든 게시판
        return lastId
          ? `/api/member/auth/board/${lastId}`
          : `/api/member/auth/board`;
      case 4: // 내가 쓴 글
        return lastId
          ? `/api/member/auth/article/${lastId}`
          : `/api/member/auth/article`;
      case 5: // 내가 쓴 댓글
        return lastId
          ? `/api/member/auth/comment/${lastId}`
          : `/api/member/auth/comment`;
      case 6: // 내가 쓴 댓글
        return lastId
          ? `/api/member/auth/judgement/${lastId}`
          : `/api/member/auth/judgement`;
      default:
        return null;
    }
  };

  // API를 호출하여 데이터를 가져오는 함수
  const fetchData = async () => {
    if (!hasNext) return;
    const url = fetchUrlByPageNum();
    if (!url) return;
    // const response = await fetch(`/api/data?page=${page}`);
    // const newData = await response.json();
    try {
      const response = await axios.get(url);
      const { list, hasNext } = response.data;
      setData((prevData) => [...prevData, ...list]);
      setHasNext(hasNext); //데이터 더 존재하는지 여부
      if (hasNext) {
        const lastItem = list[list.length - 1];
        // pageNum에 따라 적절한 PK 설정
        let newLastId = null;
        switch (pageNum) {
          case 3:
            newLastId = lastItem.boardPk;
            break;
          case 4:
            newLastId = lastItem.articlePk;
            break;
          case 5:
            newLastId = lastItem.commentPk;
            break;
          case 6:
            newLastId = lastItem.judgementPk;
            break;
          default:
            newLastId = null;
        }
        setLastId(newLastId);
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setIsLoading(false);
    }
  };

  // pageNum이 변경될 때 데이터를 초기화하고 다시 가져오기
  useEffect(() => {
    setData([]); // 데이터 초기화
    setHasNext(true); // hasNext 초기화
    setLastId(null); // lastId 초기화
    fetchData(); // 데이터 가져오기
  }, [pageNum]); // pageNum이 변경될 때 실행

  // inView와 hasMore 조건을 만족하면 데이터를 추가로 가져옴
  useEffect(() => {
    if (inView && hasNext) {
      fetchData();
    }
  }, [inView, hasNext]); // inView와 hasMore가 변경될 때 실행
  // 페이지 번호에 따른 메시지
  const getEmptyMessage = () => {
    switch (pageNum) {
      case 3:
        return "내가 만든 게시판이 없습니다.";
      case 4:
        return "내가 쓴 글이 없습니다.";
      case 5:
        return "내가 쓴 댓글이 없습니다.";
      case 6:
        return "내가 소송한 재판이 없습니다.";
      default:
        return "데이터가 없습니다.";
    }
  };

  return (
    <div className="MyContent-wrapper">
      <div className="MyContent-header">
        <h1>
          {pageNum === 3
            ? "내가 만든 게시판"
            : pageNum === 4
            ? "내가 쓴 글"
            : pageNum === 5
            ? "내가 쓴 댓글"
            : pageNum == 6
            ? "내가 소송한 재판"
            : ""}
        </h1>
      </div>
      <div className="MyContent-content">
        {data.length === 0 && !isLoading ? ( // 데이터가 없고 로딩 중이 아닐 때 메시지 표시
          <div className="empty-message">{getEmptyMessage()}</div>
        ) : (
          <ul className="content-list">
            {data.map((item, index) => (
              <MyContentItem
                key={index}
                item={item}
                pageNum={pageNum}
                onDelete={(deletedItem) => {
                  setData((prevData) =>
                    prevData.filter((d) => d !== deletedItem)
                  );
                }}
              />
            ))}
            <div ref={ref} style={{ height: "1px" }}></div>
          </ul>
        )}
      </div>
    </div>
  );
};

export default MyContent;

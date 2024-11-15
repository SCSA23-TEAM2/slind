import "./css/MyContent.css";
import MyContentItem from "./MyContentItem";
import { useState, useEffect, useRef } from "react";
import { useInView } from "react-intersection-observer";

const MyContent = () => {
  // const [page, setPage] = useState(1); // 페이지 번호
  const [data, setData] = useState([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]); // 데이터 리스트
  const [hasMore, setHasMore] = useState(true);
  // const refObj = useRef(0);

  // 옵저버 옵션 설정
  const { ref, inView } = useInView({
    threshold: 0.5, // 요소가 10% 보일 때 트리거
  });

  // API를 호출하여 데이터를 가져오는 함수
  const fetchData = () => {
    if (!hasMore) return;

    // const response = await fetch(`/api/data?page=${page}`);
    // const newData = await response.json();
    const newData = [1, 2, 3, 4];
    setData((prevData) => [...prevData, ...newData]);
    setHasMore(true); // 더 이상 데이터가 없으면 hasMore를 false로 설정
  };

  // 페이지나 inView가 변경될 때마다 데이터 가져오기
  useEffect(() => {
    if (inView && hasMore) {
      fetchData();

      // setPage((prevPage) => prevPage + 1); // 페이지 번호 증가
    }
  }, [inView]);

  return (
    <div className="MyContent-wrapper">
      <div className="MyContent-header">
        <h1>(콘텐츠이름)</h1>
      </div>
      <div className="MyContent-content">
        <ul className="content-list">
          {data.map((item) => {
            return <MyContentItem />;
          })}
          <div ref={ref} style={{ height: "1px" }}></div>
        </ul>
      </div>
    </div>
  );
};

export default MyContent;

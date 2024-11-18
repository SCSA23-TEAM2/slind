import { useInView } from "react-intersection-observer";
import { useEffect } from "react";
import "./css/InfiniteScrollTest.css";
const InfiniteScrollTest = () => {
  const { ref, inView } = useInView({
    threshold: 0.5, // 화면의 50%가 보일 때 감지
  });

  useEffect(() => {
    if (inView) {
      console.log("안녕하세요, 문어박사입니다.");
    }
  }, [inView]);

  return (
    <div>
      <div className="WOW"></div>
      <div ref={ref}>감시할 요소</div>
    </div>
  );
};
export default InfiniteScrollTest;

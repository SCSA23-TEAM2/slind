import "./css/PostDetail.css";

import PostHeaderMain from "./PostHeaderMain";
import PostCountsInfo from "./PostCountsInfo";
const PostDetail = () => {
  return (
    <div className="PostDetail-main-wrapper">
      {/* 게시글 헤더와 본문 컴포넌트화 */}
      <PostHeaderMain />
      {/* 하단, 조회수, 좋아요수,싫어요수,댓글수는 컴포넌트화 */}
      <PostCountsInfo />

      {/* 게시글 하단에 댓글쓰기,댓글 들 컴포넌트화 */}
    </div>
  );
};

export default PostDetail;

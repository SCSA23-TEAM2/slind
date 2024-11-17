import "./css/PostDetail.css";

import PostHeaderMain from "./PostHeaderMain";
import PostCountsInfo from "./PostCountsInfo";
import { useLocation } from "react-router-dom";
import { useState, useEffect} from "react";
import axios from "axios"
const PostDetail = () => {
  const PostLocation = useLocation();
  const PostInfo = PostLocation.state

  const [postInfo, setPostInfo] = useState({});
  const [isLoaded, setIsLoaded] = useState(false);
  console.log(PostInfo)
  const AxiosGetapiApiArticleDetailArticlePk = async (address) => {
    // console.log("여기다")
    // 요청보낼때 일반게시판 (kind=0) 은 infoBoard.pk 이용, 인민재판소는 (kind=1)은 필요없음
    try {
        const response = await axios.get(address);
        const { ...postDetailData } = response.data;
        setPostInfo(postDetailData);
        setIsLoaded(true);
    } catch {
      console.error();
    }    
  }
  const CallAxios = () => {
    if (PostInfo.kind) {
      AxiosGetapiApiArticleDetailArticlePk("http://localhost:3000/apiJudgementJudgementPk");
    } else {
      AxiosGetapiApiArticleDetailArticlePk("http://localhost:3000/apiArticleDetailArticlePk");
    }
    
    
  }
  useEffect(() => {
    CallAxios();
    
        // setData(prevData => [...prevData, ...newData]);
        // setHasMore(newData.length > 0);
  },[])

  return (
    <div className="PostDetail-main-wrapper">
      {/* 게시글 헤더와 본문 컴포넌트화 */}
      <PostHeaderMain {...postInfo} pi={PostInfo} />
      {/* 하단, 조회수, 좋아요수,싫어요수,댓글수는 컴포넌트화 */}
      <PostCountsInfo {...postInfo}/>

      {/* 게시글 하단에 댓글쓰기,댓글 들 컴포넌트화 */}
    </div>
  );
};

export default PostDetail;

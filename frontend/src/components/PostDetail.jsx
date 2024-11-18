import "./css/PostDetail.css";

import PostHeaderMain from "./PostHeaderMain";
import PostCountsInfo from "./PostCountsInfo";
import { useLocation, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import useAxios from "../useAxios";
const PostDetail = () => {
  const navigate = useNavigate();
  const axios = useAxios();
  const PostLocation = useLocation();
  const PostInfo = PostLocation.state;
  console.log(PostInfo);
  const [PI, setPI] = useState(PostInfo);
  const [postInfo, setPostInfo] = useState({});
  const [isLoaded, setIsLoaded] = useState(false);
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
  };
  const CallAxios = () => {
    if (PI.kind) {
      AxiosGetapiApiArticleDetailArticlePk(
        `http://localhost:8080/api/judgement/${PI.judgementPk}`
      );
    } else {
      AxiosGetapiApiArticleDetailArticlePk(
        `http://localhost:8080/api/article/detail/${PI.articlePk}`
      );
    }
  };
  const handleLinkClick = (state) => {
    setPI(state);
  };
  const gotoPostModifyForm = () => {
    navigate(`/board/${PI.boardName}/write`, {
      state: {
        pk: postInfo.articlePk,
        Name: PI.boardName,
        kind: 2,
        title: postInfo.title,
        articleContent: postInfo.articleContent,
      },
    });
  };
  const gotoSuitForm = () => {
    console.log(postInfo);
    navigate(`/board/PeopleCourt/write`, {
      state: {
        pk: postInfo.articlePk,
        Name: postInfo.title,
        kind: 1,
        title: "",
        articleContent: "",
      },
    });
  };

  useEffect(() => {
    CallAxios();
    console.log(PI);
    // setData(prevData => [...prevData, ...newData]);
    // setHasMore(newData.length > 0);
  }, [PI]);
  console.log(postInfo);

  const ILike = async () => {
    console.log({
      articlePk: postInfo.articlePk,
      isLike: true,
      isUp: true,
    });
    const response = await axios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: true,
        isUp: true,
      }
    );
    CallAxios();
  };
  const IDisike = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: false,
        isUp: true,
      }
    );
    CallAxios();
  };
  const CancelLike = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: true,
        isUp: false,
      }
    );
    CallAxios();
  };
  const CancelDisLike = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: false,
        isUp: false,
      }
    );
    CallAxios();
  };
  const Agree = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/judgement/auth/reaction",
      {
        judgementPk: postInfo.judgementPk,
        isLike: true,
      }
    );
    CallAxios();
  };

  const Oppose = async () => {
    const response = await axios.post(
      "http://localhost:8080/api/judgement/auth/reaction",
      {
        judgementPk: postInfo.judgementPk,
        isLike: false,
      }
    );
    CallAxios();
  };

  return (
    <div className="PostDetail-main-wrapper">
      {/* 게시글 헤더와 본문 컴포넌트화 */}
      <PostHeaderMain
        {...postInfo}
        pi={PI}
        handleLinkClick={handleLinkClick}
        gotoPostModifyForm={gotoPostModifyForm}
        gotoSuitForm={gotoSuitForm}
      />
      {/* 하단, 조회수, 좋아요수,싫어요수,댓글수는 컴포넌트화 */}
      <PostCountsInfo
        {...postInfo}
        pi={PI}
        ILike={ILike}
        IDisLike={IDisike}
        CancelLike={CancelLike}
        CancelDisLike={CancelDisLike}
        Agree={Agree}
        Oppose={Oppose}
      />

      {/* 게시글 하단에 댓글쓰기,댓글 들 컴포넌트화 */}
    </div>
  );
};

export default PostDetail;

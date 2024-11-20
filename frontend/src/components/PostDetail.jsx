import "./css/PostDetail.css";

import PostHeaderMain from "./PostHeaderMain";
import PostCountsInfo from "./PostCountsInfo";
import InputComment from "./InputComment";
import BestComments from "./BestComments";
import NormalComments from "./NormalComments";
import Images from "./Images";

import { useLocation, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

import { useInView } from "react-intersection-observer";
import useAxios from "../useAxios";
import axios from "axios";
const PostDetail = () => {
  const navigate = useNavigate();
  const customAxios = useAxios();
  const PostLocation = useLocation();
  const PostInfo = PostLocation.state;
  console.log(PostInfo);
  const [PI, setPI] = useState(PostInfo);
  const [postInfo, setPostInfo] = useState({});
  const [isLoaded, setIsLoaded] = useState(false);
  const [stateBestComments, setStateBestComments] = useState([]);
  const [stateNormalComments, setStateNormalComments] = useState([]);
  const [hasMore, setHasMore] = useState(true);

  const { ref, inView } = useInView({
    threshold: 0.5, // 요소가 10% 보일 때 트리거
  });

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

  const AxiosGetapiApiCommentArticlePkBest = async () => {
    // console.log("여기다")
    // 요청보낼때 일반게시판 (kind=0) 은 infoBoard.pk 이용, 인민재판소는 (kind=1)은 필요없음
    try {
      const response = await axios.get(
        `http://localhost:8080/api/comment/${PI.articlePk}/best`
      );
      const BestCommentsList = response.data;
      setStateBestComments(BestCommentsList);
      setIsLoaded(true);
    } catch {
      console.error();
    }
  };

  const AxiosGetapiApiCommentArticlePklastCommentPk = async (lastnum) => {
    // console.log("여기다")
    // 요청보낼때 일반게시판 (kind=0) 은 infoBoard.pk 이용, 인민재판소는 (kind=1)은 필요없음
    try {
      const response = await axios.get(
        `http://localhost:8080/api/comment/${PI.articlePk}?lastCommentPk=${lastnum}`
      );
      const NomalCommentsList = response.data;
      setStateNormalComments((prevData) => [
        ...prevData,
        ...NomalCommentsList.list,
      ]);
      setHasMore(NomalCommentsList.hasNext);
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
  const CallBestCommentsAxios = () => {
    AxiosGetapiApiCommentArticlePkBest();
  };
  const CallNormalCommentsAxios = (lastnum) => {
    if (!hasMore) return;
    AxiosGetapiApiCommentArticlePklastCommentPk(lastnum);
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
    if (inView && hasMore) {
      CallNormalCommentsAxios(
        stateNormalComments.length == 0
          ? 0
          : stateNormalComments[stateNormalComments.length - 1].commentPk
      );
    }
    CallAxios();
    CallBestCommentsAxios();
    console.log(PI);
    // setData(prevData => [...prevData, ...newData]);
    // setHasMore(newData.length > 0);
  }, [PI, inView]);
  console.log(postInfo);

  const ILike = async () => {
    console.log({
      articlePk: postInfo.articlePk,
      isLike: true,
      isUp: true,
    });
    const response = await customAxios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: true,
        isUp: true,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const IDisike = async () => {
    const response = await customAxios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: false,
        isUp: true,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const CancelLike = async () => {
    const response = await customAxios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: true,
        isUp: false,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const CancelDisLike = async () => {
    const response = await customAxios.post(
      "http://localhost:8080/api/article/auth/reaction",
      {
        articlePk: postInfo.articlePk,
        isLike: false,
        isUp: false,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const Agree = async () => {
    const response = await customAxios.post(
      "http://localhost:8080/api/judgement/auth/reaction",
      {
        judgementPk: postInfo.judgementPk,
        isLike: true,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };

  const Oppose = async () => {
    const response = await customAxios.post(
      "http://localhost:8080/api/judgement/auth/reaction",
      {
        judgementPk: postInfo.judgementPk,
        isLike: false,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };

  const onSubmitComment = async (commentContent) => {
    console.log("content: ", commentContent);
    const response = await customAxios.post(
      "http://localhost:8080/api/comment/auth",
      {
        articlePk: postInfo.articlePk,
        content: commentContent,
      }
    );

    const newComment = {
      articlePk: postInfo.articlePk,
      content: commentContent,
    };
    // setStateNormalComments((prevComments) => [newComment, ...prevComments]);
    // CallAxios();
    // CallBestCommentsAxios();
  };
  const commentLike = async (CommentPk) => {
    const response = await customAxios.post(
      "http://localhost:8080/api/comment/auth/reaction",
      {
        commentPk: CommentPk,
        isLike: true,
        isUp: true,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const commentDislike = async (CommentPk) => {
    const response = await customAxios.post(
      "http://localhost:8080/api/comment/auth/reaction",
      {
        commentPk: CommentPk,
        isLike: false,
        isUp: true,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const commentCancelLike = async (CommentPk) => {
    const response = await customAxios.post(
      "http://localhost:8080/api/comment/auth/reaction",
      {
        commentPk: CommentPk,
        isLike: true,
        isUp: false,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
  };
  const commentCancelDislike = async (CommentPk) => {
    const response = await customAxios.post(
      "http://localhost:8080/api/comment/auth/reaction",
      {
        commentPk: CommentPk,
        isLike: false,
        isUp: false,
      }
    );
    CallAxios();
    CallBestCommentsAxios();
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
      {!PI.kind && (
        <>
          {/* 게시글 하단에 댓글쓰기,댓글 들 컴포넌트화 */}
          <InputComment postComment={onSubmitComment} />
          {stateBestComments.map((item) => {
            return (
              <BestComments
                item={item}
                commentLike={commentLike}
                commentDislike={commentDislike}
                commentCancelLike={commentCancelLike}
                commentCancelDislike={commentCancelDislike}
                axios={axios}
              />
            );
          })}
          <div className="comment-content">
            <ul className="comment-list">
              {stateNormalComments.map((item) => {
                return (
                  <NormalComments
                    item={item}
                    commentLike={commentLike}
                    commentDislike={commentDislike}
                    commentCancelLike={commentCancelLike}
                    commentCancelDislike={commentCancelDislike}
                    axios={axios}
                  />
                );
              })}
              <div ref={ref} style={{ height: "1px" }}></div>
            </ul>
          </div>
          {/* <BestComments /> */}
          {/* <NormalComments /> */}{" "}
        </>
      )}
    </div>
  );
};

export default PostDetail;

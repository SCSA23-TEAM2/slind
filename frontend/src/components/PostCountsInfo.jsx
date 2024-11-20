import "./css/PostCountsInfo.css";
import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
import { useState, useEffect } from "react";
const PostCountsInfo = ({
  commentCount,
  dislikeCount,
  isDislike,
  isLike,
  isMine,
  likeCount,
  viewCnt,
  pi,
  ILike,
  IDisLike,
  CancelLike,
  CancelDisLike,
  Agree,
  Oppose,
}) => {
  console.log(
    commentCount,
    dislikeCount,
    isDislike,
    isLike,
    isMine,
    likeCount,
    viewCnt
  );

  const [stateIsLike, setStateIsLike] = useState(isLike);
  const [stateIsDislike, setStateIsDislike] = useState(isDislike);
  const [stateLikeCount, setStateLikeCount] = useState(likeCount);
  const [stateDislikeCount, setStateDislikeCount] = useState(dislikeCount);
  const [stateViewCount, setStateViewCount] = useState(viewCnt);
  const [stateCommentCount, setStateCommentCount] = useState(commentCount);
  // console.log(stateLikeCount);

  useEffect(() => {
    setStateIsLike(isLike);
    setStateIsDislike(isDislike);
    setStateLikeCount(likeCount);
    setStateDislikeCount(dislikeCount);
    setStateViewCount(viewCnt);
    setStateCommentCount(commentCount);
  }, [
    commentCount,
    dislikeCount,
    isDislike,
    isLike,
    isMine,
    likeCount,
    viewCnt,
  ]);
  // console.log(isLike);
  // console.log(stateIsLike);
  return (
    <div className="PostCountsInfo-wrapper">
      <div className="PostCountsInfo-toggle-wrapper">
        <div className="PostCountsInfo-like">
          <button
            className="like-button"
            onClick={() => {
              {
                pi.kind ? Agree() : stateIsLike ? CancelLike() : ILike();
              }
            }}
          >
            <Like
              size={stateLikeCount > 100 ? 500 : stateLikeCount * 3 + 40}
              color={stateIsLike ? "red" : "#A7A9AD"}
            ></Like>
          </button>
          <div>{stateLikeCount}</div>
        </div>
        <div className="PostCountsInfo-dislike">
          <button
            className="dislike-button"
            onClick={() => {
              {
                console.log("DISLIKE");
                pi.kind
                  ? Oppose()
                  : stateIsDislike
                  ? CancelDisLike()
                  : IDisLike();
              }
            }}
          >
            <DisLike
              size={stateDislikeCount > 100 ? 500 : stateDislikeCount * 3 + 40}
              color={stateIsDislike ? "red" : "#A7A9AD"}
            ></DisLike>
          </button>
          <div>{stateDislikeCount}</div>
        </div>
      </div>
      <div className="PostCountsInfo-nontoggle-wrapper">
        <div className="PostDetail-main-content-Views"></div>
        <div className="PostDetail-main-content-Comments"></div>
      </div>
    </div>
  );
};
export default PostCountsInfo;

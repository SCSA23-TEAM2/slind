import "./css/NormalComments.css";
import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
import { useState, useEffect } from "react";
import { useInView } from "react-intersection-observer";
const NormalComments = ({
  item,
  commentLike,
  commentDislike,
  commentCancelLike,
  commentCancelDislike,
  axios,
}) => {
  const nickname = item.nickname;
  const content = item.commentContent;
  const like = item.likeCount;
  const dislike = item.dislikeCount;
  const isDeleted = item.isDeleted;
  const isMine = item.isMine;
  const isLike = item.isLike;
  const isDislike = item.isDislike;

  const [stateIsLike, setStateIsLike] = useState(isLike);
  const [stateIsDislike, setStateIsDislike] = useState(isDislike);
  const [stateLikeCount, setStateLikeCount] = useState(like);
  const [stateDislikeCount, setStateDislikeCount] = useState(dislike);
  const [stateIsDeleted, setStateIsDeleted] = useState(isDeleted);
  const [stateCommentContent, setStateCommentContent] = useState(content);
  const [toggle, setToggle] = useState(false);
  const [stateReplyList, setStateReplyList] = useState([]);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    setStateIsLike(isLike);
    setStateIsDislike(isDislike);
    setStateLikeCount(like);
    setStateDislikeCount(dislike);
    setStateIsDeleted(isDeleted);
    setStateCommentContent(content);
  }, [content, like, dislike, isDeleted, isMine, isLike, isDislike]);
  const onSwitchToggle = () => {
    setToggle(!toggle);
    if (toggle) {
      try {
      } catch (error) {}
    }
  };

  const changelike = () => {
    if (isLike) {
      commentCancelLike(item.commentPk);
    } else {
      commentLike(item.commentPk);
    }
  };
  const changedislike = () => {
    if (isDislike) {
      commentCancelDislike(item.commentPk);
    } else {
      commentDislike(item.commentPk);
    }
  };

  return (
    <div className="NormalComments-wrapper">
      {stateIsDeleted ? (
        <div>삭제된 댓글입니다.</div>
      ) : (
        <>
          <div className="NormalComments-header">
            <div className="NormalComments-header-left">
              <div className="NormalComments-author">{nickname}</div>
            </div>
          </div>

          <div className="NormalComments-content">{stateCommentContent}</div>
        </>
      )}

      <div className="NormalComments-bottom">
        <div className="NormalComments-leftbutton-wrapper">
          <div className="NormalComments-reply-button-wrapper">
            <button>답글</button>
          </div>
          {isMine && !stateIsDeleted && (
            <div className="NormalComments-Modify-button-wrapper">
              <button>수정</button>
            </div>
          )}
        </div>
        <div className="NormalComments-preference-button-wrapper">
          <div className="NormalComments-like-wrapper">
            <button className="like" onClick={changelike}>
              <Like size={30} color={stateIsLike ? "red" : "gray"} />
            </button>
            <div>{stateLikeCount}</div>
          </div>
          <div className="NormalComments-dislike-wrapper">
            <button className="dislike" onClick={changedislike}>
              <DisLike size={30} color={stateIsDislike ? "red" : "gray"} />
            </button>
            <div>{stateDislikeCount}</div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default NormalComments;

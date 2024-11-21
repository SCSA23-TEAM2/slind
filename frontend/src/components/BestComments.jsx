import "./css/BestComments.css";
import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
import { useState, useEffect } from "react";

const BestComments = ({
  item,
  commentLike,
  commentDislike,
  commentCancelLike,
  commentCancelDislike,
}) => {
  const nickname = item.nickname;
  const content = item.commentContent;
  const like = item.likeCount;
  const dislike = item.dislikeCount;
  const isDeleted = item.isDeleted;
  const isMine = item.isMine;
  const isLike = item.isLike;
  const isDislike = item.isDislike;
  console.log(
    nickname,
    content,
    like,
    dislike,
    isDeleted,
    isMine,
    isLike,
    isDislike
  );
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
  // 대댓글 보기 위함
  return (
    <div className="BestComments-wrapper">
      {stateIsDeleted ? (
        <div>삭제된 댓글입니다.</div>
      ) : (
        <>
          <div className="BestComments-header">
            <div className="BestComments-header-left">
              <div className="BestComments-author">{nickname}</div>
            </div>
            <div className="BestComments-header-right">
              <div className="Best-wrapper">BEST</div>
            </div>
          </div>

          <div className="BestComments-content">{stateCommentContent}</div>
        </>
      )}

      <div className="BestComments-bottom">
        <div className="BestComments-leftbutton-wrapper">
          <div className="reply-button-wrapper">
            {/* <button onClick={onSwitchToggle}>답글</button> */}
          </div>
          {isMine && !stateIsDeleted && (
            <div className="Modify-button-wrapper">
              {/* <button>수정</button> */}
            </div>
          )}
        </div>
        <div className="preference-button-wrapper">
          <div className="like-wrapper">
            <button className="like" onClick={changelike}>
              <Like size={30} color={stateIsLike ? "red" : "gray"} />
            </button>
            <div>{stateLikeCount}</div>
          </div>
          <div className="dislike-wrapper">
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
export default BestComments;

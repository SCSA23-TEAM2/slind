import "./css/ReComments.css";
import { useState, useEffect } from "react";
import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
import ReArrow from "./IconFolder/ReArrow";

const ReComments = ({
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

  const [stateIsLike, setStateIsLike] = useState(isLike);
  const [stateIsDislike, setStateIsDislike] = useState(isDislike);
  const [stateLikeCount, setStateLikeCount] = useState(like);
  const [stateDislikeCount, setStateDislikeCount] = useState(dislike);
  const [stateIsDeleted, setStateIsDeleted] = useState(isDeleted);
  const [stateCommentContent, setStateCommentContent] = useState(content);

  useEffect(() => {
    setStateIsLike(isLike);
    setStateIsDislike(isDislike);
    setStateLikeCount(like);
    setStateDislikeCount(dislike);
    setStateIsDeleted(isDeleted);
    setStateCommentContent(content);
  }, [content, like, dislike, isDeleted, isMine, isLike, isDislike]);

  const changelike = () => {
    if (stateIsLike) {
      commentCancelLike(item.commentPk);
      setStateIsLike(false);
      setStateLikeCount(stateLikeCount - 1);
    } else {
      commentLike(item.commentPk);
      setStateIsLike(true);
      setStateLikeCount(stateLikeCount + 1);
    }
  };
  const changedislike = () => {
    if (stateIsDislike) {
      commentCancelDislike(item.commentPk);
      setStateIsDislike(false);
      setStateDislikeCount(stateDislikeCount - 1);
    } else {
      commentDislike(item.commentPk);
      setStateIsDislike(true);
      setStateDislikeCount(stateDislikeCount + 1);
    }
  };

  return (
    <div className="ReComments-super-wrapper">
      <div className="Re-icon-wrapper">
        <ReArrow />
      </div>
      <div className="ReComments-wrapper">
        {stateIsDeleted ? (
          <div>삭제된 댓글입니다.</div>
        ) : (
          <>
            <div className="ReComments-header">
              <div className="ReComments-header-left">
                <div className="ReComments-author">{nickname}</div>
              </div>
            </div>

            <div className="ReComments-content">{stateCommentContent}</div>

            <div className="ReComments-bottom">
              <div className="ReComments-leftbutton-wrapper">
                {isMine && (
                  <div className="ReComments-Modify-button-wrapper">
                    <button>수정</button>
                  </div>
                )}
              </div>
              <div className="ReComments-preference-button-wrapper">
                <div className="ReComments-like-wrapper">
                  <button
                    className="like"
                    onClick={() => {
                      changelike();
                    }}
                  >
                    <Like size={30} color={stateIsLike ? "red" : "gray"} />
                  </button>
                  <div>{stateLikeCount}</div>
                </div>
                <div className="ReComments-dislike-wrapper">
                  <button
                    className="dislike"
                    onClick={() => {
                      changedislike();
                    }}
                  >
                    <DisLike
                      size={30}
                      color={stateIsDislike ? "red" : "gray"}
                    />
                  </button>
                  <div>{stateDislikeCount}</div>
                </div>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
};
export default ReComments;

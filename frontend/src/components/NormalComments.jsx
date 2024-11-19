import "./css/NormalComments.css";
import Like from "./iconFolder/Like";
import DisLike from "./iconFolder/DisLike";
import { useState, useEffect } from "react";
import { useInView } from "react-intersection-observer";
import InputReComment from "./InputReComment"; 
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

  const [toggleReplies, setToggleReplies] = useState(false); // 대댓글 표시 여부
  const [replies, setReplies] = useState([]); // 대댓글 리스트
  const [hasMoreReplies, setHasMoreReplies] = useState(false); // 더보기 버튼 표시 여부
  const [lastReplyPk, setLastReplyPk] = useState(0); // 마지막 대댓글의 PK

  const [newReply, setNewReply] = useState(""); // 대댓글 입력 값

  useEffect(() => {
    setStateIsLike(isLike);
    setStateIsDislike(isDislike);
    setStateLikeCount(like);
    setStateDislikeCount(dislike);
    setStateIsDeleted(isDeleted);
    setStateCommentContent(content);
  }, [content, like, dislike, isDeleted, isMine, isLike, isDislike]);
  
  const toggleRepliesHandler = async () => {
    setToggleReplies(!toggleReplies);

    if (!toggleReplies) {
      // 대댓글 표시를 켜는 경우
      await loadReplies(true); // 대댓글 데이터 로드 (초기화)
    }
  };

  const loadReplies = async (reset = false) => {
    try {
      const response = await axios.get(`/api/comment/re/${item.commentPk}?lastCommentPk=${lastReplyPk}`);

      const { replyList, hasNext } = response.data;

      setReplies((prevReplies) => (reset ? replyList : [...prevReplies, ...replyList])); // 초기화 or 추가
      setHasMoreReplies(hasNext); // 더보기 버튼 여부 설정
      if (replyList.length > 0) {
        setLastReplyPk(replyList[replyList.length - 1].commentPk); // 마지막 대댓글 PK 업데이트
      }
    } catch (error) {
      console.error("대댓글 로드 실패", error);
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


  const submitNewReply = async (reComment) => {
    if (newReply.trim() === "") return alert("대댓글 내용을 입력해주세요.");

    try {
      const response = await axios.post(`/api/comment/auth/re`, {
        	originateComment : item.commentPk,
        	content : reComment

      });

      const newReplyData = {
        originateComment : item.commentPk,
        content : reComment

      } // 새 대댓글 데이터
      setReplies((prevReplies) => [...prevReplies, newReplyData]); // 새 대댓글 추가
      setNewReply(""); // 입력 칸 초기화
    } catch (error) {
      console.error(error);

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
      {/* 대댓글 리스트 */}
      {toggleReplies && (
        <div className="NormalComments-replies">
          {replies.map((reply) => (
            <div key={reply.commentPk} className="NormalComments-reply">
              <div className="reply-author">{reply.nickname}</div>
              <div className="reply-content">{reply.commentContent}</div>
            </div>
          ))}

          {/* 대댓글 쓰기 */}
          <InputReComment postComment={submitNewReply}/>

          {/* 더보기 버튼 */}
          {hasMoreReplies && (
            <button className="load-more-replies" onClick={() => loadReplies()}>
              더보기
            </button>
          )}
        </div>
      )}
    </div>
  );
};
export default NormalComments;

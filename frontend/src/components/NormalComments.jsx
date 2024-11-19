import "./css/NormalComments.css";
import Like from "./icon/Like";
import DisLike from "./icon/DisLike";

const NormalComments = () => {
  const nickname = "김똑딱";
  const content = "상처치료해줄 사람 어디없나.";
  const isMine = true;
  const isDeleted = false;
  const like = 1000;
  const dislike = 10000;
  const isLike = true;
  const isDislike = false;
  const toggle = false; // 대댓글 보기 위함
  return (
    <div className="NormalComments-wrapper">
      {isDeleted ? (
        <div>삭제된 댓글입니다.</div>
      ) : (
        <>
          <div className="NormalComments-header">
            <div className="NormalComments-header-left">
              <div className="NormalComments-author">{nickname}</div>
            </div>
          </div>

          <div className="NormalComments-content">{content}</div>
        </>
      )}

      <div className="NormalComments-bottom">
        <div className="NormalComments-leftbutton-wrapper">
          <div className="NormalComments-reply-button-wrapper">
            <button>답글</button>
          </div>
          {isMine && !isDeleted && (
            <div className="NormalComments-Modify-button-wrapper">
              <button>수정</button>
            </div>
          )}
        </div>
        <div className="NormalComments-preference-button-wrapper">
          <div className="NormalComments-like-wrapper">
            <button className="like">
              <Like size={30} color={isLike ? "red" : "gray"} />
            </button>
            <div>{like}</div>
          </div>
          <div className="NormalComments-dislike-wrapper">
            <button className="dislike">
              <DisLike size={30} color={isDislike ? "red" : "gray"} />
            </button>
            <div>{dislike}</div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default NormalComments;

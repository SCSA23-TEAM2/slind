import "./css/PostHeaderMain.css";
import { Link } from "react-router-dom";

const PostHeaderMain = ({
  boardPk,
  articlePk,
  isMine,
  title,
  nickname,
  createdDttm,
  articleContent,
  gotoPostModifyForm,
  isJudgement,
  pi,
  handleLinkClick,
  gotoSuitForm,
  status,
}) => {
  console.log(pi);
  const ismine = isMine;
  const Title = title;
  const Nickname = nickname;
  const date = createdDttm;
  const boardName = pi?.boardName||null;
  const Content = articleContent;
  const IsJudgement = isJudgement;
  const kind = pi.kind;
  const BoardPk = boardPk;
  const ArticlePk = articlePk;
  const Status = status;
  console.log(
    ismine,
    Title,
    Nickname,
    date,
    boardName,
    Content,
    IsJudgement,
    kind,
    "status",
    Status
  );
  return (
    <>
      <div className="PostDetail-main-Header">
        <div className="PostDetail-main-Header-left">
          <div className="PostDetail-main-title">
            {Title}{" "}
            {Status &&
              (Status == "LOSE"
                ? "<패소>"
                : Status == "ON"
                ? "<재판 중>"
                : "<승소>")}
          </div>
          <div className="PostDetail-main-boardName">
            {kind == 0 ? (
              <Link
                to={`/board/${boardName}`}
                state={{
                  boardPk: pi.boardPk,
                  boardName: boardName,
                  kind: 0, //kind: 0 -> 일반 게시판, kind: 1 -> 재판게시판
                }}
              >
                {boardName} 게시판
              </Link>
            ) : ArticlePk == null ? (
              <Link
                to={`/board/${boardName}`}
                state={{
                  boardPk: BoardPk,
                  boardName: boardName,
                  kind: 0, //kind: 0 -> 일반 게시판, kind: 1 -> 재판게시판
                }}
              >
                피고 : 게시판
              </Link>
            ) : (
              <Link
                to={`/board/${boardName}/Post/${title}`}
                onClick={() => {
                  handleLinkClick({
                    boardName: boardName,
                    articlePk: ArticlePk,
                    kind: 0,
                  });
                }}
              >
                피고 : 게시글
              </Link>
            )}
          </div>
          <div className="PostDetail-main-author">{nickname}</div>
          <div className="PostDetail-main-date">{date}</div>
        </div>
        <div className="PostDetail-main-Header-right">
          {kind == 0 ? (
            isJudgement ? (
              <button className="PostDetail-main-button inCourt">
                재판 중
              </button>
            ) : isMine ? (
              <button
                className="PostDetail-main-button"
                onClick={() => {
                  gotoPostModifyForm();
                }}
              >
                수정하기
              </button>
            ) : (
              <button
                className="PostDetail-main-button"
                onClick={() => {
                  gotoSuitForm();
                }}
              >
                소송하기
              </button>
            )
          ) : (
            <div></div>
          )}
        </div>
      </div>
      <div className="PostDetail-main-content-wrapper">
        <div className="PostDetail-main-content">{Content}</div>
      </div>
    </>
  );
};
export default PostHeaderMain;

import "./css/MyContentItem.css";
import {Link} from "react-router-dom"
const MyContentItem = () => {
  const boardName = "AWS"
  const content = "무야호";
  const date = "2024. 11. 14. 11:29";
  return (
    <li>
      <div className="MyContentList-content">
        <div className="MyContentList-content-boardName">
          <Link to={`/board/${boardName}`}>{boardName}</Link>
        </div>
        <div className="MyContentList-content-title">
          <Link to={`/board/${boardName}/Post/${content}`}>
            {content}
          </Link>
        </div>
        <div className="MyContentList-content-date">{date}</div>
        {/* 재판중인 게ㅣㅅ판이나 게시글은 삭제가 아니라 재판중 이라고 표시하고 비활성화해야함 */}
        <div className="MyContentList-content-delete">
          <button
            onClick={() => {
              console.log("wow");
            }}
          >
            삭제
          </button>
        </div>
      </div>
    </li>
  );
};
export default MyContentItem;

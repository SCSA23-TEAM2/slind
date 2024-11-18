import "./css/EditBookmark.css";

const EditBookmark = () => {
  const isDone = true;
  const content = "알버스 퍼시발 울프릭 브라이언 덤블도어";
  return (
    <div className="EditBookmark-wrapper">
      <div className="EditBookmark-header">
        <h1>즐겨찾기 편집</h1>
        <div className="guide-line">즐겨찾기할 게시판에 체크</div>
      </div>
      <div className="EditBookmark-content">
        <ul className="bookmark-list">
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <input readOnly checked={isDone} type="checkbox" />
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
        </ul>
      </div>
      <div className="EditBookmark-bottom">
        <button className="bookmark-save-button">저장</button>
      </div>
    </div>
  );
};

export default EditBookmark;

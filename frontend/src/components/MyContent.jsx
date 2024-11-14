import "./css/MyContent.css";

const MyContent = () => {
  const content = "무야호";
  return (
    <div className="MyContent-wrapper">
      <div className="MyContent-header">
        <h1>(콘텐츠이름)</h1>
      </div>
      <div className="MyContent-content">
        <ul className="content-list">
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
          <li>
            <div className="bookmark-board-content">
              <div className="bookmark-board-name">{content}</div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default MyContent;

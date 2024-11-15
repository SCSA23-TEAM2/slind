import "./css/MyContentItem.css";

const MyContentItem = () => {
  const content = "무야호";
  const date = "2024. 11. 14. 11:29";
  return (
    <li>
      <div className="MyContentList-content">
        <div className="MyContentList-content-boardName">
          <a href="">asldfjkhsdkfhsdfls;hsdflhsd</a>
        </div>
        <div className="MyContentList-content-title">
          <a href="">
            동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세
          </a>
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

import "./css/MyContent.css";
import MyContentItem from "./MyContentItem";

const MyContent = () => {
  // const content = "무야호";
  return (
    <div className="MyContent-wrapper">
      <div className="MyContent-header">
        <h1>(콘텐츠이름)</h1>
      </div>
      <div className="MyContent-content">
        <ul className="content-list">
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
          <MyContentItem />
        </ul>
      </div>
    </div>
  );
};

export default MyContent;

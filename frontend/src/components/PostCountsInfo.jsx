import "./css/PostCountsInfo.css";
import Like from "./icon/Like";
import DisLike from "./icon/DisLike";
const PostCountsInfo = () => {
  return (
    <div className="PostCountsInfo-wrapper">
      <div className="PostCountsInfo-toggle-wrapper">
        <div className="PostCountsInfo-like">
          <button className="like-button">
            <Like size={80}></Like>
          </button>
          <div>10</div>
        </div>
        <div className="PostCountsInfo-dislike">
          <button className="dislike-button">
            <Like size={40}></Like>
          </button>
          <div>10</div>
        </div>
      </div>
      <div className="PostCountsInfo-nontoggle-wrapper">
        <div className="PostDetail-main-content-Views"></div>
        <div className="PostDetail-main-content-Comments"></div>
      </div>
    </div>
  );
};
export default PostCountsInfo;

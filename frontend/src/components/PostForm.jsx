import "./css/PostForm.css";
import { useState } from "react";
const PostForm = () => {
  const [contentLength, setContentLength] = useState(0);
  const [content, setContent] = useState("");
  const title = "제목";
  const board = "게시판";
  const contentLengthLimit = 2000;
  const boardTitle =
    "게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름";
  const onChange = (e) => {
    console.log(e.target.value);
    setContent(e.target.value);
    setContentLength(e.target.value.length);
    // setContentLength(e.target.length);
  };
  return (
    <div className="PostForm-wrapper">
      <h4>게시글 작성</h4>
      <div className="PostForm-Header">
        <div className="PostForm-title-wrapper">
          <div className="PostForm-title">{title}</div>
          <div className="PostForm-title-value">
            <textarea
              className="PostForm-title-input"
              name=""
              id=""
              placeholder="제목을 입력하세요. 최대 200자"
            ></textarea>
          </div>
        </div>
        <div className="PostForm-board-wrapper">
          <div className="PostForm-board">{board}</div>
          <div className="PostForm-board-value">{boardTitle}</div>
        </div>
      </div>
      <div className="PostForm-content">
        <textarea
          value={content}
          onChange={onChange}
          className="PostForm-content-input"
          name=""
          id=""
          placeholder="본문 작성(최대 4000자)"
        ></textarea>
        <div className="PostForm-length">
          {contentLength}/{contentLengthLimit}
        </div>
      </div>
      <div className="PostForm-Bottom">
        <button className="PostForm-submit-button">완성</button>
      </div>
    </div>
  );
};

export default PostForm;

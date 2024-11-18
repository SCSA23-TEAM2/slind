import { useLocation, useNavigate } from "react-router-dom";
import "./css/PostForm.css";
import { useState } from "react";
import useAxios from "../useAxios";
const PostForm = () => {
  const navigate = useNavigate();
  const axios = useAxios();
  const PostFormInfo = useLocation();
  const [contentLength, setContentLength] = useState(
    PostFormInfo.state.articleContent.length
  );
  const [content, setContent] = useState(PostFormInfo.state.articleContent);
  const [board, setBoard] = useState(PostFormInfo.state.Name);
  const [title, setTitle] = useState(PostFormInfo.state.title);

  const contentLengthLimit = 2000;
  const boardTitle =
    "게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름 게시판 이름";
  const onChange = (e) => {
    console.log(e.target.value);
    setContent(e.target.value);
    setContentLength(e.target.value.length);
    // setContentLength(e.target.length);
  };
  const onChangeTitle = (e) => {
    setTitle(e.target.value);
  };
  const SubmitPost = async () => {
    try {
      if (PostFormInfo.state.kind == 0) {
        const response = await axios.post(
          "http://localhost:8080/api/article/auth",
          {
            boardPk: PostFormInfo.state.pk,
            title: title,
            articleContent: content,
          }
        );
        navigate(`/board/${board}/Post/${title}`, {
          state: {
            articlePk: response.data.articlePk,
            boardName: board,
            kind: 0,
          },
        });
      } else if (PostFormInfo.state.kind == 2) {
        const response = await axios.put(
          "http://localhost:8080/api/article/auth",
          {
            boardPk: PostFormInfo.state.pk,
            title: title,
            articleContent: content,
          }
        );
        navigate(`/board/${board}/Post/${title}`, {
          state: {
            articlePk: response.data.articlePk,
            boardName: board,
            kind: 0,
          },
        });
      } else {
        console.log("아직", PostFormInfo.state.kind);
      }

      // console.log({
      //   boardPk: PostFormInfo.state.pk,
      //   title: title,
      //   articleContent: content,
      // });
    } catch (error) {
      console.error(error);
      navigate("/");
    }
  };

  return (
    <div className="PostForm-wrapper">
      <h4>
        {PostFormInfo.state.kind % 2 == 0 ? "게시글 작성" : "재판글 작성"}
      </h4>
      <div className="PostForm-Header">
        <div className="PostForm-title-wrapper">
          <div className="PostForm-title">제목</div>
          <div className="PostForm-title-value">
            <textarea
              value={title}
              className="PostForm-title-input"
              name=""
              id=""
              placeholder="제목을 입력하세요. 최대 200자"
              onChange={onChangeTitle}
            ></textarea>
          </div>
        </div>
        <div className="PostForm-board-wrapper">
          <div className="PostForm-board">게시판</div>
          <div className="PostForm-board-value">{board}</div>
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
        <button className="PostForm-submit-button" onClick={SubmitPost}>
          완성
        </button>
      </div>
    </div>
  );
};

export default PostForm;

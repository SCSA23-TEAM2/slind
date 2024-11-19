import { useLocation, useNavigate } from "react-router-dom";
import "./css/PostForm.css";
import { useState } from "react";
import useAxios from "../useAxios";
import Images from "./Images";

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
  // console.log(PostFormInfo.state);
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
    if (PostFormInfo.state.kind == 0) {
      try {
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
      } catch (error) {
        console.error();
      }
    } else if (PostFormInfo.state.kind == 2) {
      console.log(content);
      console.log(title);
      try {
        const response = await axios.put(
          "http://localhost:8080/api/article/auth",
          {
            articlePk: PostFormInfo.state.pk,
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
      } catch (error) {
        console.error(error);
      }
    } else {
      console.log(PostFormInfo.state);
      try {
        if (PostFormInfo.state.kind == 1) {
          const response = await axios.post(
            "http://localhost:8080/api/judgement/auth/article",
            {
              articlePk: PostFormInfo.state.pk,
              title: title,
              articleContent: content,
            }
          );
          navigate("/board/PeopleCourt", {
            state: {
              kind: 1,
            },
          });
        } else {
          console.log(PostFormInfo.state);
          const response = await axios.post(
            "http://localhost:8080/api/judgement/auth/board",
            {
              boardPk: PostFormInfo.state.pk,
              title: title,
              articleContent: content,
            }
          );
          navigate("/board/PeopleCourt", {
            state: {
              kind: 1,
            },
          });
        }
      } catch (error) {
        console.error(error);
      }
    }

    // console.log({
    //   boardPk: PostFormInfo.state.pk,
    //   title: title,
    //   articleContent: content,
    // });
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
          <div className="PostForm-board">
            {PostFormInfo.state.kind % 2 == 0 ? "게시판" : "피고"}
          </div>
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
        {/* <Images /> */}
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

import "./css/InputReComment.css";
import { useState } from "react";

const InputReComment = ({ postComment }) => {
  //postComment는 여기의 input값을 가져가서, post요청을 시키도록 한다.
  // 같이 전달해야하는 body
  // {
  //     "articlePk": "int",
  //     "content": "string"
  // }

  const [comment, setComment] = useState("");

  const onSubminComment = () => {
    console.log(comment);
    postComment(comment);
    setComment("");
  };

  return (
    <div className="InputReComment-wrapper">
      <div className="InputReComment-Header">
        <h3>답글 쓰기</h3>
      </div>
      <div className="InputReComment-Content">
        <div className="InputReComment-Input-wrapper">
          <textarea
            type="text"
            value={comment}
            onChange={(e) => {
              console.log(e.target.value);
              setComment(e.target.value);
            }}
            placeholder="답글을 입력하세요"
          />
        </div>
        <div className="InputReComment-button-wrapper">
          <button onClick={onSubminComment}>작성</button>
        </div>
      </div>
    </div>
  );
};

export default InputReComment;

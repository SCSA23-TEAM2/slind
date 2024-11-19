import "./css/InputComment.css"
import { useState } from "react";

const InputComment = ({postComment})=>{
    //postComment는 여기의 input값을 가져가서, post요청을 시키도록 한다.
    // 같이 전달해야하는 body
    // {
    //     "articlePk": "int",
    //     "content": "string"
    // }

    const [comment, setComment] = useState("");

    const onSubminComment =()=>{
        console.log(comment)
        postComment(comment)
    }

    return <div className="InputComment-wrapper">
        <div className="InputComment-Header">
            <h3>댓글 쓰기</h3>
        </div>
        <div className="InputComment-Content">
            <div className="InputComment-Input-wrapper">
            <textarea type="text"
            value={comment}
            onChange={(e)=>{
                console.log(e.target.value)
                setComment(e.target.value)
            }} />
            </div>
            <div className="InputComment-button-wrapper">
                <button onClick={onSubminComment}>작성</button>
            </div>
        </div>
    </div>
}

export default InputComment;
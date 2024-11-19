import "./css/ReComments.css"
import Like from "./icon/Like";
import DisLike from "./icon/DisLike";
import ReArrow from "./Icon/ReArrow";

const ReComments = ()=> {
    const nickname = "김똑딱";
    const content = "상처치료해줄 사람 어디없나."
    const isMine = true;
    const isDeleted = false;
    const like = 1000;
    const dislike = 10000;
    const isLike = true;
    const isDislike = false;
    const toggle = false; // 대댓글 보기 위함
    return <div className="ReComments-super-wrapper">
        <div className="Re-icon-wrapper">
            <ReArrow/>
        </div>
    <div className="ReComments-wrapper">
            <div className="ReComments-header">
            <div className="ReComments-header-left">
            <div className="ReComments-author">{nickname}</div>
            </div>

        </div>

        <div className="ReComments-content">{content}</div>
        
        <div className="ReComments-bottom">
            <div className="ReComments-leftbutton-wrapper">
            {isMine &&  <div className="ReComments-Modify-button-wrapper">
                <button>수정</button>
            </div>}

            </div>
            <div className="ReComments-preference-button-wrapper">
                <div className="ReComments-like-wrapper">
                <button className="like"><Like size={30} color={isLike ? "red" : "gray"}/></button>
                <div>{like}</div>
                </div>
                <div className="ReComments-dislike-wrapper">
                <button className="dislike"><DisLike size={30} color={isDislike ? "red" : "gray"}/></button>
                <div>{dislike}</div>
                </div>
            </div>
        </div>
    
    </div>
    </div>
}
export default ReComments
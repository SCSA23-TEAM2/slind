import "./css/BestComments.css"
import Like from "./icon/Like";
import DisLike from "./icon/DisLike";

const BestComments = ()=> {
    const nickname = "김똑딱";
    const content = "상처치료해줄 사람 어디없나."
    const isMine = true;
    const isDeleted = false;
    const like = 1000;
    const dislike = 10000;
    const isLike = true;
    const isDislike = false;
    const toggle = false; // 대댓글 보기 위함
    return <div className="BestComments-wrapper">
        {isDeleted ? <div>삭제된 댓글입니다.</div> : 
            <>
            <div className="BestComments-header">
            <div className="BestComments-header-left">
            <div className="BestComments-author">{nickname}</div>
            </div>
            <div className="BestComments-header-right">
                <div className="Best-wrapper">BEST</div>
            </div>

        </div>

        <div className="BestComments-content">{content}</div>
            </>
        }
        
        <div className="BestComments-bottom">
            <div className="BestComments-leftbutton-wrapper">
            <div className="reply-button-wrapper">
                <button>답글</button>
            </div>
            {isMine && !isDeleted &&   <div className="Modify-button-wrapper">
                <button>수정</button>
            </div>}

            </div>
            <div className="preference-button-wrapper">
                <div className="like-wrapper">
                <button className="like"><Like size={30} color={isLike ? "red" : "gray"}/></button>
                <div>{like}</div>
                </div>
                <div className="dislike-wrapper">
                <button className="dislike"><DisLike size={30} color={isDislike ? "red" : "gray"}/></button>
                <div>{dislike}</div>
                </div>
            </div>
        </div>
    
    </div>
}
export default BestComments
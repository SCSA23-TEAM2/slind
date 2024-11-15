import "./css/PostHeaderMain.css";

const PostHeaderMain = () => {
  const isMine = true;
  const title1 =
    "안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  \
    안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  \
    안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  안녕하세요  ";
  const title2 = "밥드실 분 구함";
  const author = "맥도날드 트럼프";
  const date = "2018-03-09 14:21";
  const boardName = "삼성전자";
  const content =
    "What is Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. Why do we use it?It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).Where does it come from?Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure LatinBC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum,, comes from a line in section 1.10.32.The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from de Finibuor non-characteristic words etc.";
  return (
    <>
      <div className="PostDetail-main-Header">
        <div className="PostDetail-main-Header-left">
          <div className="PostDetail-main-title">{title2}</div>
          <div className="PostDetail-main-boardName">
            <a href="">{boardName} 게시판</a>
          </div>
          <div className="PostDetail-main-author">{author}</div>
          <div className="PostDetail-main-date">{date}</div>
        </div>
        <div className="PostDetail-main-Header-right">
          {isMine ? (
            <button className="PostDetail-main-button">수정하기</button>
          ) : (
            <button className="PostDetail-main-button">소송하기</button>
          )}
        </div>
      </div>
      <div className="PostDetail-main-content-wrapper">
        <div className="PostDetail-main-content">{content}</div>
      </div>
    </>
  );
};
export default PostHeaderMain;

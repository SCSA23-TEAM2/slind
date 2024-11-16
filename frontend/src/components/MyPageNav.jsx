import "./css/MyPageNav.css";
// import Menu from "./icon/Menu";
const mockitem1 = {
  id: 1,
  myPagePk: 1,
  boardTitle: "마이페이지",
};
const mockitem2 = {
  id: 2,
  myPagePk: 2,
  boardTitle: "즐겨찾기 편집",
};
const mockitem3 = {
  id: 3,
  myPagePk: 3,
  boardTitle: "내가 만든 게시판",
};
const mockitem4 = {
  id: 4,
  myPagePk: 4,
  boardTitle: "내가 쓴 글",
};
const mockitem5 = {
  id: 5,
  myPagePk: 5,
  boardTitle: "내가 쓴 댓글",
};
const mockitem6 = {
  id: 6,
  myPagePk: 6,
  boardTitle: "내가 소송한 재판",
};
const wholeMock = [];
// for (let i = 0; i < 30; i++) {
//   wholeMock.push(mockitem);
// }
wholeMock.push(mockitem1);
wholeMock.push(mockitem2);
wholeMock.push(mockitem3);
wholeMock.push(mockitem4);
wholeMock.push(mockitem5);
wholeMock.push(mockitem6);
const MyPageNav = () => {
  return (
    <div className="MyPageNav-wrapper">
      <div className="MyPageNav-header">
        <div className="Menu-icon">
          {/* <Menu /> */}
        </div>
        <h2>메뉴</h2>
      </div>
      <div className="MyPageNav-board">
        <ul className="MyPageNav-board-list">
          {wholeMock.map((item) => (
            <a key={item.id} className="MyPageNav-board-item" href="">
              <li>{item.boardTitle}</li>
            </a>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default MyPageNav;

import "./css/Nav.css";
import { useState, useRef } from "react";
import BookMark from "./icon/BookMark";

const mockitem1 = {
  boardPk: 1,
  boardTitle: "상처치료해줄사람어디없나가만히놔두다간끊임없이덧나",
};
const mockitem2 = {
  boardPk: 2,
  boardTitle: "와우",
};
const mockitem3 = {
  boardPk: 3,
  boardTitle: "백준오브레전드",
};
const mockitem4 = {
  boardPk: 4,
  boardTitle: "t",
};
const wholeMock = [];
// for (let i = 0; i < 30; i++) {
//   wholeMock.push(mockitem);
// }
wholeMock.push(mockitem1);
wholeMock.push(mockitem2);
wholeMock.push(mockitem3);
wholeMock.push(mockitem4);
const Nav = () => {
  const idRef = useRef(1);

  const [boardList, setBoardList] = useState(wholeMock);
  // const [inputBoard, setInputBoard] = useState("");
  const onChange = (e) => {
    if (e.target.value === "") setBoardList(wholeMock);
    else {
      const filtered = wholeMock.filter((item) => {
        if (
          item.boardTitle.toLowerCase().includes(e.target.value.toLowerCase())
        )
          return true;
      });
      setBoardList(filtered);
    }
  };
  // useEffect(() => {
  //   // console.log(`count: ${count} , ${input}`);
  // }, [inputBoard]);
  return (
    <div className="Nav-wrapper">
      <div className="Nav-header">
        <div className="Bookmark-icon">
          <BookMark />
        </div>
        <h2 className="Bookmark-title">즐겨찾기</h2>
      </div>
      <div className="Nav-board">
        <ul className="Nav-board-list">
          {boardList.map((item) => (
            <a key={item.boardPk} className="Nav-board-item" href="">
              <li>{item.boardTitle}</li>
            </a>
          ))}
        </ul>
      </div>
      <div className="Nav-header">
        <h2 className="board-title">게시판</h2>
      </div>
      <div className="Nav-search">
        <input
          name="boardname"
          onChange={onChange}
          type="text"
          placeholder="게시판 입력"
        />
      </div>
      <div className="Nav-board">
        <ul className="Nav-board-list">
          {boardList.map((item) => (
            <a key={item.boardPk} className="Nav-board-item" href="">
              <li>{item.boardTitle}</li>
            </a>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default Nav;

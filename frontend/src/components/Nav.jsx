import "./css/Nav.css";
import { useState } from "react";
const Nav = () => {
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
  const wholeMock = [];
  // for (let i = 0; i < 30; i++) {
  //   wholeMock.push(mockitem);
  // }
  wholeMock.push(mockitem1);
  wholeMock.push(mockitem2);
  wholeMock.push(mockitem3);
  const [boardList, setBoardList] = useState(wholeMock);
  // const [inputBoard, setInputBoard] = useState("");
  const onChange = (e) => {
    if (e.target.value === "") setBoardList(wholeMock);
    else {
      const filtered = wholeMock.filter((item) => {
        if (item.boardTitle.includes(e.target.value)) return true;
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
        <h2>게시판</h2>
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
          {/* <a className="Nav-board-item" href="">
            <li>도레미</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미파솔라시도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시
            </li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미파솔라시도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시
            </li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미파솔라시도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시
            </li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>도레미파솔라시도</li>
          </a>
          <a className="Nav-board-item" href="">
            <li>
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시
            </li>
          </a> */}
          {/* <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시도
            </a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시도
            </a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시도
            </a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시도
            </a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">도레미파솔라시도도레미파솔라시도</a>
          </li>
          <li className="Nav-board-item">
            <a href="">
              도레미파솔라시도도레미파솔라시도도레미파솔라시도도레미파솔라시도
            </a>
          </li>
          <li className="Nav-board-item">
            <a href="">도</a>
          </li> */}
        </ul>
      </div>
    </div>
  );
};

export default Nav;

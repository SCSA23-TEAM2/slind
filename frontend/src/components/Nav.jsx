import "./css/Nav.css";
import { useState, useRef } from "react";
import BookMark from "./icon/BookMark";
import Modal from "./Modal/CreateBoardModal";

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

  // useState를 사용하여 open상태를 변경한다. (open일때 true로 만들어 열리는 방식)
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
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
      <div className="Nav-header second-header">
        <h2 className="board-title">게시판</h2>
        <button onClick={openModal}>새로 만들기</button>
        <Modal open={modalOpen} close={closeModal} header="게시판 생성">
          {/* Modal.js <main> {props.children} </main>에 내용이 입력된다. 리액트 */}
          함수형 모달 팝업창입니다. 쉽게 만들 수 있어요. 같이 만들어봐요!
        </Modal>
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

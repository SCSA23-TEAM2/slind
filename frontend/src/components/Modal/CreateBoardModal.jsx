// import React from "react";
import axios from "axios";
import "../css/CreateBoardModal.css";

const Modal = (props) => {
  const validateBoardName = async () => {
    // 게시판 이름 중복 체크
    // 중복이면 false, 중복이 아니면 true

    // validation
    if (document.querySelector("input").value.length == 0) {
      alert("게시판 이름을 입력해주세요.");
      document.querySelector("input").value = "";
      document.querySelector("input").focus();
      return;
    } else if (document.querySelector("input").value.length > 15) {
      alert("게시판 이름은 15자 이하로 입력해주세요.");
      document.querySelector("input").value = "";
      document.querySelector("input").focus();
      return;
    }

    try {
      console.log(document.querySelector("input").value);
      const response = await axios.get(
        "http://localhost:8080/api/board/check?title=" + document.querySelector("input").value,
      )
      if (response.status == 200) {
        try {
          const create_response = await axios.post(
          "http://localhost:8080/api/board/auth",
          {
            title: document.querySelector("input").value,
          });
          if (create_response.status == 200) {
            // created board
            alert("게시판이 생성되었습니다.");
            close();
          }
        } catch (error) {
          if (error.response.status == 400) {
            alert("게시판 이름은 15자 이하로 입력해주세요.");
            document.querySelector("input").value = "";
            document.querySelector("input").focus();
          } else if (error.response.status == 401) {
            alert("아직 게시판을 생성할 수 없습니다.");
            document.querySelector("input").value = "";
            document.querySelector("input").focus();
          } else if (error.response.status == 409) {
            alert("이미 존재하는 게시판 이름입니다.");
            document.querySelector("input").value = "";
            document.querySelector("input").focus();
          }
        }
      }
    } catch (error) {
      console.log(error);
      if (error.response.status == 400) {
        alert("게시판 이름은 15자 이하로 입력해주세요.");
        document.querySelector("input").value = "";
        document.querySelector("input").focus();
      } else if (error.response.status == 401) {
        alert("이미 존재하는 게시판 이름입니다.");
        document.querySelector("input").value = "";
        document.querySelector("input").focus();
      }
    }
  };

  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header } = props;

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          <header>
            {header}
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>
            <input type="text" placeholder="최대 15자" />
          </main>
          <footer>
            <button className="close" onClick={validateBoardName}>
              생성
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};
export default Modal;

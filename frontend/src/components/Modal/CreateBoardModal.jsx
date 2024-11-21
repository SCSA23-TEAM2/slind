// import React from "react";
// import httpAxios from "../../api/httpAxios";
import useAxios from "../../api/useAxios";
import "../css/CreateBoardModal.css";
import { useAuth } from "../../AuthContext";
import { useNavigate } from "react-router-dom";

const Modal = (props) => {
  const axios = useAxios();
  const navigate = useNavigate();
  const { accessToken } = useAuth(); // JWT 토큰을 가져옴
  const createBoard = async () => {
    if (!accessToken) {
      // JWT 토큰이 없는 경우 로그인 페이지로 리다이렉트
      alert("로그인이 필요합니다.");
      navigate("/Login");
      return;
    }
    // validation
    if (document.querySelector("input").value.trim().length == 0) {
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
      const create_response = await axios.post("/api/board/auth", {
        title: document.querySelector("input").value,
      });
      if (create_response.status == 200) {
        // created board
        alert("게시판이 생성되었습니다.");
        reload();
        close();
      }
    } catch (error) {
      if (error.response.status == 400) {
        alert("게시판 이름은 15자 이하로 입력해주세요.");
        document.querySelector("input").value = "";
        document.querySelector("input").focus();
      } else if (error.response.status == 409) {
        alert("이미 존재하는 게시판 이름입니다.");
        document.querySelector("input").value = "";
        document.querySelector("input").focus();
      } else if (error.response.status === 404) {
        alert("어딜 감히... 넌 추방이다.");
        close();
      }
    }
  };

  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header, reload } = props;

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
            <button className="close" onClick={createBoard}>
              생성
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};
export default Modal;

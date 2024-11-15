import { useState } from "react";
import "./css/MyPage.css";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const MyPage = () => {
  const options = { "너희 어머니는?": 0, "형은 뭐해?": 1, "니 동생뭐함?": 2 };
  const [curOption, setCurOption] = useState("너희 아버지 뭐하시니?");
  const tempNickname =
    "안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안";
  return (
    <div className="MyPage-wrapper">
      <div className="MyPage-header">
        <h1>마이페이지</h1>
      </div>
      <div className="MyPage-content">
        <div className="MyPage-item-wrapper id">
          <div className="item-header">아이디</div>
          <div className="item-content">sdfsdfgfds</div>
        </div>
        <div className="MyPage-item-wrapper nickname">
          <div className="item-header">닉네임</div>
          <div className="item-content">
            <div className="input-check">
              <input
                className="nickname-input"
                type="text"
                value={tempNickname}
              />
              {/* <div className="nickname-check-icon">asd</div> */}
              <div className="nickname-check-button">
                <button>중복체크</button>
              </div>
            </div>
            <div className="nickname-check-warning">
              사용 가능한 닉네임입니다.
            </div>
          </div>
        </div>
        <div className="MyPage-item-wrapper pwdQ">
          <div className="item-header">비밀번호 질문</div>
          <div className="item-content">
            <Dropdown
              options={Object.keys(options)}
              onChange={(e) => {
                console.log(options[e.value]);
                setCurOption(e.value);
              }}
              value={curOption}
              placeholder="정렬 기준"
            />
          </div>
        </div>
        <div className="MyPage-item-wrapper pwdA">
          <div className="item-header">비밀번호 질문 답변</div>
          <div className="item-content">
            <input
              className="nickname-input"
              type="text"
              value={tempNickname}
            />
          </div>
        </div>
      </div>
      <div className="MyPage-bottom">
        <button className="withdraw-button">회원탈퇴</button>
        <button className="save-button">저장</button>
      </div>
    </div>
  );
};

export default MyPage;

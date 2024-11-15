import "./css/FindPassword.css";
import { useState } from "react";
import Logo from "../assets/RealLogoWithoutBackground.png";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";
const options = { "너희 어머니는?": 0, "형은 뭐해?": 1, "니 동생뭐함?": 2 };

const FindPassword = () => {
  const [curOption, setCurOption] = useState("너희 어머니는?");
  return (
    <div className="FindPassword-body-wrapper">
      <div className="FindPassword-wrapper">
        <div className="FindPassword-header">
          <a href="">
            <img src={Logo} alt="logo" />
          </a>
        </div>
        <div className="FindPassword-content">
          <div className="FindPassword-Id">
            <input
              type="text"
              className="FindPassword-id-input"
              placeholder="아이디"
            />
            <div className="FindPassword-id-warning">
              아이디를 입력해주세요!
            </div>
          </div>
          <div className="FindPassword-pwdQ">
            <div>비밀번호 질문</div>
            <div className="pwdQ-dropdown">
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
          <div className="FindPassword-pwdA">
            <div>비밀번호 질문답변</div>
            <textarea
              type="text"
              className="FindPassword-pwdA-input"
              placeholder="비밀번호 질문답변"
              name=""
              id=""
            ></textarea>
          </div>
        </div>
        <div className="FindPassword-bottom">
          <button className="FindPassword-check-button">확인</button>
        </div>
      </div>
    </div>
  );
};

export default FindPassword;

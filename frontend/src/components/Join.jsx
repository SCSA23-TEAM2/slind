import "./css/Join.css";
import { useState } from "react";
import Logo from "../assets/RealLogoWithoutBackground.png";
import PwdConfig from "./PwdConfig";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";
const options = { "너희 어머니는?": 0, "형은 뭐해?": 1, "니 동생뭐함?": 2 };

const Join = () => {
  const [curOption, setCurOption] = useState("너희 어머니는?");
  return (
    <div className="Join-body-wrapper">
      <div className="Join-wrapper">
        <div className="Join-Header">
          <a href="">
            <img src={Logo} alt="logo" />
          </a>
        </div>
        <div className="Join-content">
          <div className="Join-id-wrapper">
            <div>아이디 입력</div>
            <div className="Join-id-content">
              <input type="text" placeholder="4자~16자 (영어,숫자만)" />
              <div>
                <button onClick={() => console.log("중복체크")}>
                  중복체크
                </button>
              </div>
            </div>
            <div className="Join-warning">아이디를 입력해주세요!</div>
          </div>
          <div className="Join-pwd-wrapper">
            <PwdConfig />
          </div>
          <div className="Join-nickname-wrapper">
            <div>닉네임 입력</div>
            <div className="Join-nickname-content">
              <input type="text" placeholder="4자~16자 (영어,숫자만)" />
              <div>
                <button onClick={() => console.log("중복체크")}>
                  중복체크
                </button>
              </div>
            </div>
            <div className="Join-warning">닉네임을 입력해주세요!</div>
          </div>

          <div className="Join-pwdQA-wrapper">
            <div className="Join-pwdQ">
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
            <div className="Join-pwdA">
              <div>비밀번호 질문답변</div>
              <textarea
                type="text"
                className="Join-pwdA-input"
                placeholder="비밀번호 질문답변"
                name=""
                id=""
              ></textarea>
              <div className="Join-warning">닉네임을 입력해주세요!</div>
            </div>
          </div>
        </div>
        <div className="Join-bottom">
          <button className="Join-complete-button">회원 가입</button>
        </div>
      </div>
    </div>
  );
};

export default Join;

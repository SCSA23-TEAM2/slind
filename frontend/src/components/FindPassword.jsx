import "./css/FindPassword.css";
import { useState } from "react";
import Logo from "../assets/RealLogoWithoutBackground.png";

const FindPassword = () => {
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
          </div>
          <div className="FindPassword-pwdQ">
            <div>비밀번호 질문</div>
          </div>
          <div className="FindPassword-pwdA"></div>
        </div>
        <div></div>
      </div>
    </div>
  );
};

export default FindPassword;

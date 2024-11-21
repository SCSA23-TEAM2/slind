import "./css/PwdConfig.css";

import ViewPwd from "./iconFolder/ViewPwd";
import LockPwd from "./iconFolder/LockPwd";
import { useState } from "react";

const PwdConfig = ({ pwd1, setPwd1, pwd2, setPwd2 }) => {
  const [isPwdVisible1, setIsPwdVisible1] = useState(false); // 비밀번호 보기 여부
  const [isPwdVisible2, setIsPwdVisible2] = useState(false); // 비밀번호 확인 보기 여부
  const [pwdMessage, setPwdMessage] = useState(""); // 비밀번호 유효성 메시지
  const [pwdConfirmMessage, setPwdConfirmMessage] = useState(""); // 비밀번호 확인 메시지

  // 비밀번호 유효성 검사
  const validatePassword = (value) => {
    const isValid =
      value.length >= 8 &&
      value.length <= 20 &&
      /[a-zA-Z]/.test(value) && // 영문 포함
      /[0-9]/.test(value) && // 숫자 포함
      /[!@#$%^&*(),.?":{}|<>]/.test(value); // 특수문자 포함
    return isValid;
  };

  // 비밀번호 입력값 변경 처리
  const handlePwdChange1 = (e) => {
    const value = e.target.value;
    setPwd1(value);

    if (validatePassword(value)) {
      setPwdMessage(""); // 조건 충족 시 메시지 비움
    } else {
      setPwdMessage("비밀번호는 8~20자, 영문/특수문자/숫자로 구성되어야 합니다.");
    }

    // 비밀번호 확인 메시지도 업데이트
    if (pwd2 && value !== pwd2) {
      setPwdConfirmMessage("비밀번호 확인이 불일치합니다.");
    } else if (pwd2 && value === pwd2) {
      setPwdConfirmMessage("비밀번호 확인이 일치합니다.");
    }
  };

  // 비밀번호 확인 입력값 변경 처리
  const handlePwdChange2 = (e) => {
    const value = e.target.value;
    setPwd2(value);

    if (!validatePassword(value)) {
      setPwdConfirmMessage("비밀번호는 8~20자, 영문/특수문자/숫자로 구성되어야 합니다.");
    } else if (value !== pwd1) {
      setPwdConfirmMessage("비밀번호 확인이 불일치합니다.");
    } else {
      setPwdConfirmMessage("비밀번호 확인이 일치합니다.");
    }
  };

  return (
    <>
      {/* 비밀번호 입력 */}
      <div className="PwdConfig-input-wrapper1">
        <div>비밀번호</div>
        <div className="PwdConfig-input-content1">
          <input
            type={isPwdVisible1 ? "text" : "password"} // 보기/숨기기 기능
            value={pwd1}
            className="PwdConfig-input1"
            placeholder="8자~20자(영어,숫자,특수문자)"
            onChange={handlePwdChange1}
          />
          <button
            className="SeePwd"
            onClick={() => setIsPwdVisible1(!isPwdVisible1)}
          >
            {isPwdVisible1 ? <LockPwd /> : <ViewPwd />}
          </button>
        </div>
        <div className="PwdConfig-pwd-warning">{pwdMessage}</div>
      </div>

      {/* 비밀번호 확인 */}
      <div className="PwdConfig-input-wrapper2">
        <div>비밀번호 다시 입력</div>
        <div className="PwdConfig-input-content2">
          <input
            type={isPwdVisible2 ? "text" : "password"} // 보기/숨기기 기능
            value={pwd2}
            className="PwdConfig-input2"
            placeholder="다시 입력"
            onChange={handlePwdChange2}
          />
          <button
            className="SeePwd"
            onClick={() => setIsPwdVisible2(!isPwdVisible2)}
          >
            {isPwdVisible2 ? <LockPwd /> : <ViewPwd />}
          </button>
        </div>
        <div className="PwdConfig-pwd-warning">{pwdConfirmMessage}</div>
      </div>
    </>
  );
};

export default PwdConfig;

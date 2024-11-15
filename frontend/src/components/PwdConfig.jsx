import "./css/PwdConfig.css";

import ViewPwd from "./icon/ViewPwd";
import LockPwd from "./icon/LockPwd";
import { useState } from "react";

const PwdConfig = () => {
  const [invisible1, setInvisible1] = useState(true);
  const [invisible2, setInvisible2] = useState(true);
  const [pwd1, setPwd1] = useState("");
  const [pwd2, setPwd2] = useState("");
  const onChange1 = (e) => {
    setPwd1(e.target.value);
  };
  const onChange2 = (e) => {
    setPwd2(e.target.value);
  };
  return (
    <>
      <div className="PwdConfig-input-wrapper1">
        <div>비밀번호</div>
        <div className="PwdConfig-input-content1">
          <input
            type={invisible1 ? "password" : "text"}
            value={pwd1}
            className="PwdConfig-input1"
            placeholder="8자~20자(영어,숫자,특수문자)"
            onChange={onChange1}
          />
          {invisible1 ? (
            <button
              className="SeePwd"
              onClick={() => {
                setInvisible1(!invisible1);
              }}
            >
              <ViewPwd />
            </button>
          ) : (
            <button
              className="SeePwd"
              onClick={() => {
                setInvisible1(!invisible1);
              }}
            >
              <LockPwd />
            </button>
          )}
        </div>
        <div className="PwdConfig-pwd-warning">비밀번호 입력이 개판이다..</div>
      </div>
      <div className="PwdConfig-input-wrapper2">
        <div>비밀번호 다시 입력</div>
        <div className="PwdConfig-input-content2">
          <input
            type={invisible2 ? "password" : "text"}
            value={pwd2}
            className="PwdConfig-input2"
            placeholder="다시 입력"
            onChange={onChange2}
          />
          {invisible2 ? (
            <button
              className="SeePwd"
              onClick={() => {
                setInvisible2(!invisible2);
              }}
            >
              <ViewPwd />
            </button>
          ) : (
            <button
              className="SeePwd"
              onClick={() => {
                setInvisible2(!invisible2);
              }}
            >
              <LockPwd />
            </button>
          )}
        </div>
        <div className="PwdConfig-pwd-warning">비밀번호 입력이 개판이다..</div>
      </div>
    </>
  );
};

export default PwdConfig;

import "./css/Login.css";
import { useState } from "react";
import {Link} from "react-router-dom"
import Logo from "../assets/RealLogoWithoutBackground.png";

const Login = () => {
  const [input, setInput] = useState({
    id: "",
    password: "",
  });
  const onChange = (e) => {
    console.log(e.target.name, e.target.value);
    setInput({
      ...input,
      [e.target.name]: e.target.value,
    });
  };
  const chechValueForLogin = () => {
    console.log(input.id, input.password);
  };

  return (
    <div className="body-wrapper">
      <div className="login-wrapper">
        <div className="login-header-content">
          <Link to="/">
            <img src={Logo} alt="logo" />
          </Link>
        </div>
        <div className="login-warning"></div>
        <div className="login-form-wrapper">
          <input
            name="id"
            value={input.id}
            onChange={onChange}
            type="text"
            placeholder="아이디 입력"
          />
          <input
            name="password"
            value={input.password}
            onChange={onChange}
            type="password"
            placeholder="비밀번호 입력"
          />
          <button onClick={chechValueForLogin}>로그인</button>
        </div>
        <div className="login-other-wrapper">
          <div className="login-other-content">
            <div>비밀번호 기억이 안나세요?</div>
            <button>비밀 번호 찾기</button>
          </div>
          <div className="login-other-content">
            <div>회원이 아니세요?</div>
            <button>회원가입 하기</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;

import "./css/Login.css";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Logo from "../assets/RealLogoWithoutBackground.png";
import { useAuth } from "../AuthContext";
import axios from "axios";
const Login = () => {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [input, setInput] = useState({
    memberId: "",
    memberPassword: "",
  });
  const onChange = (e) => {
    console.log(e.target.name, e.target.value);
    setInput({
      ...input,
      [e.target.name]: e.target.value,
    });
  };
  const handlePwdKeyDown = (e) => {
    if (e.key === "Enter") {
      Login();
    }
  };
  const Login = async () => {
    console.log(input);
    try {
      const response = await axios.post(
        "http://localhost:8080/api/login",
        input
      );

      const tokens = response.data; // Assumes the response contains access_token and refresh_token
      console.log(tokens);
      login(tokens); // Set the tokens in context and localStorage

      // Redirect or do something after login

      navigate("/");
    } catch (error) {
      alert("아이디나 비밀번호 정보가 일치하지 않습니다!");
      setInput({
        memberId: "",
        memberPassword: "",
      });
      console.error("Login failed:", error);
    }
  };

  const chechValueForLogin = () => {
    Login();
    // setData(prevData => [...prevData, ...newData]);
    // setHasMore(newData.length > 0);
  };
  const gotoJoin = () => {
    navigate("/Join");
  };
  const gotoFindPassword = () => {
    navigate("/FindPassword");
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
            name="memberId"
            value={input.memberId}
            onChange={onChange}
            type="text"
            placeholder="아이디 입력"
          />
          <input
            name="memberPassword"
            value={input.memberPassword}
            onChange={onChange}
            type="password"
            placeholder="비밀번호 입력"
            onKeyDown={handlePwdKeyDown}
          />
          <button onClick={chechValueForLogin}>로그인</button>
        </div>
        <div className="login-other-wrapper">
          <div className="login-other-content">
            <div>비밀번호 기억이 안나세요?</div>
            <button onClick={gotoFindPassword}>비밀 번호 찾기</button>
          </div>
          <div className="login-other-content">
            <div>회원이 아니세요?</div>
            <button onClick={gotoJoin}>회원가입 하기</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;

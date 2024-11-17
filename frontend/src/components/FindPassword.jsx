import "./css/FindPassword.css";
import { useState } from "react";
import {Link, useNavigate} from "react-router-dom"
import Logo from "../assets/RealLogoWithoutBackground.png";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";
const options = { "너희 어머니는?": 0, "형은 뭐해?": 1, "니 동생뭐함?": 2 };

const FindPassword = () => {
  const [userId, setUserId] = useState("")
  const [okUserId, setOkUserId] = useState(false);
  const [userIdWarning, setUserIdWarning] = useState("");
  const [curOption, setCurOption] = useState("너희 어머니는?");
  const navigate = useNavigate();
  const gotoPasswordConfig = () =>{
    navigate("/PasswordConfig", {
      state : {
        userid: userId
      }
    })
  }
  const onChange = (e) => {
    if (e.target.value === "") setUserIdWarning("아이디를 입력해주세요!")
    else {
      //빈칸, 글자수 제한, 정규표현식 체크 
      setOkUserId(true);
      setUserIdWarning("")
    }
    console.log(e.target.value)
    setUserId(e.target.value)
  };
  return (
    <div className="FindPassword-body-wrapper">
      <div className="FindPassword-wrapper">
        <div className="FindPassword-header">
          <Link to="/">
            <img src={Logo} alt="logo" />
          </Link>
        </div>
        <div className="FindPassword-content">
          <div className="FindPassword-Id">
            <input
            value={userId}
              type="text"
              className="FindPassword-id-input"
              placeholder="아이디"
              onChange={onChange}
            />
            <div className="FindPassword-id-warning">
              {userIdWarning}
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
                        <div className="FindPassword-id-warning">
              아이디를 입력해주세요!
            </div>
          </div>
        </div>
        <div className="FindPassword-bottom">
          <button onClick={gotoPasswordConfig} className="FindPassword-check-button">확인</button>
        </div>
      </div>
    </div>
  );
};

export default FindPassword;

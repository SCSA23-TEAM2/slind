import "./css/Join.css";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate  } from "react-router-dom";
import Logo from "../assets/RealLogoWithoutBackground.png";
import PwdConfig from "./PwdConfig";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";

const Join = () => {
  const navigate = useNavigate(); 

  const [curOption, setCurOption] = useState("선택하세요");
  const [dropdownOptions, setDropdownOptions] = useState([]);
  const [questionMap, setQuestionMap] = useState({});
  const [pwdAnswer, setPwdAnswer] = useState(""); // 비밀번호 질문 답변
  const [pwdAnswerMessage, setPwdAnswerMessage] = useState(""); // 비밀번호 질문 답변 메시지

  const [memberId, setMemberId] = useState("");
  const [idValidMessage, setIdValidMessage] = useState("아이디를 입력해주세요!");
  const [memberIdCheck, setMemberIdCheck] = useState(false);
  const [isIdFocused, setIsIdFocused] = useState(false);

  const [nickname, setNickname] = useState("");
  const [nicknameValidMessage, setNicknameValidMessage] = useState("닉네임을 입력해주세요!");
  const [nicknameCheck, setNicknameCheck] = useState(false);
  const [isNicknameFocused, setIsNicknameFocused] = useState(false);

  const [pwd1, setPwd1] = useState("");
  const [pwd2, setPwd2] = useState("");

  // 비밀번호 질문 가져오기
  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/question");
        const questionData = response.data;

        const questionTexts = questionData.map((item) => item.questionText);
        setDropdownOptions(questionTexts);
        const questionMapping = questionData.reduce((acc, item) => {
          acc[item.questionPk] = item.questionText;
          return acc;
        }, {});
        setQuestionMap(questionMapping);
      } catch (error) {
        console.error("Error fetching questions:", error);
      }
    };
    fetchQuestions();
  }, []);

  // Handle password answer
  const handlePwdAnswerChange = (e) => {
    setPwdAnswer(e.target.value);
    setPwdAnswerMessage("");
  };

  const validatePwdAnswer = () => {
    if (!pwdAnswer.trim()) {
      setPwdAnswerMessage("비밀번호 질문 답변을 입력해주세요!");
      return false;
    }
    return true;
  };

  const handlePwdKeyDown = (e) => {
    if (e.key === "Enter") {
      handleSignup();
    }
  };

  // 아이디 유효성 검사
  const handleMemberIdChange = (e) => {
    const value = e.target.value;

    setMemberId(value);
    setMemberIdCheck(false);

    if (value.length < 4 || value.length > 16 || !/^[a-zA-Z0-9]+$/.test(value)) {
      setIdValidMessage("아이디는 4~16자 영어, 숫자로만 입력 가능합니다.");
    } else {
      setIdValidMessage("중복 확인을 해주세요.");
    }
  };

  const handleIdCheck = async () => {
    if (memberId.length < 4 || memberId.length > 16 || !/^[a-zA-Z0-9]+$/.test(memberId)) {
      setIdValidMessage("아이디는 4~16자 영어, 숫자로만 입력 가능합니다.");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/api/member/check-id?memberId=${memberId}`
      );
      if (response.status === 200) {
        setIdValidMessage("사용 가능한 아이디입니다.");
        setMemberIdCheck(true);
      } else {
        setIdValidMessage("이미 사용 중인 아이디입니다.");
        setMemberIdCheck(false);
      }
    } catch (error) {
      console.error("Error during ID check:", error);
      setIdValidMessage("이미 사용 중인 아이디입니다.");
      setMemberIdCheck(false);
    }
  };

  // 닉네임 유효성 검사
  const validateNickname = (value) => {
    return (
      value.length >= 4 &&
      value.length <= 16 &&
      /^[가-힣a-zA-Z0-9]+$/.test(value) &&
      !/\s/.test(value)
    );
  };

  const handleNicknameChange = (e) => {
    const value = e.target.value;

    setNickname(value);
    setNicknameCheck(false);

    if (validateNickname(value)) {
      setNicknameValidMessage("닉네임 중복확인을 해주세요.");
    } else {
      setNicknameValidMessage("닉네임은 4~16자, 한글/영어/숫자만 가능합니다.");
    }
  };

  const handleNicknameCheck = async () => {
    if (!validateNickname(nickname)) {
      setNicknameValidMessage("닉네임은 4~16자, 한글/영어/숫자만 가능합니다.");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/api/member/check-nickname?nickname=${nickname}`
      );
      if (response.status === 200) {
        setNicknameValidMessage("사용 가능한 닉네임입니다.");
        setNicknameCheck(true);
      } else {
        setNicknameValidMessage("이미 사용 중인 닉네임입니다.");
        setNicknameCheck(false);
      }
    } catch (error) {
      console.error("Error during nickname check:", error);
      setNicknameValidMessage("이미 사용 중인 닉네임입니다.");
      setNicknameCheck(false);
    }
  };

  // 회원가입 처리
  const handleSignup = async () => {
    if (
      memberIdCheck &&
      nicknameCheck &&
      pwd1 &&
      pwd1 === pwd2 &&
      curOption !== "선택하세요" &&
      validatePwdAnswer()
    ) {
      try {
        const questionPk = Object.keys(questionMap).find(
          (key) => questionMap[key] === curOption
        );
        const data = {
          memberId: memberId,
          memberPassword: pwd1,
          nickname: nickname,
          answer: pwdAnswer,
          questionPk: Number(questionPk),
        };
        const response = await axios.post("http://localhost:8080/api/member/signup", data);
        if (response.status === 200) {
          alert("회원가입 성공!");
          navigate("/Login");
        }
      } catch (error) {
        console.error("Error during signup:", error);
        alert("회원가입 실패. 다시 시도해주세요.");
      }
    } else {
      alert("모든 입력 조건을 만족해주세요.");
      console.log("memberIdCheck : ",)
    }
  };

  return (
    <div className="Join-body-wrapper">
      <div className="Join-wrapper">
        <div className="Join-Header">
          <Link to="/">
            <img src={Logo} alt="logo" />
          </Link>
        </div>
        <div className="Join-content">
          <div className="Join-id-wrapper">
            <div>아이디 입력</div>
            <div className="Join-id-content">
              <input
                type="text"
                value={memberId}
                onChange={handleMemberIdChange}
                onKeyDown={(e) => e.key === "Enter" && handleIdCheck()}
                onFocus={() => setIsIdFocused(true)}
                onBlur={() => setIsIdFocused(false)}
                placeholder="4자~16자 (영어,숫자만)"
              />
              <button onClick={handleIdCheck}>중복확인</button>
            </div>
            <div className="Join-warning">{idValidMessage}</div>
          </div>

          <div className="Join-pwd-wrapper">
            <PwdConfig
              pwd1={pwd1}
              setPwd1={setPwd1}
              pwd2={pwd2}
              setPwd2={setPwd2}
            />
          </div>

          <div className="Join-nickname-wrapper">
            <div>닉네임 입력</div>
            <div className="Join-nickname-content">
              <input
                type="text"
                placeholder="4자~16자 (한글,영어,숫자만)"
                value={nickname}
                onChange={handleNicknameChange}
                onKeyDown={(e) => e.key === "Enter" && handleNicknameCheck()}
                onFocus={() => setIsNicknameFocused(true)}
                onBlur={() => setIsNicknameFocused(false)}
              />
              <button onClick={handleNicknameCheck}>중복체크</button>
            </div>
            <div className="Join-warning">{nicknameValidMessage}</div>
          </div>

          <div className="Join-pwdQA-wrapper">
            <div className="Join-pwdQ">
              <div>비밀번호 질문</div>
              <div className="pwdQ-dropdown">
                <Dropdown
                  options={dropdownOptions}
                  onChange={(e) => setCurOption(e.value)}
                  value={curOption}
                  placeholder="비밀번호 질문을 선택하세요"
                />
              </div>
            </div>
            <div className="Join-pwdA">
              <div>비밀번호 질문 답변</div>
              <textarea
                className="Join-pwdA-input"
                placeholder="비밀번호 질문 답변"
                value={pwdAnswer}
                onChange={handlePwdAnswerChange}
                onKeyDown={handlePwdKeyDown}
              />
              <div className="Join-warning">{pwdAnswerMessage}</div>
            </div>
          </div>
        </div>
        <div className="Join-bottom">
          <button className="Join-complete-button" onClick={handleSignup}>
            회원 가입
          </button>
        </div>
      </div>
    </div>
  );
};

export default Join;

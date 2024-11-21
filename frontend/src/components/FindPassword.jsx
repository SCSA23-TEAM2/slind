import "./css/FindPassword.css";
import { useState, useEffect } from "react";
import {Link, useNavigate} from "react-router-dom"
import Logo from "../assets/RealLogoWithoutBackground.png";
import Dropdown from "react-dropdown";
import httpAxios from "../api/httpAxios"
import "react-dropdown/style.css";

const FindPassword = () => {
  const axios = httpAxios;
  const [userId, setUserId] = useState("");
  const [okUserId, setOkUserId] = useState(false);
  const [userIdWarning, setUserIdWarning] = useState("");
  const [curOption, setCurOption] = useState("선택하세요");
  const [dropdownOptions, setDropdownOptions] = useState([]);
  const [questionMap, setQuestionMap] = useState({});
  const [pwdAnswer, setPwdAnswer] = useState("");
  const [pwdAnswerMessage, setPwdAnswerMessage] = useState("");

  const navigate = useNavigate();

   // 비밀번호 질문 목록 요청
   useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await axios.get("/api/question");
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

  const handlePwdAnswerChange = (e) => {
    setPwdAnswer(e.target.value);
    setPwdAnswerMessage("");
  };


  const validateInputs = () => {
    let valid = true;

    if (!userId.trim()) {
      setUserIdWarning("아이디를 입력해주세요.");
      valid = false;
    }

    if (curOption === "선택하세요" || !curOption) {
      alert("비밀번호 확인 질문을 선택해주세요.");
      valid = false;
    }

    if (!pwdAnswer.trim()) {
      setPwdAnswerMessage("비밀번호 질문 답변을 입력해주세요!");
      alert("비밀번호 질문 답변을 입력해주세요.")
      valid = false;
    }

    return valid;
  };

  const gotoPasswordConfig = async () => {
    if (validateInputs()) {
      try{
        // questionPk 추출
        const questionPk = Object.keys(questionMap).find(
          (key) => questionMap[key] === curOption
        );

        // 요청 데이터
        const requestData = {
          memberId: userId,
          questionPk: Number(questionPk),
          answer: pwdAnswer,
        };

        // 비밀번호 찾기 요청
        const response = await axios.post("/api/member/forgot-password", requestData);

        if (response.status === 200) {
          // 성공 시 PasswordConfig로 이동하며 memberPk 전달
          const { memberPk } = response.data;
          navigate("/PasswordConfig", { state: { memberPk } });
        }
      } catch (error) {
        console.error("Error finding password:", error);
        alert("비밀번호 찾기에 실패했습니다. 다시 시도해주세요.");
      }
    }

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
                options={dropdownOptions}
                onChange={(e) => {
                  setCurOption(e.value);
                }}
                value={curOption}
                placeholder="비밀번호 질문을 선택하세요"
              />
            </div>
          </div>
          <div className="FindPassword-pwdA">
            <div>비밀번호 질문답변</div>
            <textarea
              type="text"
              className="FindPassword-pwdA-input"
              placeholder="비밀번호 질문답변"
              value={pwdAnswer}
              onChange={handlePwdAnswerChange}
            ></textarea>
              <div className="FindPassword-id-warning">{pwdAnswerMessage}</div>
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

import { useState, useEffect, } from "react";
import "./css/MyPage.css";
import Dropdown from "react-dropdown";
import "react-dropdown/style.css";
import useAxios from "../useAxios";

const MyPage = () => {
  const [memberId, setMemberId] = useState("");
  const [nickname, setNickname] = useState("");
  const [nicknameMessage, setNicknameMessage] = useState("");
  const [nicknameCheck, setNicknameCheck] = useState(false);
  const [nicknameChanged, setNicknameChanged] = useState(false);

  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [answerMessage, setAnswerMessage] = useState("");

  const [questions, setQuestions] = useState([]);
  const [curOptionPk, setCurOptionPk] = useState(null);
  const [curOptionText, setCurOptionText] = useState("");
  const axios = useAxios();

  //초기 개인정보 불러오기

  // 초기 개인정보 불러오기
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/member/auth/mypage")
      .then((response) => {
        const { memberId, nickname, question, answer } = response.data;
        setMemberId(memberId);
        setNickname(nickname);
        setAnswer(answer);

        // 초기 질문 데이터를 설정
        axios
          .get("http://localhost:8080/api/question")
          .then((questionResponse) => {
            const questionList = questionResponse.data;
            setQuestions(questionList);
            const matchedQuestion = questionList.find(
              (q) => q.questionText === question
            );
            if (matchedQuestion) {
              setCurOptionPk(matchedQuestion.questionPk);
              setCurOptionText(matchedQuestion.questionText);
            }
          })
          .catch((error) => {
            console.error("Error fetching questions:", error);
          });
      })
      .catch((error) => {
        console.error("Error fetching member data:", error);
      });
  }, []);

    // 닉네임 변경 시 유효성 검사 및 메시지 업데이트
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
    setNicknameChanged(true);

    if (validateNickname(value)) {
      setNicknameMessage("닉네임 중복확인을 해주세요.");
    } else {
      setNicknameMessage("닉네임은 4~16자, 한글/영어/숫자만 가능합니다.");
    }
  };
    const handleNicknameCheck = async () => {
      if (!validateNickname(nickname)) {
        setNicknameMessage("닉네임은 4~16자, 한글/영어/숫자만 가능합니다.");
        return;
      }
  
      try {
        const response = await axios.get(
          `http://localhost:8080/api/member/check-nickname?nickname=${nickname}`
        );
        if (response.status === 200) {
          setNicknameMessage("사용 가능한 닉네임입니다.");
          setNicknameCheck(true);
        } else {
          setNicknameMessage("이미 사용 중인 닉네임입니다.");
          setNicknameCheck(false);
        }
      } catch (error) {
        console.error("Error during nickname check:", error);
        setNicknameMessage("이미 사용 중인 닉네임입니다.");
        setNicknameCheck(false);
      }
    };

    const handleAnswerChange = (e) => {
      setAnswer(e.target.value);
      if (e.target.value.trim() === "") {
        setAnswerMessage("비밀번호 질문 답변을 작성해주세요.");
      } else {
        setAnswerMessage("");
      }
    };

  const handleSave = () => {
    if (!nicknameChanged) {
      alert("닉네임을 수정해주세요.");
      return;
    }
    if (!validateNickname(nickname)) {
      alert("닉네임은 4~16자, 한글/영어/숫자만 가능합니다.");
      return;
    }
    if (!nicknameCheck) {
      alert("닉네임 중복 확인을 완료해주세요.");
      return;
    }
    if (!curOptionPk) {
      alert("비밀번호 질문을 선택해주세요.");
      return;
    }
    if (answer.trim() === "") {
      alert("비밀번호 질문 답변을 작성해주세요.");
      return;
    }

    const payload = {
      nickname,
      questionPk: curOptionPk, // Pk로 질문을 전송
      answer,
    };
    axios
      .put("http://localhost:8080/api/member/auth/mypage", payload)
      .then(() => {
        alert("내 정보 변경이 완료되었습니다.");
      })
      .catch((error) => {
        console.error("Error saving data:", error);
      });
  };

  return (
    <div className="MyPage-wrapper">
      <div className="MyPage-header">
        <h1>마이페이지</h1>
      </div>
      <div className="MyPage-content">
        <div className="MyPage-item-wrapper id">
          <div className="item-header">아이디</div>
          <div className="item-content">{memberId}</div>
        </div>
        <div className="MyPage-item-wrapper nickname">
          <div className="item-header">닉네임</div>
          <div className="item-content">
            <div className="input-check">
              <input
                className="nickname-input"
                type="text"
                value={nickname}
                onChange={handleNicknameChange}
              />
              {/* <div className="nickname-check-icon">asd</div> */}
              <div className="nickname-check-button">
                <button onClick={handleNicknameCheck}>중복체크</button>
              </div>
            </div>
            <div className="nickname-check-warning">
              {nicknameMessage}
            </div>
          </div>
        </div>
        <div className="MyPage-item-wrapper pwdQ">
          <div className="item-header">비밀번호 질문</div>
          <div className="item-content">
            <Dropdown
              options={questions.map((q) => q.questionText)}
              onChange={(e) => {
                const selectedQuestion = questions.find(
                  (q) => q.questionText === e.value
                );
                setCurOptionPk(selectedQuestion.questionPk);
                setCurOptionText(selectedQuestion.questionText);
              }}
              value={curOptionText}
              placeholder="비밀번호 질문을 선택하세요"
            />
          </div>
        </div>
        <div className="MyPage-item-wrapper pwdA">
          <div className="item-header">비밀번호 질문 답변</div>
          <div className="item-content">
            <input
              className="nickname-input"
              type="text"
              value={answer}
              onChange={handleAnswerChange}
            />
            <div className="nickname-check-warning">{answerMessage}</div>
          </div>
        </div>
      </div>
      <div className="MyPage-bottom">
        <button className="withdraw-button">회원탈퇴</button>
        <button className="save-button"  onClick={handleSave}>저장</button>
      </div>
    </div>
  );
};

export default MyPage;

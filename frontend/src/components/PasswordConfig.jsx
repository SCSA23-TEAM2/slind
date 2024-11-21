import Logo from "../assets/RealLogoWithoutBackground.png";
import "./css/PasswordConfig.css";
import PwdConfig from "./PwdConfig";
import httpAxios from "../api/httpAxios";
import {Link, useLocation, useNavigate } from "react-router-dom"


const PasswordConfig = () => {
  const axios = httpAxios;
  const location = useLocation();
  const navigate = useNavigate();
  const {memberPk} = location.state || null;
  const [pwd1, setPwd1] = useState("");
  const [pwd2, setPwd2] = useState("");

   // 비밀번호 변경 요청
   const handlePasswordChange = async () => {
    if (!pwd1 || !pwd2) {
      alert("비밀번호와 비밀번호 확인을 모두 입력해주세요.");
      return;
    }

    if (pwd1 !== pwd2) {
      alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
      return;
    }

    try {
      const requestBody = {
        memberPk: memberPk,
        newPassword: pwd1,
      };

      const response = await axios.put("/api/member/password", requestBody);

      if (response.status === 200) {
        alert("비밀번호가 성공적으로 변경되었습니다.");
        navigate("/Login");
      }
    } catch (error) {
      console.error("Error updating password:", error);
      alert("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
    }
  };


  return (
    <div className="PasswordConfig-body-wrapper">
      <div className="PasswordConfig-wrapper">
        <div className="PasswordConfig-header">
          <Link to="/">
            <img src={Logo} alt="logo" />
          </Link>
        </div>
        <div className="PasswordConfig-content">
          <PwdConfig pwd1={pwd1} setPwd1={setPwd1} pwd2={pwd2} setPwd2={setPwd2} />
        </div>
        <div className="PasswordConfig-bottom">
          <button className="PasswordConfig-reconfig-button">재설정</button>
        </div>
      </div>
    </div>
  );
};

export default PasswordConfig;

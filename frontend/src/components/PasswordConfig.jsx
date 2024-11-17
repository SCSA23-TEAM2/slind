import Logo from "../assets/RealLogoWithoutBackground.png";
import "./css/PasswordConfig.css";
import PwdConfig from "./PwdConfig";
import {Link, useLocation} from "react-router-dom"


const PasswordConfig = () => {

  const userId = useLocation();
  console.log(userId.state.userid);
  return (
    <div className="PasswordConfig-body-wrapper">
      <div className="PasswordConfig-wrapper">
        <div className="PasswordConfig-header">
          <Link to="/">
            <img src={Logo} alt="logo" />
          </Link>
        </div>
        <div className="PasswordConfig-content">
          <PwdConfig />
        </div>
        <div className="PasswordConfig-bottom">
          <button className="PasswordConfig-reconfig-button">재설정</button>
        </div>
      </div>
    </div>
  );
};

export default PasswordConfig;

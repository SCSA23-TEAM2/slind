import Logo from "../assets/RealLogoWithoutBackground.png";
import "./css/PasswordConfig.css";
import PwdConfig from "./PwdConfig";

const PasswordConfig = () => {
  return (
    <div className="PasswordConfig-body-wrapper">
      <div className="PasswordConfig-wrapper">
        <div className="PasswordConfig-header">
          <a href="">
            <img src={Logo} alt="logo" />
          </a>
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

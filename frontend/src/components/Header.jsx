import "./css/Header.css";
import Logo from "../assets/RealLogoWithoutBackground.png";

const Header = () => {
  return (
    <div className="header-wrapper">
      <div className="header-content">
        <div className="logo">
          {/* 링크로 이동가능하도록 해야함 */}
          <a href="">
            <img src={Logo} alt="logo" />
          </a>
        </div>
      </div>
      <div className="header-nav-wrapper">
        <ul className="header-nav-contents">
          <li className="header-nav-list">
            <a href="">로그인</a>
          </li>
          <li className="header-nav-list">
            <a href="">마이페이지</a>
          </li>
          {/* <li className="header-nav-list">
            <a href="">로그아웃</a>
          </li> */}
        </ul>
      </div>
    </div>
  );
};

export default Header;

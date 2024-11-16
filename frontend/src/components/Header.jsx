import "./css/Header.css";
import Logo from "../assets/RealLogoWithoutBackground.png";
import {useState} from "react";
import {Link} from "react-router-dom";
const Header = () => {
  
  const [isLogined, setIsLogined] = useState(true);
  // console.log("이동")
  return (
    <div className="header-wrapper">
      <div className="header-content">
        <div className="logo">
          {/* 링크로 이동가능하도록 해야함 */}
          <Link to="/">
            <img src={Logo} alt="logo" />
          </Link>
        </div>
      </div>
      <div className="header-nav-wrapper">
        <ul className="header-nav-contents">
          {isLogined ?  (<><li className={"header-nav-list"}>
            <a href="">로그아웃</a>
          </li>
          <li className="header-nav-list">
            <Link to="/MyPage" state= {{pageNum: 1}}>마이페이지</Link>
          </li></>) : (<li className="header-nav-list">
            <Link to="/Login">로그인</Link>
          </li>)}

        </ul>
      </div>
    </div>
  );
};

export default Header;

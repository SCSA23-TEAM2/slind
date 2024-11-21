import "./css/Header.css";
import Logo from "../assets/RealLogoWithoutBackground.png";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../AuthContext";
const Header = () => {
  const navigate = useNavigate();
  const { isAuthenticated, logout } = useAuth();

  const handleLogout = async () => {
    // setIsLogined(false);
    await logout(); // Remove tokens from context and localStorage
    // Redirect to login or home page
    navigate("/Login");
  };

  // const [isLogined, setIsLogined] = useState(true);
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
          {isAuthenticated ? (
            <>
              <li className={"header-nav-list"}>
                <Link onClick={handleLogout}>로그아웃</Link>
              </li>
              <li className="header-nav-list">
                <Link to="/MyPage" state={{ pageNum: 1 }}>
                  마이페이지
                </Link>
              </li>
            </>
          ) : (
            <li className="header-nav-list">
              <Link to="/Login">로그인</Link>
            </li>
          )}
          <li className="header-nav-list"></li>
        </ul>
      </div>
    </div>
  );
};

export default Header;

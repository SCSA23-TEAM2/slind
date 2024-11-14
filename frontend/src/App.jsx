import Header from "./components/Header";
import Login from "./components/Login";
import Nav from "./components/Nav";
import GeneralBoard from "./components/GeneralBoard";
import MainLatestBoard from "./components/MainLatestBoard";
import MainCourtBoard from "./components/MainCourtBoard";
import AsideRealTimeBoard from "./components/AsideRealTimeBoard";
import MyPageNav from "./components/MyPageNav";
import MyPage from "./components/MyPage";

import "./App.css";

function App() {
  return (
    <div className="Body">
      {/*고정 컴포넌트  */}
      <Header />
      {/* <Nav /> */}

      {/* 로그인 페이지 */}
      {/* <Login /> */}

      {/* 게시판 페이지 */}
      {/* <GeneralBoard /> */}

      {/* 메인 페이지 */}
      {/* <MainCourtBoard />
      <MainLatestBoard />
      <AsideRealTimeBoard /> */}

      {/* 마이페이지 */}
      <MyPageNav />
      {/* <MyPage /> */}
    </div>
  );
}

export default App;

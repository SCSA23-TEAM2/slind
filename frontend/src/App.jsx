import Header from "./components/Header";
import Login from "./components/Login";
import Nav from "./components/Nav";
import GeneralBoard from "./components/GeneralBoard";
import MainLatestBoard from "./components/MainLatestBoard";
import MainCourtBoard from "./components/MainCourtBoard";
import AsideRealTimeBoard from "./components/AsideRealTimeBoard";
import MyPageNav from "./components/MyPageNav";
import MyPage from "./components/MyPage";
import EditBookmark from "./components/EditBookmark";
import MyContent from "./components/MyContent";
import PostForm from "./components/PostForm";

import "./App.css";

function App() {
  return (
    <div className="Body">
      {/*고정 컴포넌트 */}
      <Header /> {/*로그인, 회원가입, 비밀번호찾기 제외*/}
      <Nav /> {/*로그인, 회원가입, 비밀번호찾기, 마이페이지 제외*/}
      {/* ----- 로그인 페이지 */}
      {/* <Login /> */}
      {/* ----- 게시판 페이지 */}
      {/* <GeneralBoard /> */}
      {/* ----- 메인 페이지 */}
      {/* <MainCourtBoard />
      <MainLatestBoard />
      <AsideRealTimeBoard /> */}
      {/* ----- 마이페이지 */}
      {/* <MyPageNav /> */}
      {/* <MyPage /> */}
      {/* <EditBookmark /> */}
      {/* <MyContent /> */}
      {/* ----- 게시글 작성 페이지 */}
      <PostForm />
    </div>
  );
}

export default App;

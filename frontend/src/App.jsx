// import Header from "./components/Header";
// import Nav from "./components/Nav";
import Login from "./components/Login";
import Join from "./components/Join";
import FindPassword from "./components/FindPassword";
import PasswordConfig from "./components/PasswordConfig";
// import GeneralBoard from "./components/GeneralBoard";
// import MainLatestBoard from "./components/MainLatestBoard";
// import MainCourtBoard from "./components/MainCourtBoard";
// import AsideRealTimeBoard from "./components/AsideRealTimeBoard";
// import MyPageNav from "./components/MyPageNav";
// import MyPage from "./components/MyPage";
// import EditBookmark from "./components/EditBookmark";
// import MyContent from "./components/MyContent";
// import PostForm from "./components/PostForm";
// import PostDetail from "./components/PostDetail";
// import InfiniteScrollTest from "./components/InfiniteScrollTest";

import HomePage from "./components/Pages/HomePage";
import BoardPage from "./components/Pages/BoardPage";
import MyPagePage from "./components/Pages/MyPagePage";
import PostPage from "./components/Pages/PostPage";
import WritePage from "./components/Pages/writePage";
import NotFoundPage from "./components/Pages/NotFoundPage"

import { useState } from "react";
import {Routes, Route, Link, useNavigate} from "react-router-dom";
import "./App.css";

function App() {

  return (
    <div className="Body">
      <Routes>
        <Route path="/" element={<HomePage/>}/>
        <Route path="/board/:Name" element={<BoardPage/>}/>
        <Route path="/board/:Name/Post/:id" element={<PostPage/>}/>
        <Route path="/board/:Name/write" element={<WritePage/>}/>
        <Route path="/MyPage" element={<MyPagePage/>}>
          <Route path="Bookmark" element={<MyPagePage/>}/>
          <Route path="MyContent/MyBoard" element={<MyPagePage/>}/>
          <Route path="MyContent/MyPost" element={<MyPagePage/>}/>
          <Route path="MyContent/MyComment" element={<MyPagePage/>}/>
          <Route path="MyContent/MySuit" element={<MyPagePage/>}/>
        </Route>
        <Route path="*" element={<NotFoundPage/>}/>
        <Route path="/Login" element={<Login/>}/>
        <Route path="/Join" element={<Join/>}/>
        <Route path="/FindPassword" element={<FindPassword/>}/>
        <Route path="/PasswordConfig" element={<PasswordConfig/>}/>
      </Routes>
      {/*고정 컴포넌트 */}
      {/*로그인, 회원가입, 비밀번호찾기 제외*/}
      {/* <Header /> */}
      {/*로그인, 회원가입, 비밀번호찾기, 마이페이지 제외*/}
      {/* <Nav /> */}
      {/* ----- 로그인 페이지 */}
      {/* <Login /> */}
      {/* <Join /> */}
      {/* <FindPassword /> */}
      {/* <PasswordConfig /> */}
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
      {/* <PostForm /> */}

      {/* 게시글 상세 페이지 */}
      {/* <PostDetail /> */}
      {/* 테스트용 */}
      {/* <InfiniteScrollTest /> */}
    </div>
  );
}
export default App;

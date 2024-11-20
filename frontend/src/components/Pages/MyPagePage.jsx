import Header from "../Header";
import MyPageNav from "../MyPageNav";
import MyPage from "../MyPage";
import MyContent from "../MyContent"
// import EditBookmark from "../EditBookmark"
import {useLocation} from "react-router-dom"
import NotFoundPage from "./NotFoundPage";
// import EditBookmark from "./components/EditBookmark";
// import MyContent from "./components/MyContent";


const MyPagePage = () => {
    const MyPageLocation = useLocation();
    // console.log(MyPageLocation);
    const pageNum = MyPageLocation.state.pageNum;

    const selectPage = () => {
        switch (pageNum) {
            case (1): { //마이페이지
                return <MyPage/>
            }
            // case (2): { //즐겨찾기 편집
            //     return <EditBookmark/>
            // }
            case 3: // 내가 만든 게시판
            case 4: // 내가 쓴 글
            case 5: // 내가 쓴 댓글
            case (6): //내가 소송한 재판
                return <MyContent key={pageNum} pageNum={pageNum} />;
            
            default: { 
                return <NotFoundPage/>
            }
        }
    } 
    
    return <>
        <Header/>
        <MyPageNav/>
        {selectPage()}
    </>
}
export default MyPagePage
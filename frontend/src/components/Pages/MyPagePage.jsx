import Header from "../Header";
import MyPageNav from "../MyPageNav";
import MyPage from "../MyPage";
import MyContent from "../MyContent"
import EditBookmark from "../EditBookmark"
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
            case (1): {
                return <MyPage/>
            }
            case (2): {
                return <EditBookmark/>
            }
            case (3): {
                return <MyContent/>
            }
            case (4): {
                return <MyContent/>
            }
            case (5): {
                return <MyContent/>
            }
            case (6): {
                return <MyContent/>
            }
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
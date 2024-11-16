import Header from "../Header";
import Nav from "../Nav";
import MainLatestBoard from "../MainLatestBoard";
import MainCourtBoard from "../MainCourtBoard";
import AsideRealTimeBoard from "../AsideRealTimeBoard";

const HomePage = () => {
    return (
        <>
        <Header/>
        <Nav/>
        <MainCourtBoard/>
        <MainLatestBoard/>
        <AsideRealTimeBoard/>
        </>
    )
}
export default HomePage
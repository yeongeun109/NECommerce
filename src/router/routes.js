import MyPage from "../pages/MyPage";
import Enter from "../pages/Enter";
import MintingNFT from "../pages/MintingNFT";
import Main from "../pages/Main";


const routes = [
  
  {
    path: "/",
    component: Enter,
    mode: "public",
  },

  {
    path: "/mypage",
    component: MyPage,
    mode: "private",
  },

  {
    path: "/main",
    component: Main,
    mode: "private",
  },
  
  {
    path: "/minting",
    component: MintingNFT,
    mode: "private",
  },

];

export default routes;

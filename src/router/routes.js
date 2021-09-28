import MyPage from "../pages/MyPage";
import Main from "../pages/Main";


const routes = [
  
  {
    path: "/",
    component: Main,
    mode: "public",
  },

  {
    path: "/mypage",
    component: MyPage,
    mode: "private",
  },

];

export default routes;

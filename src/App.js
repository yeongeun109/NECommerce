import './App.css';
import GetAccount from './components/GetAccount';
import ChargeEther from './components/ChargeEther';
import CreateWallet from './components/CreateWallet';
import UploadImageToS3 from './components/Uploadfiles';
import { Container } from "react-bootstrap";
import { HashRouter as Router, Switch } from "react-router-dom";
import Layout from './layouts/Layout';
import PrivateRouter from "./router/PrivateRouter";
import PublicRouter from "./router/PublicRouter";
import Main from './pages/Main';
import MyPage from './pages/MyPage';

function App() {

  return (
    <Router>
      <Switch>
        <Layout>
          <Container style={{ minHeight: "75vh", position: "relative" }}>
          
            <PublicRouter component={Main} path="/" exact />
            <PrivateRouter component={MyPage} path="/mypage" exact />
          </Container>
        </Layout>
      </Switch>
    </Router>
    // <div>
    //   <h1>계정정보 가져오기</h1>
    //   <GetAccount/>
    //   <h1>이더를 충전하기</h1>
    //   <ChargeEther/>
    //   <h1>지갑을 생성해보자</h1>
    //   <CreateWallet/>
    //   <h1>그림올리기</h1>
    //   <UploadImageToS3/>
    // </div>
  );
}

export default App;

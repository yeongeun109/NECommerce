import './App.css';
import GetAccount from './component/GetAccount';
import ChargeEther from './component/ChargeEther';
import CreateWallet from './component/CreateWallet';
import UploadImageToS3 from './component/Uploadfiles';
function App() {

  return (
    <div>
      <h1>계정정보 가져오기</h1>
      <GetAccount/>
      <h1>이더를 충전하기</h1>
      <ChargeEther/>
      <h1>지갑을 생성해보자</h1>
      <CreateWallet/>
      <h1>그림올리기</h1>
      <UploadImageToS3/>
    </div>
  );
}

export default App;

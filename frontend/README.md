# Frontend
## component
### 지갑관련 기능
- Wallet.js : 어디와 연결되어있는지 주소 기입한 파일
- GetAccount.js : 기입한 address의 지갑정보를 가져오는 것.
- CreateWallet.js : 패스워드 기입 후 account 생성. 생성된 address 표기
- ChargeEther.js : 충전할 이더를 기입하면 coinbase 계정에서 tx를 생성하여 보냄. -> 현재는 채굴이활성화 되어있어야 sendtransaction가능
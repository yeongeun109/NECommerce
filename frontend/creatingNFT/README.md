# Creating NFT

## 순서

1. 터미널 오픈
2. npm i (root directory)
3. 루트 디렉토리에 .env파일 생성
4. .env파일에 API_URL과 PRIVATE_KEY기입
    - PRIVATE_KEY="myprivatekey"
    - 메타마스크에서 추출 가능
5. 터미널에 npx hardhat run scripts/deploy.js --network ropsten 하면 생성될 경우 터미널에 생성된 스마트컨트랙트 주소가 출력.
    - 안되었을 경우 unlock여부 확인 or creation gas fee가 부족한지 확인
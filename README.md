<img src="https://user-images.githubusercontent.com/17819249/136695054-57e4aa56-6982-47a9-90da-cbe3d811068c.png" alt="로고" width="200"/>

# NECommerce
### 당신의 디지털 자산을 안전하게 관리하고 손쉽게 거래하도록 도와줄게요! 😎

<br/>

## 0. 목차
[1. NECommerce 소개](#1-NECommerce-소개)<br/>
[2. 핵심기능](#2-핵심기능)<br/>
[3. 기술 스택 및 아키텍처](#3-기술-스택-및-아키텍처)<br/>

<br/>

## 1. NECommerce 소개

### 🖌 개요

#### `NFT E-Commerce`

> 디지털 이미지를 **NFT로 발급**해 디지털 자산의 **소유권**을 증명했으며 추가적으로 ERC-20 기반의 token을 통해 사용자 간의 NFT **거래**를 가능하게 했습니다.

> 🦊 **MetaMask** 지갑을 활용하여 사용자의 UI/UX 편의성과 사용성을 높였습니다.


- 개발 기간 : 2021.08.30 ~ 2021.10.08
- 주제 : NFT 발급과 자체 토큰인 NE를 화폐로 NFT를 거래할 수 있는 웹 서비스

<br/>

### 👨‍👩‍👦‍👦 팀원소개

| Name | 최다윗 | 고노윤 | 윤영은 | 임영찬 |
| :----: | :----: | :----: | :----: | :----: |
| Role | 👑팀장<br/> ERC-20 작성 및 배포 | 📹UCC 담당자<br/> ERC-20 작성 및 배포 | 🎤발표자<br/> ERC-721 작성 및 배포 | 📝발표자료 작성<br/> ERC-721 작성 및 배포 |  
| Position | `Backend` <br/>`CI/CD` | `Backend`<br/>`Frontend` | `Backend` <br/> `Frontend`  | `Backend`<br/> `Frontend`|
| Git |  [@dawit95](https://github.com/dawit95) | [@nohyoonko](https://github.com/nohyoonko) | [@yeongeun109](https://github.com/yeongeun109) | [@lim8662](https://github.com/lim8662) |
 
<br/>

### ⛓ 블록체인
> ERC-20, ERC-721 모두 **Ropsten Testnet**에 배포해서 사용했습니다.

- **ERC-20** 기반의 **자체 코인(NE)** 사용
<img src="https://user-images.githubusercontent.com/62532878/136697466-5ea2be9d-3d5d-44c5-8520-eee36f9fbe7e.JPG" alt="ERC-20" width="800"/>

- **ERC-721** 기반의 **NFT 발행 및 거래**
<img src="https://user-images.githubusercontent.com/62532878/136697526-9f51f91c-649d-4faa-a880-447727d2b16f.JPG" alt="ERC-721" width="800"/>

<br />

### ✨ 차별점

#### 메인넷 환경과 가장 비슷한 Ropsten 테스트넷을 사용해 스마트 컨트랙트 개발환경의 최적화를 진행했습니다.

#### 테스트넷에 ERC-20, ERC-721 스마트 컨트랙트를 배포하여 사용했습니다.

- ERC-20 스마트 컨트랙트는 10만개의 NE 토큰을 생성해 사용자의 거래 코인으로 사용되게 구현했습니다.
- ERC-721 스마트 컨트랙트는 특정 이미지 url에 대하여 소유권을 증명하는 NFT를 생성하고 소유자가 사이트를 통해 안전하게 거래할 수 있도록 서비스를 제공합니다.


<br/>

## 2. 핵심기능

### 1️⃣ 상품 목록 조회 - 메인 페이지
> 메인 페이지에서는 판매 등록된 모든 NFT 상품의 목록 조회가 가능합니다.
> 
> `Art`, `Photo` 두 개의 카테고리 별 분류와, 제목/소유자 기반 검색어로 필터 기능을 제공합니다.

<img src="https://user-images.githubusercontent.com/17819249/136694800-7dd0c175-7196-4c63-b78c-fd8a1be77346.gif" alt="메인페이지" width="1000"/>


<br/>

### 2️⃣ NFT 발급
> NFT 상품명과 이미지 URI, 상품 설명, 카테고리를 입력 받아 NFT를 발급합니다.

<img src="https://user-images.githubusercontent.com/17819249/136694827-402e12cc-f69a-4553-b4e3-a316d546ad8f.gif" alt="NFT_발급" width="1000"/>

### 3️⃣ 소유한 NFT 조회 - 마이 페이지
> 마이페이지로 이동하려면 먼저 메타마스크의 지갑과 연결을 해야합니다.
> 
> 연결이 완료되면 Wallet 탭에서 지갑의 계정 주소와 이더잔액, 그리고 NE 토큰의 잔액이 보이고 충전 버튼을 통해 5NE씩 충전할 수 있습니다.
>
> My NFT 탭에는 사용자가 소유한 NFT들을 조회할 수 있습니다.

<img src="https://user-images.githubusercontent.com/17819249/136694824-3b126cf1-eb85-4f3a-968e-1527b52b7f13.gif" alt="마이페이지" width="1000"/>

### 4️⃣ NFT 거래 기능

- 판매
> 사용자가 소유한 NFT 상세 페이지에서 NE 토큰 가격을 설정해 판매 등록을 할 수 있습니다.
> 
> 판매 등록이 완료되면 메인 페이지의 거래 목록에 올라가게 됩니다.

<img src="https://user-images.githubusercontent.com/17819249/136694822-e7b721f3-8ce1-4782-882b-8c300dca1015.gif" alt="판매_등록" width="1000"/>

- 구매
> 구매 버튼을 누르면 메타마스크가 호출되며 사용자가 서명하면 해당 NE 가격만큼 송금됩니다.
> 
> 또한 블록체인 상의 NFT의 소유자가 바뀌고 마이페이지의 My NFT 탭에서 구매한 NFT를 확인할 수 있습니다.

<img src="https://user-images.githubusercontent.com/17819249/136695049-ac39ea55-60ad-4ca6-929d-43f5e82ba452.gif" alt="구매" width="1000"/>

<br />
<br />

## 3. 기술 스택 및 아키텍처
### 🛠 기술 스택

### Frontend : <img src="https://img.shields.io/badge/React.js-17.0.2-61DAFB?style=flat-square&logo=React&logoColor="/> <img src="https://img.shields.io/badge/HTML-269539?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black"/> <img src="https://img.shields.io/badge/Bootstrap-339AF0?style=flat-square&logo=Bootstrap&logoColor=white"/> <img src="https://img.shields.io/badge/Web3.js-EF6830?style=flat-square&logo=Web3.js&logoColor=white"/> 

### Backend : <img src="https://img.shields.io/badge/Java-11-007396?style=flat-square&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/Spring-2.5.4-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> <img src="https://img.shields.io/badge/MariaDB-10.7.0-4479A1?style=flat-square&logo=MariaDB&logoColor=white"/> <img src="https://img.shields.io/badge/JPA-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white"/>

### Tool :  <img src="https://img.shields.io/badge/Ethereum-grey?style=flat-square&logo=Ethereum&logoColor=white"/> <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/> <img src="https://img.shields.io/badge/GitLab-FCA121?style=flat-square&logo=GitLab&logoColor=black"/> <img src="https://img.shields.io/badge/Jenkins-red?style=flat-square&logo=Jenkins&logoColor=black"/> <img src="https://img.shields.io/badge/NGINX-269539?style=flat-square&logo=NGINX&logoColor=black"/> <img src="https://img.shields.io/badge/Jira-0052CC?style=flat-square&logo=Jira&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/> <img src="https://img.shields.io/badge/Notion-black?style=flat-square&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Metamask-e2761b?style=flat-square&logo=Metamask&logoColor=white"/>



<br/>

### ⚙️ 아키텍처
<img src="https://user-images.githubusercontent.com/17819249/136696142-caf6b8b1-dabb-4090-8f8f-aab7c1d7b6ee.png" alt="아키텍처" width="800"/>



<br/> <br/>
### fin.

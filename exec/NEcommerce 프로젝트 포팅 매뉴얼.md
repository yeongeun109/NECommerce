# NEcommerce 프로젝트 포팅 매뉴얼

## 1. gitlab 소스 크론 이후 빌드 및 배포할수 있는 작업 문서

### FrontEnd

- 모든 빌드 버전은 package.json에 있습니다.<br>
  `"@fortawesome/fontawesome-svg-core": "^1.2.36", "@fortawesome/free-solid-svg-icons": "^5.15.4", "@fortawesome/react-fontawesome": "^0.1.15", "@material-ui/core": "^4.12.3", "@metamask/detect-provider": "^1.2.0", "@metamask/onboarding": "^1.0.1", "@openzeppelin/contracts": "^3.4.2", "@testing-library/jest-dom": "^5.11.4", "@testing-library/react": "^11.1.0", "@testing-library/user-event": "^12.1.10", "@uiw/react-md-editor": "^3.6.4", "axios": "^0.21.4", "bignumber.js": "^9.0.1", "dotenv": "^10.0.0", "eth-provider": "^0.9.4", "http-proxy-middleware": "^2.0.1", "jsonwebtoken": "^8.5.1", "jwt-decode": "^3.1.2", "metamask-react": "^2.0.2", "react": "^17.0.2", "react-bootstrap": "^1.6.1", "react-dom": "^17.0.2", "react-hook-form": "^7.15.0", "react-redux": "^7.2.5", "react-router-dom": "^5.3.0", "react-s3": "^1.3.1", "react-scripts": "4.0.3", "redux-devtools-extension": "^2.13.9", "redux-persist": "^6.0.0", "semantic-ui-react": "^2.0.4", "web-vitals": "^1.0.1", "web3": "^1.6.0" `

### BackEnd

- 사용한 JVM : openjdk version jdk11.0.12 (Zulu azul-11.0.12)
- SpringBoot version '2.5.4' (IDE => intellij ultimate version 212.4746.92)- gradle 방식(build.gradle파일에 선언되어있음)

2. 빌드시 사용되는 환경변수
   프론트 엔드에서 빌드시 사용되는 환경변수

- frontend 시작시 필요한 환경변수는 .env파일에 저장되어 있습니다.
  - AWS 접근을 위한 주소와 S3 버켓접근 key
  - Metamask의 관리자 계정 정보와 privateKey
  - Ethereum testnet인 Ropsten네트워크의 접근할수 있는 노드 infra주소

```bash
    REACT_APP_S3_BUCKET = ""
    REACT_APP_REGION = ""
    REACT_APP_ACCESS_KEY = ""
    REACT_APP_SECRET_ACCESS_KEY = ""
    REACT_APP_EXAMPLE = ""
    REACT_APP_SERVER_BASE_URL = ""

    REACT_APP_API_URL = ""
    REACT_APP_PRIVATE_KEY = ""

    REACT_APP_PUBLIC_KEY = ""
    REACT_APP_MY_AMAZON_SERVER = ""
```

백엔드인 springboot파일은 모두 build.gradle파일에 있습니다.
아래는 디펜던시 목록입니다.

```java
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    implementation 'com.h2database:h2'
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'

    //Swagger 등록
    implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
    implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
    implementation group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.12.3'

    //blockchain
    implementation 'org.apache.httpcomponents:httpclient:4.5.5'
    implementation 'org.web3j:web3j-spring-boot-starter:1.6.0'
    implementation 'org.web3j:core:4.8.7'
    implementation group: 'org.hyperledger.fabric-sdk-java', name: 'fabric-sdk-java', version: '1.4.0'

    //jwt
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2',
            // Uncomment the next line if you want to use RSASSA-PSS (PS256, PS384, PS512) algorithms:
            //'org.bouncycastle:bcprov-jdk15on:1.60',
            'io.jsonwebtoken:jjwt-jackson:0.11.2' // or 'io.jsonwebtoken:jjwt-gson:0.11.2' for gson

    //okhttp
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation group: 'com.squareup.okhttp3', name: 'okhttp', version: '4.9.1'
}
```

- 백엔드 빌드에 필요한 환경변수는 src/main/resources/\*.properties 파일에 있습니다.
- application파일은 EC2위에 올린 mariaDB 주소와 접근계정 id,password가 있는 환경변수 파일입니다.

<br>
3) 배포 환경 설명

- ec2위에 nginx를 이용해 Reverse Proxy(forward proxy)를 만들고 ssl인증서를 통해 https통신을 구현했습니다.

- 백엔드 서버는 jenkins를 이용해 gitlab의 backend 브랜치에서 webhook을 트리거로 build되어 docker img로 만든후 dockerhub에 repository로 관리하며 갱신된 후에 EC2 서버위 docker에 이미지로 배포했습니다.
- frontend는 EC2서버위에 직접 code를 git을 통해 받고 build한 파일을 nginx를 통해 배포했습니다.

<br>
4) 데이터베이스 접속 정보

```
8083번 포트에 docker mariaDB이미지로 존재합니다.
접근 계정은
ID : a407
PW : a407maria
url = jdbc:mariadb://j5a407.p.ssafy.io:8083/blockchain?useUnicode=true&serverTimezone=KST&characterEncoding=utf8mb4&autoReconnect=true
```

프로퍼티 정의된 목록은 없습니다.

## 2. 프로젝트에서 사용하는 외부 서비스 정보문서

AWS S3 => (https://aws.amazon.com/ko/) wkjung0329@gmail.com으로 가입된 S3 버켓을 사용<br>
Metamask => .env에 관리자 계정 private key등록 (지갑 가입자 : 다윗팀장)<br>

## 3. 데이터베이스 덤프 파일 최신본

exec폴더 내에 `backup2021008.zip`파일로 별도 첨부합니다.

## 4. 시연 시나리오

exec폴더 내에 `시연 시나리오.pptx`파일로 별도 첨부합니다.

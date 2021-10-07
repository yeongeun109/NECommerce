
# 이더리움

## 1. 프라이빗 이더리움 네트워크 구축

### 1-1. 가상 머신 구성

- Windows VirtualBox 설치
    다운로드 링크 : https://www.virtualbox.org/wiki/Downloads
- Vagrant 설치
    ``` bash
    # 설치 및 버전 확인
    > vagrant version
    Installed Version: 2.x.x...
    #파일 이동을 위한 SCP 설정
    > vagrant plugin install vagrant-scp
    ...
    Installed the plugin 'vagrant-scp (0.x.x)'
    ```
    **vagrant**는  가상 머신 프로비저닝 도구이다. 아래는 기본 명령어
    ``` bash
    ## vagrant 명령어

    vagrant up <args>			// args에 해당하는 가상머신 구동
    vagrant half <args> 			// args에 해당하는 가상머신 정지
    vagrant destory <args> 		// args에 해당하는 가상머신 삭제
    vagrant ssh <args> 			// args에 해당하는 가상머신 접속
    vagrant ssh-config <args> 	// args에 해당하는 가상머신 ssh 설정 확인
    vagrant global-status 		// 가상머신 상태 정보 출력
    vagrant --help 				// vagrant 관련 명령어 정보 출력
    ```

- 이더리움 네트워크 구축용 VM 2대 생성.
    <details>
    <summary> VM생성에 사용되는 vagrantFile </summary>
    <div markdown="1">       

    ```bash

    VAGRANT_API_VERSION = "2"

    vms = {
      'eth0' => '10',
      'eth1' => '11'
    }

    Vagrant.configure(VAGRANT_API_VERSION) do |config|
       config.vm.box = "ubuntu/bionic64"
       vms.each do |key, value|
          config.vm.define "#{key}" do |node|
             node.vm.network "private_network", ip: "192.168.50.#{value}"
             if "#{key}" == "eth0"
                node.vm.network "forwarded_port", guest: 8545, host: 8545
             end
             node.vm.hostname = "#{key}"
             node.vm.provider "virtualbox" do |nodev|
             nodev.memory = 2048
             end
          end
       end
    end
    ```

    </div>
    </details>

    <br>

    > 이슈 : vagrant up으로 한 번에 실행하려고 했지만, timeout 에러가 발생. => vargrant up eth0 처럼 직접 하나씩 실행으로 해결.

### 1-2. 이더리움 노드 구성
- 이더리움 소프트웨어는 Geth 1.9(stable) 이상을 사용
    1. Go 설치
    ```bash
    sudo apt-get install -y build-essential golang
    ```

    2. Geth 설치
    ```bash
    sudo apt-get update
    sudo apt-get install software-properties-common
    sudo add-apt-repository -y ppa:ethereum/ethereum
    sudo apt-get install ethereum
    ```

- 가상 머신에서 Geth 동작하도록 구축
    - Genesis.json

    ```json
    {
    "config": {
        "chainId": "myChainNumber",
        "homesteadBlock": 0,
        "eip150Block": 0,
        "eip155Block": 0,
        "eip158Block": 0
    },
    "difficulty": "0x10",
    "coinbase": "0x0000000000000000000000000000000000000000",
    "gasLimit": "9999999",
    "alloc": {},
    "extraData": "",
    "nonce": "myNonceValue - 0x로시작하는 무작위 값",
    "mixhash": "0x0000000000000000000000000000000000000000000000000000000000000000",
    "parentHash": "0x0000000000000000000000000000000000000000000000000000000000000000",
    "timestamp": "0x00"
    }
    ```
    > 참고: genesis.json 파일 주의사항 : hash값은 64bit, eip155Block을 사용하기 위해선 eip150Block을 같이 설정함, gasLimit 값은 충분한 크기를 줘야함.<br>
    > 이슈: genesis block을 각각의 가상머신에서 생성하고 genesis block을 init 시켜줘야함. 이 때 genesis block은 두 노드에서 같은 값을 가져야 함. 
- eth0과 eth1 노드 구성
    ```bash
    geth --datadir /home/vagrant/dev/eth_localdata init /home/vagrant/dev/eth_localdata/CustomGenesis.json
    ```
    - eth0
    **eth0 노드의 경우 RPC API를 호출할 수 있도록 활성화**
        ```bash
        geth
        --networkid 921
        --maxpeers 2
        --datadir ~/dev/eth_localdata/
        --nodiscover
        --allow-insecure-unlock
        --port 30300
        --rpc
        --rpcport 8545
        --rpcaddr 0.0.0.0
        --rpccorsdomain "*"
        --rpcapi "admin,net,miner,eth,rpc,web3,txpool,debug,db,personal"
        console 2>> ~/dev/eth_localdata/geth.log
        ```
    - eth1
        ```bash
        geth
        --networkid 921
        --maxpeers 2
        --datadir ~/dev/eth_localdata/
        --port 303
        console 2>> ~/dev/eth_localdata/geth.log
        ```
        
    > 이슈 : 노드 생성하는 과정에서 오류가 나서 다시 하고 싶은 경우.
    > => geth --datadir /home/vagrant/dev/eth_localdata/ removedb

### 1-3. 이더리움 노드 간의 peer 연결
- 각 노드의 geth console에서 node 확인
    - geth console 접속
    - eth1 노드에서 admin.nodeInfo.enode 명령어를 통해서 enode주소 확인
    - eth0 노드에서 admin.addPeer("enode주소@ip주소:port번호")를 통해서 eth1를 peer로 추가
    - admin.peers 로 peer 확인
<br/><br/>
## 2. 이더리움 계정 생성

### 2-1. 계정 생성
- 사용자 계정은 노드마다 최소 1개 이상 생성
- 계정 생성에 따른 keystore 파일 확인
    ``` bash
    # 가상머신 작동
    vargant up
    # 가상머신 접속
    vargant ssh "virtual machine name"
    #geth console 접속.
    geth --networkId "chainId" --maxpeers "최대 peer연결 number" --datadir "데이터 디렉토리 주소" --port "포트주소"
    personal.newAccount("password")
    # 이후 geth console을 빠져 나온 후 account가 생성되어있는지 확인.
    geth account list --keystore "데이터디렉토리/keystore"
    ```
- rpc를 연결하는 노드의 경우 추가적인 rpc option을 설정해주어야 함.
    ```bash
    geth --networkId "chainId" --maxpeers "최대 peer연결 number" --datadir "데이터 디렉토리 주소" --port "포트주소" --rpc --rpcport "vagrant파일에 쓴 hostport number" --rpcaddr 0.0.0.0 --rpccorsdomain "*" --rpcapi "admin, net, miner, web3, eth, rpc, txpool, db, personal, debug"
    # rpccorsdomain은 rpc로 교류할 수 있는 도메인을 설정해주는 것.
    # rpcaddr 0.0.0.0을 작성할 경우 모든 ip에 대해 접속이 가능하게 함
    # rpcapi는 호출가능한 api 설정
    ```


### 2-2. 코인베이스(Coinbase) 설정
- 코인베이스 : 블록 생성에 따른 보상금 지급 계정
- 노드마다 생성한 계정 중 하나를 코인베이스로 설정
    ``` bash
    # 코인 베이스 계정 조회(최초 계정 생성시 account[0]으로 할당)
    eth.coinbase
    ```

### 2-3. 마이닝(Mining) 시작
- 모든 노드에서 마이닝 시작
    ``` bash
    # 마이닝 시작 start(thread 수)
    miner.start(1)
    ```
- 마이닝 진행 상태 확인
    ```bash
    # 마이닝 상태 확인 / boolean값으로 표기됨
    eth.mining
    # 마이닝 속도 확인
    eth.hashrate
    ```
- 일정량 마이닝 진행 후 중단
    ```bash
    # 마이닝 중지 
    miner.stop()
    ```
### 2-4. 마이닝(Mining 결과 확인)
- 계정별 잔액 확인
    ``` bash
    # 잔액 확인, fromWei(계정주소, 단위)
    web3.fromWei(eth.getBalance(eth.coinbase), "ether")
    ```
- 생성된 블록 수 조회
    ``` bash
    # 블록 수 확인
    eth.blockNumber
    ```
- 블록의 상세 정보 조회
    ``` bash
    # 0번째 블록 정보 조회
    # eth.getBlock()은 eth.getBlockByNumber, getBlockByHash를 합친 것이라고 보면 됨
    eth.getBlock(1219)
    ```

## 3. 이더리움 트랜잭션 생성
- 계정 상태 출력(Locked or Unlocked)
    ``` bash
    personal.listWallets[0].status
    ```
- 계정 Unlock
    ``` bash
    # 방법1
    web3.personal.unlockAccount(eth.coinbase)
    # 방법2
    web3.personal.unlockAccount(eth.coinbase, "패스워드")
    # 방법3
    personal.unlockAccount("주소")
    ```
    > 이슈 : http통신의 경우 보안 문제로 계정 unlock이 불가능 할 수 있다. 
    > => geth console 옵션추가 ``--allow-insecure-unlock``

### 3-1. 트랜잭션(Transaction) 생성
- 계정 간 이더(ETH) 전송 트랜잭션 생성
- 전송할 이더량은 임의로 지정
    ```bash
    # 트랜잭션 생성
    tx = { from: eth.coinbase, to: eth.accounts[1], value: web3.toWei(1, 'ether'), data: "보내고싶은 데이터의 hex값"}
    ```
- 트랜잭션 해시 값 확인 
    ``` bash
    tx_hash = eth.sendTransaction(tx)
    ```
- 트랜잭션 상태 조회
    ```
    eth.getTransactionRecipt(tx_hash)
    ```

### 3-2. 트랜잭션(Transaction) 결과 확인
- 마이닝 재시작(일정 시간 수행) : 트랜잭션 동기화
    ``` bash
    # 마이닝 시작 start(thread 수)
    miner.start(1)
    ```
- 트랜잭션 상태 조회
    ```
    eth.getTransactionRecipt(tx_hash)
    ```
- 마이닝에 따른 트랜잭션 처리가 완료되면 마이닝 중단
    ```
    miner.stop()
    ```
- 코인 받은 계정의 잔액 변동 확인
    ``` 
   web3.fromWei(eth.getBalance("계정주소"))
    ```
<br/><br/>
## 4. 스마트 컨트랙트 배포

### 4-1. eth0 노드 확인
- VirtualBox 또는 Vagrant에서 eth0 VM에 대한 포트포워딩 확인
    - Host 8545 -> Guest 8545

![포트포워딩 확인](https://i.imgur.com/greugEU.png)

- eth0의 keystore를 json 파일로 저장

### 4-2. Metamask 설정
- Chrome 확장 프로그램 검색
- Metamask 설치 및 실행
- Metamask의 Custom RPC 옵션 설정

- Metamask에서 계정 Import(eth0의 keystore json 파일 활용)
    1. cat 명령어로 json 확인 후 local(window)에서 json 파일 새로 생성
    2. 공유 폴더로 옮겨서 가져오기
        ```bash
        cp [원본 파일 위치] [공유 폴더 위치]
        ```
        - VM 내 원본 파일 위치
        /home/vagrant/dev/eth_localdata/keystore/파일이름
        - VM 내 공유 폴더 위치
        /vagrant
- Metamask에서 계정 및 잔액 정보 확인

### 4-3. 스마트 컨트랙트 배포(Remix)
- Remix 접속
    > private network를 http 프로토콜을 사용해 만들면 remix페이지의 https프로토콜과 충돌이 남 => remix페이지를 http로 접속한다.
- Deploy & Run Transactions으로 이동
- Environment를 로컬 이더리움 네트워크와 연동
- 기본 제공 예제 중 1개를 선택하여 코드 내용 확인
- Compile 및 Deploy 수행 후 결과 확인
    > solidity 파일의 버전과 geth버전의 충돌로 배포후 사용 불가능 => solidity파일 버전 다운 (ex: geth ver 1.10.8&&solidity파일 ver 4)가능

### 4-4. 블록 정보 조회
- 스켈레톤 프로젝트의 이더리움 네트워크 정보를 맞게 수정
- 스켈레톤 프로젝트 구동
- 각 블록 및 트랜잭션 정보 확인

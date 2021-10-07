import React, { useState } from "react";
import { useMetaMask } from "metamask-react";
import OnboardingButton from "../components/OnboardingMetamask";
import { Tabs, Tab } from "react-bootstrap";
import MyWallet from "../components/MyWallet";
import OwningNFT from "../components/OwningNFT";

const MyPage = () => {
    const [key, setKey] = useState("wallet")
    const {status, account } = useMetaMask();
    // if (status === "initializing") return <div>싱크로 진행중</div>
    // if (status === "unavailable") return <div>메타마스크 사용불가</div>
    // if (status === "notConnected") return <button onClick={connect}>메타마스크연결</button>
    // if (status === "connecting") return <div>커넥팅</div>
    // if (status === "connected") return <div>Connected account : {account}</div>

    return (
        <div>
            {(status !== "connected") ?
            (<div className="d-flex justify-content-center">
                <OnboardingButton/>    
            </div>)
                :
            (
            <Tabs activeKey={key} onSelect={(k) => setKey(k)} id="my-page">
                <Tab title="Wallet" eventKey="wallet">
                    <MyWallet account={account}/>
                </Tab>
                <Tab title="My NFT" eventKey="MyNFT">
                    <OwningNFT/>
                </Tab>
                {/* <div>Connected account : {account}</div> */}
            </Tabs>
            )
            }
        </div>
    )
}

export default MyPage
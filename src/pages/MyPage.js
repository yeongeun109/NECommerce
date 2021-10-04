import React from "react";
import { useMetaMask } from "metamask-react";
import OnboardingButton from "../components/OnboardingMetamask";
const MyPage = () => {
    const {status, connect, account } = useMetaMask();
    // if (status === "initializing") return <div>싱크로 진행중</div>
    // if (status === "unavailable") return <div>메타마스크 사용불가</div>
    // if (status === "notConnected") return <button onClick={connect}>메타마스크연결</button>
    // if (status === "connecting") return <div>커넥팅</div>
    // if (status === "connected") return <div>Connected account : {account}</div>
    return (
        <div>
            <OnboardingButton/>
            
            <div>Connected account : {account}</div>
        </div>
    )
}

export default MyPage
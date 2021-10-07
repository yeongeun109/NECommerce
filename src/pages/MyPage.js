import React, { useEffect, useState } from "react";
import { useMetaMask } from "metamask-react";
import OnboardingButton from "../components/OnboardingMetamask";
import { Tabs, Tab } from "react-bootstrap";
import MyWallet from "../components/MyWallet";
import OwningNFT from "../components/OwningNFT";

const MyPage = () => {
    const [key, setKey] = useState("wallet")
    const {status, connect, account } = useMetaMask();
                

    return (
        <div>
            {(status !== "connected") ? 
            (<div><OnboardingButton/></div>) 
            : 
            (
            <div>
    
                <Tabs activeKey={key} onSelect={(k) => setKey(k)} id="my-page">
                    <Tab title="Wallet" eventKey="wallet">
                        <MyWallet account={account}/>
                    </Tab>
                    <Tab title="My NFT" eventKey="MyNFT">
                        <OwningNFT/>
                    </Tab>
                </Tabs>
            </div>)}
        </div>
        );
};

export default MyPage
import React, {useEffect, useState} from "react";
import {useMetaMask} from "metamask-react";
import OnboardingButton from "../components/OnboardingMetamask";
import {Tabs, Tab} from "react-bootstrap";
import MyWallet from "../components/MyWallet";
import OwningNFT from "../components/OwningNFT";
import GetUserPK from "../assets/GetUserPK";
import axios from "axios";

const MyPage = () => {
    const [key, setKey] = useState("wallet")
    const {status, connect, account} = useMetaMask();
    const [userName, setUserName] = useState("")

    useEffect(() => {
        const fetchData = async () => {
            const userPK = GetUserPK()
            console.log("userPK : " + userPK)

            const result = await axios.get(
                `api/v1/users/${userPK}`
            );
            setUserName(result.data.success)
        };
        fetchData();
    }, []);

    const fontName = {
        fontSize: "30px",
        fontWeight: "bold"
    }
    const layoutCenter = {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      };
    
    return (
        <div className="mt-5 mx-5">
            <div className="mb-5" style={fontName}>{userName}님 환영합니다.</div>

            {(status !== "connected") ?
                (<div><OnboardingButton/></div>)
                :
                (
                    <div className="container">
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
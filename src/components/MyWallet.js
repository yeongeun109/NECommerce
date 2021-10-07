import React from "react";
import ChargeEther from "./ChargeEther";
import GetAccount from "./GetAccount";
import chargeNeTokenButton from "../assets/scripts/charge-NeToken";
import TransferNFT from "../assets/scripts/transfer-nft";

const MyWallet = (props) => {
    const chargeNe = () => {
        chargeNeTokenButton();
    }

    const transferNFT = () => {
        let price = 5;
        let tokenId = 12;
        TransferNFT("",price, tokenId);
    }

    return (
        <div>
            <GetAccount account={props.account}/>
            <div>
                <button onClick={chargeNe}>ne 토큰 충전하기</button>
            </div>
            <div>
                <button onClick={transferNFT}>nft 구매하기</button>
            </div>
        </div>
    )
}

export default MyWallet
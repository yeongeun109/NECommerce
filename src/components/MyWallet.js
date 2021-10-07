import React from "react";
import GetAccount from "./GetAccount";
import chargeNeTokenButton from "../assets/scripts/charge-NeToken";

const MyWallet = (props) => {
    const chargeNe = () => {
        chargeNeTokenButton();
    }

    return (
        <div>
            <GetAccount account={props.account}/>
            <div>
                <button onClick={chargeNe}>ne 토큰 충전하기</button>
            </div>
        </div>
    )
}

export default MyWallet
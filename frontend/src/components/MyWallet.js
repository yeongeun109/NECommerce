import React from "react";
import GetAccount from "./GetAccount";
import chargeNeTokenButton from "../assets/scripts/charge-NeToken";

const MyWallet = (props) => {
    const chargeNe = () => {
        chargeNeTokenButton();
    }

    return (
        <div className="mt-4">
            <GetAccount account={props.account}/>
            <div>
                <button type="button" className="btn btn-warning" onClick={chargeNe}>NE 토큰 충전하기</button>
            </div>
        </div>
    )
}

export default MyWallet
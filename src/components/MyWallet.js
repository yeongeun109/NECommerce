import React from "react";
import ChargeEther from "./ChargeEther";
import GetAccount from "./GetAccount";

const MyWallet = (props) => {
    return (
        <div>
            <GetAccount account={props.account}/>
        </div>
    )
}

export default MyWallet
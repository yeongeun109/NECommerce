import React from "react";
import BigNumber from "bignumber.js";

const chargeNeTokenButton = () => {

    const Web3 = require('web3')
    const contract = require("./NeERC20.json")
    const {REACT_APP_API_URL, REACT_APP_PRIVATE_KEY, REACT_APP_PUBLIC_KEY, REACT_APP_MY_AMAZON_SERVER, REACT_APP_SERVER_BASE_URL} = process.env;
    let web3js

    if (typeof web3 !== 'undefined') {
        web3js = new Web3(window.web3.currentProvider);
        console.log("web3 okay");
    } else {
        web3js = new Web3(new Web3.providers.HttpProvider(REACT_APP_API_URL));
        console.log("web3 undefined");
    }

    const contractAddress = "0xDA4a547a5622fc3D592F0144fBCae2754679245e"
    const NEContract = new web3js.eth.Contract(contract.abi, contractAddress)

    async function chargeNE() {

        let account = await web3js.eth.getAccounts().then(function (array) {
            return array[0]
        });

        const nonce = await web3js.eth.getTransactionCount(REACT_APP_PUBLIC_KEY, 'latest'); //get latest nonce

        console.log(account)

        const tx = {
            'from': REACT_APP_PUBLIC_KEY,
            'to': contractAddress,
            'nonce': nonce,
            'gas': 5000000,
            'data': NEContract.methods.transfer(account, BigNumber(5000000000000000000)).encodeABI()
        }

        const signPromise = web3js.eth.accounts.signTransaction(tx, REACT_APP_PRIVATE_KEY);

        signPromise.then((signedTx) => {
            web3js.eth.sendSignedTransaction(signedTx.rawTransaction, function(err, hash) {
            if (!err) {
                console.log("The hash of your transaction is: ", hash);
            } else {
                console.log("Something went wrong when submitting your transaction:", err)
                console.log(signedTx.transactionHash)
            }
            });
        }).catch((err) => {
            console.log("Promise failed:", err);
        });

    }

    // const onClick = () => {
    //     console.log("충전쓰")
    //     transferNE()
    // };

    // return (
    //     <button onClick={onClick}>
    //         Ne 토큰 충전하기
    //     </button>
    // );
    chargeNE()
}

export default chargeNeTokenButton
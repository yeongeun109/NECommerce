import React from "react";
import BigNumber from "bignumber.js";
import contract from "./MyItem.json";

const TransferNeToken = async (seller, price) => {

    const Web3 = require('web3')
    const {REACT_APP_API_URL} = process.env;
    let web3js

    if (typeof web3 !== 'undefined') {
        web3js = new Web3(window.web3.currentProvider);
        console.log("web3 okay");
    } else {
        web3js = new Web3(new Web3.providers.HttpProvider(REACT_APP_API_URL));
        console.log("web3 undefined");
    }

    const neContractABI = require("./NeERC20.json")
    const neContractAddress = "0xDA4a547a5622fc3D592F0144fBCae2754679245e"
    const NEContract = new web3js.eth.Contract(neContractABI.abi, neContractAddress)

    const nftContractABI = require("./MyItem.json")
    const nftContractAddress = "0x9d83e140330758a8fFD07F8Bd73e86ebcA8a5692"
    const nftContract = new web3js.eth.Contract(nftContractABI.abi, nftContractAddress)

    let account = await web3js.eth.getAccounts().then(function (array) {
        return array[0]
    });

    const nonce = await web3js.eth.getTransactionCount(account, 'latest'); //get latest nonce

    async function transferNFT() {
        web3js.eth.sendTransaction({
            'from': account,
            'to': nftContractAddress,
            'nonce': nonce,
            'gas': 500000,
            'maxPriorityFeePerGas': 1999999987,
            'input': nftContract.methods.transferFrom("0x5c6B0f7Bf3E7ce046039Bd8FABdfD3f9F5021678", account, 0).encodeABI()
        },function(error, hash){
            console.log("nft transfer 오케이")
            console.log(hash);
        }).then({

        });
    }

    async function transferNE() {
        let flag = true;

        let amount = BigNumber(price * Math.pow(10, 18))
        web3js.eth.sendTransaction({
            'from': account,
            'to': neContractAddress,
            'nonce': nonce,
            'gas': 500000,
            'maxPriorityFeePerGas': 1999999987,
            'input': NEContract.methods.transfer(seller, amount).encodeABI()
        }, function (error, hash) {
            console.log(hash);
        }).then(() => {
            console.log("오케이")
            transferNFT()
        }).catch(() => {
            flag = false;
        })
        return flag;
    }

    transferNE()

}

export default TransferNeToken
import React from "react";
import BigNumber from "bignumber.js";
import axios from "axios";
import jwt from "jsonwebtoken";
import {Router} from "react-router-dom";

const TransferNFT = async (owner, price, tokenId) => {

    const Web3 = require('web3')
    const {REACT_APP_API_URL, REACT_APP_PUBLIC_KEY} = process.env;
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

    // nftContract.methods.ownerOf(tokenId).call().then(
    //
    // );
    // Router.push(`/campaigns/${REACT_APP_PUBLIC_KEY}/requests`)
    // nftContract.methods.ownerOf(0).call(function (err, res) {
    //     if (err) {
    //         console.log("An error occured", err)
    //         return
    //     }
    //     console.log("The balance is: ", res.address)
    // })


    let account = await web3js.eth.getAccounts().then(function (array) {
        return array[0]
    });

    let token = JSON.parse(window.localStorage.getItem("token"));
    token = token.replace('Bearer', "")
    let jwt = require('jsonwebtoken')
    let uid = jwt.decode(token).uid;
    console.log("uid : ", uid)

    const formData = {buyerId:uid, nftId:tokenId}

    const nonce = await web3js.eth.getTransactionCount(account, 'latest'); //get latest nonce

    async function transferNFT() {
        web3js.eth.sendTransaction({
            'from': account,
            'to': nftContractAddress,
            'nonce': nonce,
            'gas': 500000,
            'input': nftContract.methods.transferFrom(ownerAddress, account, tokenId).encodeABI()
        }).then(() => {
            console.log("nft transfer 오케이")
            axios
                .post(
                    '/api/v1/product/purchase',
                    formData
                )
                .then((response) => {
                    alert("구매 성공했습니다.");
                })
                .catch((error) => {
                    alert("구매 실패했습니다.");
                })
        }).catch(() => {
        })
    }

    async function transferNE() {
        let amount = BigNumber(price * Math.pow(10, 18))
        web3js.eth.sendTransaction({
            'from': account,
            'to': neContractAddress,
            'nonce': nonce,
            'gas': 500000,
            'maxPriorityFeePerGas': 1999999987,
            'input': NEContract.methods.transfer(ownerAddress, amount).encodeABI()
        }, function (error, hash) {
            console.log(hash);
        }).then(() => {
            console.log("오케이")
        }).catch(() => {
        })
    }

    transferNE()
    transferNFT()
}

export default TransferNFT
import React from 'react';
import contract from "./MyItem.json";
import axios from "axios";
import jwt from "jsonwebtoken";

const TransactNFT = (nftName, imgURI, intro, category) => {

    require("dotenv").config();
    const Web3 = require('web3')
    const {REACT_APP_API_URL, REACT_APP_PRIVATE_KEY, REACT_APP_PUBLIC_KEY, REACT_APP_MY_AMAZON_SERVER, REACT_APP_SERVER_BASE_URL} = process.env;

    // const provider = require("eth-provider");
    // const web3 = new Web3(provider(REACT_APP_API_URL))

    const contract = require("./MyItem.json")

    let web3js

    if (typeof web3 !== 'undefined') {
        web3js = new Web3(window.web3.currentProvider);
        console.log("web3 okay");
    } else {
        web3js = new Web3(new Web3.providers.HttpProvider("https://ropsten.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161"));
        console.log("web3 undefined");
    }

    //const contractAddress = "0x065CA34BECe8BcF281e87AECda088ef10c00168A" // 영은
    const contractAddress = "0x72fc26DEc1554b816C39e12601534e92aF292898"; // 영찬
    const nftContract = new web3js.eth.Contract(contract.abi, contractAddress)

    var token = JSON.parse(window.localStorage.getItem("token"));
    token = token.replace('Bearer', "")
    var jwt = require('jsonwebtoken')
    var uid = jwt.decode(token).uid;
    console.log("uid : ", uid)

    async function mintNFT(nftName, imgURI, intro, category) {

        let account = await web3js.eth.getAccounts().then(function (array) {
            return array[0]
        });
        console.log(nftName)
        console.log(imgURI)
        console.log(intro)
        console.log(category)
        console.log(account)

        const nonce = await web3js.eth.getTransactionCount(account, 'latest'); //get latest nonce
        let tx_hash;


        //리턴값 받아서 BE에 전달(해시값 이랑 NFT 정보)
        //const formData = {address:account, ownerId:1, token:JSON.parse(window.localStorage.getItem("token"))}
        const formData = {category:category, explanation:intro, imageUrl:imgURI, seller_id:uid, title:nftName}

        //const formData = {email:"test1@naver.com", password:"ssafy407!"}
        //const formData = {email:"aaaa@naver.com", name:"aaaa", password:"aaaaaaaa!"}
        //api/v1/wallet/token
        let result = false


        web3js.eth.sendTransaction({
            'from': account,
            'to': contractAddress,
            'nonce': nonce,
            'gas': 500000,
            'maxPriorityFeePerGas': 1999999987,
            'input': nftContract.methods.mintImage(nftName, account, imgURI, intro, category).encodeABI()
        },function(error, hash){
            tx_hash = hash;
            console.log(tx_hash);
        }).then(
            axios
                .post(
                    '/api/v1/nft/register',
                    formData
                )
                .then((response) => {
                    alert("상품 등록에 성공하였습니다");
                    result = true;

                })
                .catch((error) => {
                    result = false;
                    alert("상품을 등록하지 못햇습니다");
                })
        );




        // const signPromise = web3js.eth.accounts.signTransaction(tx, REACT_APP_PRIVATE_KEY);

        // signPromise.then((signedTx) => {
        //
        //     web3js.eth.sendSignedTransaction(signedTx.rawTransaction, function(err, hash) {
        //     if (!err) {
        //         console.log("The hash of your transaction is: ", hash);
        //     } else {
        //         console.log("Something went wrong when submitting your transaction:", err)
        //     }
        //     });
        // }).catch((err) => {
        //     console.log("Promise failed:", err);
        // });



        //리턴값 받아서 BE에 전달(해시값 이랑 NFT 정보)
        //const formData = {name:nftName, tokenURI:imgURI, description:intro, category:category}

        // axios
        //   .post(
        //     '/api/vi/nft/register',
        //     formData
        //   )
        //   .then((response) => {
        //     alert("상품 등록에 성공하였습니다");
        //     //history.push(`/detail/${response.data}`);
        //     //response.data = NFT의 ID값
        //   })
        //   .catch((error) => {
        //     alert("상품을 등록하지 못햇습니다");
        //   });
        const nftId = 2;
        return true;
    }
    return mintNFT(nftName, imgURI, intro, category)
    // .then((respons) => {
    //        console.log(respons)
    //     return respons
    //  })

}

// mintNFT("https://myimageuploadserver.s3.ap-northeast-2.amazonaws.com/nft-metadata.json")
export default TransactNFT  
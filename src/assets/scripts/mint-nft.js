import React from 'react';
import contract from "./MyItem.json";

const TransactNFT = (tokenURI) => {

    require("dotenv").config();
    const Web3 = require('web3')
    const {REACT_APP_API_URL, REACT_APP_PRIVATE_KEY, REACT_APP_PUBLIC_KEY, REACT_APP_MY_AMAZON_SERVER} = process.env;

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

    const contractAddress = "0x065CA34BECe8BcF281e87AECda088ef10c00168A"
    const nftContract = new web3js.eth.Contract(contract.abi, contractAddress)

    async function mintNFT(tokenURI) {
        let account = await web3js.eth.getAccounts().then(function (array) {
            return array[0]
        });
        console.log(account)

        const nonce = await web3js.eth.getTransactionCount(account, 'latest'); //get latest nonce

        web3js.eth.sendTransaction({
            'from': account,
            'to': contractAddress,
            'nonce': nonce,
            'gas': 500000,
            'maxPriorityFeePerGas': 1999999987,
            'input': nftContract.methods.mintImage("name", account, tokenURI, "explane", 0).encodeABI()
        }).then(console.log);

        // const signPromise = web3js.eth.accounts.signTransaction(tx, REACT_APP_PRIVATE_KEY);
        //
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

    }

    mintNFT(tokenURI)
}

// mintNFT("https://myimageuploadserver.s3.ap-northeast-2.amazonaws.com/nft-metadata.json")
export default TransactNFT  
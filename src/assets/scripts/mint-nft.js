import React from 'react';

const TransactNFT = (tokenURI) => {

    require("dotenv").config();
    const Web3 = require('web3')
    const { REACT_APP_API_URL, REACT_APP_PRIVATE_KEY, REACT_APP_PUBLIC_KEY, REACT_APP_MY_AMAZON_SERVER } = process.env;
    
    const provider = require("eth-provider");
    const web3 = new Web3(provider(REACT_APP_API_URL))
    
    const contract = require("../artifacts/contracts/MyNFT.sol/MyNFT.json")
    
    const contractAddress = "0x4A14FeBd1B851C98d9a568cAd986C990D8CFeA49"
    const nftContract = new web3.eth.Contract(contract.abi, contractAddress)
    
    async function mintNFT(tokenURI) {
        console.log(contractAddress)
        const nonce = await web3.eth.getTransactionCount(REACT_APP_PUBLIC_KEY, 'latest'); //get latest nonce
        //the transaction
        const tx = {
          'from': REACT_APP_PUBLIC_KEY,
          'to': contractAddress,
          'nonce': nonce,
          'gas': 500000,
          'maxPriorityFeePerGas': 1999999987,
          'data': nftContract.methods.mintNFT(REACT_APP_PUBLIC_KEY, tokenURI).encodeABI()
        };
        const signPromise = web3.eth.accounts.signTransaction(tx, REACT_APP_PRIVATE_KEY);
        signPromise.then((signedTx) => {
    
            web3.eth.sendSignedTransaction(signedTx.rawTransaction, function(err, hash) {
            if (!err) {
                console.log("The hash of your transaction is: ", hash); 
            } else {
                console.log("Something went wrong when submitting your transaction:", err)
            }
            });
        }).catch((err) => {
            console.log("Promise failed:", err);
        });
    
      }
    mintNFT(tokenURI)    
}

// mintNFT("https://myimageuploadserver.s3.ap-northeast-2.amazonaws.com/nft-metadata.json")
export default TransactNFT  
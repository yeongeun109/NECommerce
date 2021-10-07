import React from "react";
import BigNumber from "bignumber.js";
import axios from "axios";
import jwt from "jsonwebtoken";
import { Router } from "react-router-dom";

const TransferNFT = async (price, tokenId, nftId, userId, productId) => {
  const Web3 = require("web3");
  const { REACT_APP_API_URL, REACT_APP_PUBLIC_KEY } = process.env;
  let web3js;

  if (typeof web3 !== "undefined") {
    web3js = new Web3(window.web3.currentProvider);
    console.log("web3 okay");
  } else {
    web3js = new Web3(new Web3.providers.HttpProvider(REACT_APP_API_URL));
    console.log("web3 undefined");
  }

  const neContractABI = require("./NeERC20.json");
  const neContractAddress = "0xDA4a547a5622fc3D592F0144fBCae2754679245e";
  const NEContract = new web3js.eth.Contract(neContractABI.abi, neContractAddress);

  const nftContractABI = require("./MyItem.json");
  const nftContractAddress = "0x467cAea8a04E52EbF79b5a64b0d6306B38096E65";
  const nftContract = new web3js.eth.Contract(nftContractABI.abi, nftContractAddress);

  // nftContract.methods.ownerOf(0).call(function (err, res) {
  //     if (err) {
  //         console.log("An error occured", err)
  //         return
  //     }
  //     console.log("The balance is: ", res.address)
  // })

  const ownerAddress = await nftContract.methods.ownerOf(tokenId).call();
  console.log("ownerAddress : " + ownerAddress);
  console.log("price : " + price);
  console.log("tokenId : " + tokenId);

  const h = "0x6ce928a6fc817168af1591498fa09de256915d7d8ef5f160b98e97dc85684116";
  let x = await web3js.eth.getTransaction(h);
  console.log(x);

  let account = await web3js.eth.getAccounts().then(function (array) {
    return array[0];
  });

  // let token = window.localStorage.getItem("token");
  // token = token.replace('Bearer', "")
  // let jwt = require('jsonwebtoken')
  // console.log("jwt : " +jwt.decode(token))
  // let uid = jwt.decode(token).uid;
  // console.log("uid : ", uid.toString().trim())

  const formData = { buyerId: userId, nftId: nftId, productId: productId };

  console.log("uid:" + userId);
  console.log("nftId:" + nftId);
  const nonce = await web3js.eth.getTransactionCount(account, "latest"); //get latest nonce

  async function transferNFT() {
    web3js.eth
      .sendTransaction({
        from: account,
        to: nftContractAddress,
        nonce: nonce,
        gas: 500000,
        input: nftContract.methods.transferFrom(ownerAddress, account, tokenId).encodeABI(),
      })
      .then(() => {
        console.log("nft transfer 오케이");
        axios
          .put("/api/v1/product/purchase", formData)
          .then((response) => {
            alert("구매 성공했습니다.");
          })
          .catch((error) => {
            alert("구매 실패했습니다.");
          });
      })
      .catch(() => {});
  }

  async function transferNE() {
    let amount = new BigNumber(price * Math.pow(10, 18)) ;
    web3js.eth
      .sendTransaction(
        {
          from: account,
          to: neContractAddress,
          nonce: nonce,
          gas: 500000,
          // 'maxPriorityFeePerGas': 1999999987,
          input: NEContract.methods.transfer(ownerAddress, amount).encodeABI(),
        },
        function (error, hash) {
          console.log(hash);
        }
      )
      .then(() => {
        console.log("오케이");
      })
      .catch(() => {});
  }

  transferNE();
  transferNFT();
};

export default TransferNFT;

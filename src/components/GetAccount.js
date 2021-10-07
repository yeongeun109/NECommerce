import web3 from './Wallet';
import React, { useState } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap'
import { useMetaMask } from "metamask-react";
const GetAccount = (props) => {
  // console.log(props.account)
  const [addressBalance, setAddressBalance] = useState("")
  const [address, setAddress] = useState(props.account)
  const [neBalance, setNeBalance] = useState("")
  
  async function baseAccountBalance () {
    web3.eth.getBalance(address, function(error, wei){
      if(!error) {
        let balance = web3.utils.fromWei(wei, "ether")
        // console.log(balance)
        setAddressBalance(balance + " ETH")
      }
    })
  }

  async function getERC20TokenBalance () {
    const ne = require("../assets/scripts/NeERC20.json")
    const contractAddress = "0xDA4a547a5622fc3D592F0144fBCae2754679245e";

    // let contract = web3.eth.contract(myItem.abi).at(contractAddress);
    const contract = new web3.eth.Contract(ne.abi, contractAddress)
    setNeBalance(await contract.methods.balanceOf(props.account).call());
    // console.log("neBalance : " + neBalance)
  }

  getERC20TokenBalance()
  baseAccountBalance()
  return (
    <div className="get-account">
      <div>
        현재 연결된 계정 {props.account}
      </div>
      <div>
        현재 ETH 잔액 {addressBalance}
      </div>
      <div>
        현재 NE 잔액 {neBalance} NE
      </div>
    </div>
  );
}

export default GetAccount
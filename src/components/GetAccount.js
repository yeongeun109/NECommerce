import web3 from './Wallet';
import React, { useState } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap'
import { useMetaMask } from "metamask-react";
const GetAccount = (props) => {
  console.log(props.account)
  const [baseBalance, setBaseBalance] = useState("")
  const [addressBalance, setAddressBalance] = useState("")
  const [address, setAddress] = useState(props.account)
  
  async function baseAccountBalance () {
    web3.eth.getBalance(address, function(error, wei){
      if(!error) {
        var balance = web3.utils.fromWei(wei, "ether")
        // console.log(balance)
        setAddressBalance(balance + "ETH")
      }
    })
  }

  const onChangeInput = (event) => {
    setAddress(event.target.value);
  }

  const changeAddress = () => {
    web3.eth.getBalance(address, function(error, wei){
      if(!error) {
        var balance = web3.utils.fromWei(wei, "ether")
        // console.log(balance)
        setAddressBalance(balance + "ETH")
      }
    })
  }

  return (
    <div className="get-account">
      <div>
        현재 연결된 계정은 {props.account}입니다.
      </div>
      <div>
        현재 계정에는 {addressBalance}
      </div>
    </div>
  );
}

export default GetAccount
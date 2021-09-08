import web3 from './Wallet';
import React, { useState } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap'

const GetAccount = () => {
  const [coinbase, setCoinbase] = useState("")
  const [baseBalance, setBaseBalance] = useState("")
  const [addressBalance, setAddressBalance] = useState("")
  const [address, setAddress] = useState("")
  // coinbase 설정
  web3.eth.getCoinbase()
  .then((response) => {
    // console.log(response)
    setCoinbase(response)
  })
  // coinbase에 잔액이 얼마나 남았는지 설정
  if (coinbase !== "") {
    web3.eth.getBalance(coinbase, function(error, wei){
      if(!error) {
        var balance = web3.utils.fromWei(wei, "ether")
        // console.log(balance)
        setBaseBalance(balance + "ETH")
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
        <p>{baseBalance}</p>
      </div>
      <InputGroup id="id-search">
        <FormControl
        placeholder=""
        value={address}
        onChange={onChangeInput}/>
        <Button
        onClick={changeAddress}
        >주소 입력</Button>      
      </InputGroup>          
      <div>
        현재 계정에는 {addressBalance}
      </div>
    </div>
  );
}

export default GetAccount
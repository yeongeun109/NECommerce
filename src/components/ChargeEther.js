import web3 from './Wallet';
import React, { useState } from 'react';
import { Button, InputGroup, FormControl } from 'react-bootstrap'

const ChargeEther = () => {
  const [coinbase, setCoinbase] = useState("")
  const [addressBalance, setAddressBalance] = useState("")
  const [address, setAddress] = useState("")
  const [chargeEther, setChargeEther] = useState("")

  // coinbase 설정
  web3.eth.getCoinbase()
  .then((response) => {
    // console.log(response)
    setCoinbase(response)
  })
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
  const onChangeInputEther = (event) => {
      setChargeEther(event.target.value);
    }
    
const chargingEther = () => {
    const ether = chargeEther * 1e18
    const tx = {
        from : `${coinbase}`,
        to: `${address}`,
        value: `${ether}`}
    web3.eth.sendTransaction(tx)
    .then((response) => {
        console.log(response)
        changeAddress()
    })
  }

  return (
    <div className="charge-ether">

      <InputGroup id="id-search">
        <FormControl
        placeholder="주소를 입력해주세요"
        value={address}
        onChange={onChangeInput}/>
        <Button
        onClick={changeAddress}
        >주소 입력</Button>      
      </InputGroup>          
      <div>
        현재 계정에는 {addressBalance}
      </div>

      <InputGroup id="charge-ether">
        <FormControl
        placeholder="충전할 이더를 입력해주세요"
        value={chargeEther}
        onChange={onChangeInputEther}/>
        <Button
        onClick={chargingEther}
        >충전해줘</Button>      
      </InputGroup>          
      <div>
        현재 계정에는 {addressBalance}
      </div>
    </div>
  );
}

export default ChargeEther
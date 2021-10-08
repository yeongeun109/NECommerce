import web3 from './Wallet';
import React, { useState } from 'react';
import { Container, Row, Col } from 'react-bootstrap'
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
    // console.log("neBalance : " + neBalance)
    
    setNeBalance(await contract.methods.balanceOf(props.account).call());
    
  }

  getERC20TokenBalance()
  baseAccountBalance()
  return (
    <Container className="get-account">
      <Row>
          <div className="card border-primary mb-3" style={{height:100}}>
            <div className="card-header">현재 연결된 계정</div>
            <div className="card-body">
              <h4 className="card-title">{props.account}</h4>
            </div>
          </div>
        
          <div className="card border-primary mb-3">
            <div className="card-header">현재 ETH 잔액</div>
            <div className="card-body">
              <h4 className="card-title">{addressBalance}</h4>
            </div>
          </div>
          <div className="card border-primary mb-3">
            <div className="card-header">현재 NE 잔액</div>
            <div className="card-body">
              <h4 className="card-title">{neBalance / 1000000000000000000} NE</h4>
            </div>
          </div>
      </Row>
    </Container>
  );
}

export default GetAccount
import web3 from './Wallet';
import React, { useState } from 'react';
import { Button, InputGroup, Form } from 'react-bootstrap'

const CreateWallet = () => {
  const [address, setAddress] = useState("")
  const [password, setPassword] = useState("")
  const onChangePassword = (event) => {
      setPassword(event.target.value)
  }
  const createAccount = () => {
      web3.eth.personal.newAccount(password)
      .then((response) => {
          setAddress(response)

          // console.log(response) = address 주소 string을 반환함.
      })
  }

  return (
    <div className="create-account">
      <div>
        <InputGroup>
            <Form.Control
                placeholder="비밀번호를 입력해주세요"
                type="password"
                value={password}
                onChange={onChangePassword}
            />
            <Button
            onClick={createAccount}
            >지갑생성</Button>      
        </InputGroup>
        <p>생성된 계정의 주소는 : {address}</p>
      </div>
    </div>
  );
}

export default CreateWallet
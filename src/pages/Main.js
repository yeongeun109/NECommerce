import React, {useState} from "react";
import { Form, InputGroup  } from "react-bootstrap";
import SearchButton from "../components/Buttons/SearchButton";
import NFTList from "../components/NFTList";
import Web3 from "web3";
import {red} from "@material-ui/core/colors";
const Main = () => {
    const [searchText, setSearchText] = useState("");
    const [searchCategory, setSearchCategory] = useState("");
    const onChangeInput = (event) => {
      setSearchText(event.target.value);
    };
    const onClickCategory = (event) => {
      if (event.target.id === "전체") {
        setSearchCategory("");
      } else {
        setSearchCategory(event.target.id);
      }
    };

    /*
    blockHash: "0x9df145df7be255e67d1d0d7fda5658d4391e7da303b1a632214e3bc16c9b0984"
blockNumber: 11176166
chainId: "0x3"
from: "0xBc564f9F13874ACd63E54d32FEBC76135ee8182C"
gas: 255852
gasPrice: "1000000010"
hash: "0x591c7944860d7f5afd9b81c1516f83419cb51513f0426360c83d7150ceaf34d8"
input: "0x6f185be000000000000000000000000000000000000000000000000000000000000000a0000000000000000000000000bc564f9f13874acd63e54d32febc76135ee8182c00000000000000000000000000000000000000000000000000000000000000e000000000000000000000000000000000000000000000000000000000000001800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000a4e45636f6d6d6572636500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006568747470733a2f2f757365722d696d616765732e67697468756275736572636f6e74656e742e636f6d2f35343833373234322f3133363233343136352d37643830656432642d646362342d343932612d616230362d3565663831383533383734302e706e6700000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000124e45636f6d6d657263652773206c6f676f2e0000000000000000000000000000"
maxFeePerGas: "1000000014"
maxPriorityFeePerGas: "1000000000"
nonce: 47
r: "0xfb7369b8a5df418b8a6894b53480bbbebc5c95960aa1f3b7c5b87e265122d48c"
s: "0x40312837f8d9c7c329c41c04c9897e56e6497c236b54eaafbc6bce17074f7821"
to: "0x467cAea8a04E52EbF79b5a64b0d6306B38096E65"
transactionIndex: 36
type: 2
v: "0x0"
value: "0"
     */

    const {REACT_APP_API_URL} = process.env;
    const Web3 = require('web3')
    let web3 = new Web3(new Web3.providers.HttpProvider(REACT_APP_API_URL));
    const hash = "0x591c7944860d7f5afd9b81c1516f83419cb51513f0426360c83d7150ceaf34d8";
    let tx, blockHash;
    const test = async() => {
        tx = await web3.eth.getTransaction(hash)
        blockHash = tx.blockHash
        console.log(tx)
    }
    test()

    const myStyle = {
        color: "gray",
        padding: "10px",
        fontFamily: "Sans-Serif"
    };
    return(

        <div>
            MainPage
            <div>
                <img
                    src="https://user-images.githubusercontent.com/54837242/136234165-7d80ed2d-dcb4-492a-ab06-5ef818538740.png"
                    width='200px'
                    height='200px'
                    alt='mainLogo' />

                <div>
                    <h1 style={myStyle}>blockHash</h1>
                    {/*<h1 style={myStyle}>${tx.blockHash}</h1>*/}
                </div>
            </div>
            <div>
                <div className="main-explore">

                <InputGroup id="main-search" className="mb-5">
                    <Form.Control
                    placeholder=""
                    value={searchText}
                    onChange={onChangeInput}
                    />
                    <SearchButton />
                </InputGroup>

                <div className="main-explore-option justify-content-end">
                    <div className="btn-group mt-3 mb-3">
                        <input
                            className="btn-check btn-info"
                            autoComplete="off"
                            onClick={onClickCategory}
                            id="전체"
                        />
                        <label className="btn fs-4" htmlFor="전체">
                            전체
                        </label>

                        <input
                            className="btn-check btn-light"
                            autoComplete="off"
                            onClick={onClickCategory}
                            id="Art"
                        />
                        <label className="btn fs-4" htmlFor="Art">
                            ART
                        </label>
                        <input
                            className="btn-check btn-light"
                            autoComplete="off"
                            onClick={onClickCategory}
                            id="Photo"
                        />
                        <label className="btn fs-4" htmlFor="Photo">
                            Photo
                        </label>
                    
                    </div>
                </div>
                </div>

                <NFTList searchText={searchText} searchCategory={searchCategory} />
            </div>
        </div>
    )
}

export default Main
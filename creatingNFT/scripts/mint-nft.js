require("dotenv").config();
const Web3 = require('web3')
const { API_URL, PRIVATE_KEY, PUBLIC_KEY, MY_AMAZON_SERVER } = process.env;

const provider = require("eth-provider");
const web3 = new Web3(provider(API_URL))

const contract = require("../artifacts/contracts/MyNFT.sol/MyNFT.json")

const contractAddress = "0x4A14FeBd1B851C98d9a568cAd986C990D8CFeA49"
const nftContract = new web3.eth.Contract(contract.abi, contractAddress)

async function mintNFT(tokenURI) {
    const nonce = await web3.eth.getTransactionCount(PUBLIC_KEY, 'latest'); //get latest nonce
  
    //the transaction
    const tx = {
      'from': PUBLIC_KEY,
      'to': contractAddress,
      'nonce': nonce,
      'gas': 500000,
      'maxPriorityFeePerGas': 1999999987,
      'data': nftContract.methods.mintNFT(PUBLIC_KEY, tokenURI).encodeABI()
    };
    const signPromise = web3.eth.accounts.signTransaction(tx, PRIVATE_KEY);
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

mintNFT(`${MY_AMAZON_SERVER}/nft-metadata.json`)
  
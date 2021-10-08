
import Web3 from 'web3';

const URL = process.env.REACT_APP_API_URL
const web3 = new Web3(new Web3.providers.HttpProvider(URL))

export default web3
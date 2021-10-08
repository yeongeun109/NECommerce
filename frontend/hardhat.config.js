/**
 * @type import('hardhat/config').HardhatUserConfig
 */
require('dotenv').config();
require('@nomiclabs/hardhat-ethers');
const { REACT_APP_API_URL, REACT_APP_PRIVATE_KEY } = process.env;
module.exports = {
  solidity: "0.7.3",
  defaultNetwork:"myNetwork",
  networks: {
    hardhat: {},
    myNetwork: {
      url: REACT_APP_API_URL,
      chainId: 921,
      accounts: [`0x${REACT_APP_PRIVATE_KEY}`]
    }
  },
};

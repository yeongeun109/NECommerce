// Contract based on https://docs.openzeppelin.com/contracts/3.x/erc721
// SPDX-License-Identifier: MIT
pragma solidity >=0.7.3;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
// inherit ERC721 functions
import "@openzeppelin/contracts/utils/Counters.sol";
// provide counters that can only be incremented or decremented by one
// to keep track of the total number of NFTs minted and set the unique IDD to our new NFT
import "@openzeppelin/contracts/access/Ownable.sol";
// access control.. only owner can mint NFTs. if anyone to be able to mint an NFT, remove Ownable, onlyOwner.

// openzeppelin contract를 상속받기떄문에 onwerof, traferFrom 등의 function을 만들지않아도 사용가능함.
contract MyNFT is ERC721, Ownable {
    using Counters for Counters.Counter;
    Counters.Counter private _tokenIds;
    // ERC721 (contractname, symbol)
    constructor() public ERC721("MyNFT", "NFT") {}

    function mintNFT(address recipient, string memory tokenURI)
        public onlyOwner
        returns (uint256)
    {
            _tokenIds.increment();

            uint256 newItemId = _tokenIds.current();
            _mint(recipient, newItemId);
            _setTokenURI(newItemId, tokenURI);

            return newItemId;
    } 
}
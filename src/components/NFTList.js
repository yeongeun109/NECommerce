import React, { useState, useEffect } from "react";
import NFTCard from "./NFTCard";
import { faBan } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import "./NFTList.css";
import axios from "axios";

const NFTList = (props) => {
    const [filteredNFTs, setFilteredNFTs] = useState([]);
    const [NFTs, setNFTs] = useState([]);
  
    // useEffect(() => {
    //   axios
    //     .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/home/list`)
    //     .then((response) => {
    //       setRooms(response.data);
    //     })
    //     .catch((error) => {});
    // }, []);
  
    useEffect(() => {
      setFilteredNFTs(
        NFTs.filter((NFT) => {
          const filteredNFTs =
            NFT.title.includes(props.searchText) ||
            NFT.owner.userName.includes(props.searchText) ||
            NFT.owner.userId.includes(props.searchText);
          return filteredNFTs;
        })
      );
    }, [props.searchText, NFTs]);
  
    const category = (s) => {
      switch (s) {
        case 1:
          return "Art";
        case 2:
          return "Photo";
        default:
          return "";
      }
    };
    useEffect(() => {
      setFilteredNFTs(
        NFTs.filter((NFT) => {
          const filteredNFTs = category(NFT.category).includes(
            props.searchCategory
          );
          return filteredNFTs;
        })
      );
    }, [props.searchCategory, NFTs]);
  
    return (
        <>
        {filteredNFTs.length === 0 ? (
          <div className="NFT-search-result">
            <>
              <FontAwesomeIcon
                icon={faBan}
                size="10x"
                style={{ color: "hsl(350deg 100% 66%)" }}
              />
              <h1>상품 없음</h1>
              <p>
                상품이 없습니다.
              </p>
            </>
          </div>
        ) : (
          <div className="NFT-grid">
            {filteredNFTs.map((NFT, idx) => {
              return (
                <div key={idx}>
                  <NFTCard
                    key={NFT.id}
                    id={NFT.id}
                    intro={NFT.intro}
                    owner={NFT.owner}
                    title={NFT.title}
                    thumbnail={NFT.thumbnail}
                    description={NFT.description}
                    category={NFT.category}
                    price={NFT.price}
                  />
                </div>
              );
            })}
          </div>
        )}
      </>
    );
}

export default NFTList
import React, { useState, useEffect } from "react";
import { Container, Row, Col, Image } from "react-bootstrap";
import axios from "axios";
import Category from "../assets/Category";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCrown, faCheck } from "@fortawesome/free-solid-svg-icons";
import GetUserPK from "../assets/GetUserPK";
import { useParams } from "react-router";
import TransferNFT from "../assets/scripts/transfer-nft";

const SellingNFTDetail = (props, { history }) => {
  const userPK = GetUserPK();
  const [NFTDetail, setNFTDetail] = useState("");
  const [loading, setLoading] = useState(true);
  const { productId } = useParams();
  const [imageURL, setImageURL] = useState("");
  const [explanation, setExplanation] = useState("");
  const [price, setPrice] = useState("");
  const [nftId, setNftId] = useState("");
  useEffect(() => {
    var t = window.localStorage.getItem("token");
    if (t === "") {
      history.push("/");
    }
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.get(`api/v1/product/detail/${productId}`);
      console.log(result);
      setNFTDetail(result.data.success.nft);
      setLoading(false);
      setImageURL(result.data.success.nft.imageUrl);
      setPrice(result.data.success.price);
      setExplanation(result.data.success.nft.explanation);
      setNftId(result.data.success.nft.id);
    };

    fetchData();
  }, []);

  const buyProduct = () => {
    TransferNFT(price, NFTDetail.tokenId, nftId, userPK, productId);
  };
  const cardStyle = {
    width: "70%",
  };

  const layoutCenter = {
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  };

  const imgStyle = {
    minWidth: "500px",
    maxWidth: "600px",
  };

  return (
    <Row style={layoutCenter}>
      <div style={cardStyle}>
        <div className="card mb-3">
          <h3 className="card-header">{NFTDetail.title}</h3>
          <div className="card-body">
            <span className="badge bg-dark">{NFTDetail.category === "0" ? "Art" : "Photo"}</span>
          </div>
          <div style={layoutCenter}>
            <img src={imageURL} className="m-2" style={imgStyle} />
          </div>
          <ul className="list-group list-group-flush">
            <li className="list-group-item">
              <b>상품 설명</b>
            </li>
            <li className="list-group-item">{explanation}</li>
            <li className="list-group-item">
              <b>가격</b>
            </li>
            <li className="list-group-item">{price}Ne</li>
          </ul>
          <div className="card-body" style={layoutCenter}>
            <button type="button" className="btn btn-lg btn-info" onClick={buyProduct}>
              구매
            </button>
          </div>
          <div className="card-footer text-muted">{NFTDetail.transactionHash}</div>
        </div>
      </div>
    </Row>
  );
};

export default SellingNFTDetail;

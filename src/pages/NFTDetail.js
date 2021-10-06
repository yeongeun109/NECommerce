import React, { useState, useEffect, useParams } from "react";
import { Container, Row, Col, Image } from "react-bootstrap";
import axios from "axios";
import Category from "../assets/Category";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCrown, faCheck } from "@fortawesome/free-solid-svg-icons";
import GetUserPK from "../assets/GetUserPK";

const NFTDetail = (props,{history}) => {
    const userPK = GetUserPK();
    const [NFTDetail, setNFTDetail] = useState("")
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        var t = window.localStorage.getItem("token");
        if (t === "") {
          history.push("/");
        }
      }, []);

  useEffect(() => {
    const fetchData = async () => {
        const result = await axios.get(
        `api/v1/nft/detail/9`,
        
        );
        console.log(result)
        setNFTDetail(result);
        setLoading(false);
    };
    fetchData();
  }, []);
    
  return (
    <div>
      <Container>
        <Row>
          <Col>
            <Image src={NFTDetail.tokenURI} fulid id="tokenURI"/>
          </Col>
          <Col>
            <div>
              상품이름 : {NFTDetail.name}
            </div>
            <div>
              상품 설명 : {NFTDetail.introValue}
            </div>
          </Col>
        </Row>
        <Row>
          <div>
            거래내역
            <span>{NFTDetail.transactionHistory}</span>
          </div>
          <div>
            
          </div>
        </Row>
      </Container>
    </div>
  );

    
}

export default NFTDetail
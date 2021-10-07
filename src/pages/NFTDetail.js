import React, { useState, useEffect} from "react";
import { Container, Row, Col, Image } from "react-bootstrap";
import axios from "axios";
import Category from "../assets/Category";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCrown, faCheck } from "@fortawesome/free-solid-svg-icons";
import GetUserPK from "../assets/GetUserPK";
import { useParams } from "react-router";

const NFTDetail = (props,{history}) => {
    const userPK = GetUserPK();
    const [NFTDetail, setNFTDetail] = useState("")
    const [loading, setLoading] = useState(true);
    const { NFTId } = useParams()
    const token = JSON.parse(window.localStorage.getItem("token"))
    console.log(token)
    useEffect(() => {
        var t = window.localStorage.getItem("token");
        if (t === "") {
          history.push("/");
        }
      }, []);

  useEffect(() => {
    const fetchData = async () => {
        const result = await axios.post(
        `api/v1/nft/detail/${NFTId}`,
        {token : token}
        );
        console.log(result)
        setNFTDetail(result.data.success);
        setLoading(false);
    };
    fetchData();
  }, []);
    
  return (
    <div>
      <Container>
        <Row>
          <Col>
            <Image src={NFTDetail.imageUrl} height={500} id="imageUrl"/>
          </Col>
          <Col>
            <div>
              상품이름 : {NFTDetail.title}
            </div>
            <div>
              상품 설명 : {NFTDetail.explanation}
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
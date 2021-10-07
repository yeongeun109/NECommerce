import React, { useState, useEffect } from "react";
import { Row } from "react-bootstrap";
import axios from "axios";
import { useParams } from "react-router";
import { Link } from "react-router-dom";

const NFTDetail = (props, { history }) => {
  const [NFTDetail, setNFTDetail] = useState("");
  const { NFTId } = useParams();
  const token = window.localStorage.getItem("token");
  useEffect(() => {
    if (token === "") {
      history.push("/");
    }
  });

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.post(`api/v1/nft/detail/${NFTId}`, { token: token });
      setNFTDetail(result.data.success);
    };
    fetchData();
  });

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
          {/*<svg xmlns="http://www.w3.org/2000/svg" className="d-block user-select-none" width="100%" height="200"*/}
          {/*     aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice"*/}
          {/*     viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">*/}
          {/*    <rect width="100%" height="100%" fill="#868e96"></rect>*/}
          {/*    */}
          {/*</svg>*/}
          <div style={layoutCenter}>
            <img src={NFTDetail.imageUrl} className="m-2" style={imgStyle} alt="nftimage" />
          </div>
          <ul className="list-group list-group-flush">
            <li className="list-group-item">
              <b>상품 설명</b>
            </li>
            <li className="list-group-item">{NFTDetail.explanation}</li>
          </ul>
          <div className="card-body text-center">
            <Link to={`/creatingorder/${NFTDetail.id}`} className="text-decoration-none">
              <button type="button" className="btn btn-lg btn-info">
                판매 등록
              </button>
            </Link>
          </div>
          <div className="card-footer text-muted">트랜잭션 해시 : {NFTDetail.transactionHash}</div>
        </div>
      </div>
    </Row>
  );
};

export default NFTDetail;

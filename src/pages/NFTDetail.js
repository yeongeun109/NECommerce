import React, { useState, useEffect, useParams } from "react";
import { Container } from "react-bootstrap";
import axios from "axios";
import Category from "../assets/Category";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCrown, faCheck } from "@fortawesome/free-solid-svg-icons";


const NFTDetail = ({history}) => {
    const { code } = useParams();

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
        `api/detail/${code}`,
        {
            headers: {
            Authorization:
                JSON.parse(window.localStorage.getItem("token")),
            },
        }
        );
        setNFTDetail(result.data);
        setLoading(false);
    };
    fetchData();
  }, []);
    
  return (
    <div>
      
    </div>
  );

    
}

export default NFTDetail
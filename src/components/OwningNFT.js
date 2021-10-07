import React, {useEffect, useState} from "react";
import axios from "axios";
import {Table, Image} from "react-bootstrap";
import {Link} from "react-router-dom";
import GetUserPK from "../assets/GetUserPK";

const OwningNFT = (props) => {
    const [loading, setLoading] = useState(true)
    const [MyNFT, setMyNFT] = useState([])

    useEffect(() => {
        const fetchData = async () => {
            const userPK = GetUserPK()
            console.log("userPK : " + userPK)

            const result = await axios.post(
                `api/v1/nft/list/${userPK}`,
                {token: window.localStorage.getItem("token")}
            );
            console.log(result.data.success)
            setMyNFT(result.data.success);
            setLoading(false);
        };
        fetchData();
    }, []);

    const showAsTable = (NFT, idx) => {
        return (
            <tr key={idx}>
                <td>
                    <Link to={`/detail/${NFT.id}`}>
                        <Image src={NFT.imageUrl}/>
                    </Link>
                </td>
                <td>
                    <p>{NFT.title} </p>
                </td>
            </tr>)
    }

    return (
        <>
            {/*{loading ? (*/}
            {/*  <div>*/}
            {/*    Loading...*/}
            {/*  </div>*/}
            {/*) : (*/}
            <Table>
                <thead>
                <tr>
                    <th>이미지</th>
                    <th>제목</th>
                </tr>
                </thead>
                <tbody>

                {MyNFT.map(showAsTable)}
                </tbody>
            </Table>

            {/*)}*/}

        </>
    )
}

export default OwningNFT
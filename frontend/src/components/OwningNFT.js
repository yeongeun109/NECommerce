import React, {useEffect, useState} from "react";
import axios from "axios";
import {Link} from "react-router-dom";
import GetUserPK from "../assets/GetUserPK";
import "../pages/Main.css"
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import Typography from "@material-ui/core/Typography";
import CardMedia from "@material-ui/core/CardMedia";
import {makeStyles} from "@material-ui/core/styles";
import {red} from "@material-ui/core/colors";

const OwningNFT = (props) => {
    const [loading, setLoading] = useState(true)
    const [MyNFT, setMyNFT] = useState([])

    const useStyles = makeStyles((theme) => ({
        root: {
            maxWidth: 345,
        },
        media: {
            height: 0,
            paddingTop: "50%", // 16:9 (56.25)
        },
        expand: {
            transform: "rotate(0deg)",
            marginLeft: "auto",
            transition: theme.transitions.create("transform", {
                duration: theme.transitions.duration.shortest,
            }),
        },
        expandOpen: {
            transform: "rotate(180deg)",
        },
        avatar: {
            backgroundColor: red[500],
        },
    }));

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
    const cardStyle = {
        display: "block",
        height: "40vh",
        maxWidth: "40vw",
    };
    const NFTstyle = useStyles();

    const showAsTable = (NFT, idx) => {
        return (
            <div className="col-4 my-4 mx-4">

                <Link to={`/detail/${NFT.id}`} className="text-decoration-none">
                    <Card id="NFT-card" style={cardStyle}>
                        <CardHeader
                            className="card-header"
                            title={<Typography variant="subtitle1">{NFT.title}</Typography>}
                        />

                        <div>
                            <p className="card-desc-p">{NFT.explanation}</p>
                            <CardMedia id="card-image" className={NFTstyle.media} image={NFT.imageUrl}/>
                        </div>
                    </Card>
                </Link>
            </div>
        )
    }

    return (
        <div className="container">
            <div className="row">

                {MyNFT.map(showAsTable)}
            </div>
        </div>
    )
}

export default OwningNFT
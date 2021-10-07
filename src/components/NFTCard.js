import React from "react";
import { Link } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardHeader from "@material-ui/core/CardHeader";
import CardMedia from "@material-ui/core/CardMedia";
import CardContent from "@material-ui/core/CardContent";
import CardActions from "@material-ui/core/CardActions";
import Avatar from "@material-ui/core/Avatar";

import Typography from "@material-ui/core/Typography";
import { red } from "@material-ui/core/colors";

import "./NFTCard.css";
const useStyles = makeStyles((theme) => ({
  root: {
    maxWidth: 345,
  },
  media: {
    height: 0,
    paddingTop: "70%", // 16:9 (56.25)
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

const status = (s) => {
  switch (s) {
    case true:
      return "selling";
    case false:
      return "sold";
    default:
      return "selling";
  }
};
const NFTCard = (props) => {
  const NFT = useStyles();
  const title = props.nft.title;
  const ownerName = props.owner.name;
   // base64 디코딩하는 부분
  const imgSrc = props.nft.imageUrl;
  const cardStyle = {
    display: "block",
    height: "35vw",
  };

  return (
    <Link to={`/product/${props.id}`} className="text-decoration-none">
      <Card id="NFT-card" style={cardStyle}>
        <span className="ribbon-angle" id={status(props.status)}>
          <small className="card-ribbon">{status(props.status)}</small>
        </span>
        <CardHeader
          className="card-header"
          avatar={
            <Avatar className={NFT.avatar} id="card-avatar">
              {ownerName && ownerName[0]}
            </Avatar>
          }
          title={<text variant="subtitle1" className="change-font">{title}</text>}
          subheader={
            <text variant="subtitle2" className="change-font">
              {props.price} Ne
            </text>
          }
        />

        <div>
          <p className="card-desc-p">{props.nft.explanation}</p>
          <CardMedia id="card-image" className={NFT.media} image={imgSrc} />
        </div>

      </Card>
    </Link>
  );
};

export default NFTCard;

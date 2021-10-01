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

import "./RoomCard.css";
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
const NFTCard = (props) => {
  const NFT = useStyles();
  const Title = props.title;
  const ownerName = props.owner.userName;
  const ownerID = props.owner.userId;
  // base64 디코딩하는 부분
  const imgSrc = "data:image/png;base64,".concat(props.thumbnail);
  const cardStyle = {
    display: "block",
    height: "35vw",
  };
  return (
    <Link to={`/course/${props.code}`} className="text-decoration-none">
      <Card className={classes.root} id="NFT-card" style={cardStyle}>
        <span className="ribbon-angle" id={category(props.category)}>
          <small className="card-ribbon">{category(props.category)}</small>
        </span>
        <CardHeader
          className="card-header"
          avatar={
            <Avatar className={classes.avatar} id="card-avatar">
              {classTeacherName && classTeacherName[0]}
            </Avatar>
          }
          title={<Typography variant="subtitle1">{classTitle}</Typography>}
          subheader={
            <Typography variant="subtitle2">
              {classTeacherName}({classTeacherID})
            </Typography>
          }
        />

        <div>
          <p className="card-desc-p">{props.intro}</p>
          <CardMedia id="card-image" className={classes.media} image={imgSrc} />
        </div>

      </Card>
    </Link>
  );
};

export default NFTCard;

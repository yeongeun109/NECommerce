import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Navbar, Container, Nav, Modal } from "react-bootstrap";
import { Link, Redirect } from "react-router-dom";

import SignIn from "../components/SignIn";
import SignUp from "../components/SignUp";
import allActions from "../actions/index";
import Logo from "../assets/Logo.png";
import "./Header.css";

const Header = () => {
  const [show, setShow] = useState(false);
  const [clickvalue, setClickvalue] = useState("signin");
  const [className, setClassName] = useState("");
  const currentUser = useSelector((state) => state.currentUser);
  const dispatch = useDispatch();

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const token = window.localStorage.getItem("token");
  let isLoggedIn = false;
  if (token == "" || token == null) isLoggedIn = false;
  else isLoggedIn = true;

  const logout = () => {
    alert("로그아웃 되었습니다.");
    dispatch(allActions.userActions.logoutUser());
    window.localStorage.setItem("token", "");
  };

  return (
    <header>
      <Navbar className="navbar-dark bg-primary">
        <Container>
          <div className="navbar-left">
            <Link to="/">
              <img src={Logo} height="50px" width="50px" />
              <span className="text-white header-logo-name">NECommerce</span>
            </Link>
          </div>

          <Nav>
            <div className="nav-link-right text-white">
              <>
                <Link to="/minting" className="text-white">
                  NFT등록
                </Link>
                <Link to="/MyPage" className="text-white">
                  MyPage
                </Link>
                {isLoggedIn && (
                  <span className="logout" onClick={logout}>
                    로그아웃
                  </span>
                )}
              </>
            </div>
          </Nav>
        </Container>
      </Navbar>
    </header>
  );
};

export default Header;

import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Navbar, Container, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

import allActions from "../actions/index";
import Logo from "../assets/Logo.png";
import "./Header.css";

const Header = () => {
  const dispatch = useDispatch();

  const token = window.localStorage.getItem("token");
  let isLoggedIn = false;
  if (token === "" || token == null) isLoggedIn = false;
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
              <img src={Logo} height="50px" width="50px" alt="logo"/>
            </Link>
            <Link to="/">
              <span className="text-white header-logo-name">NECommerce</span>
            </Link>
          </div>

          <Nav>
            <div className="nav-link-right text-white">
              <>
                <Link to="/minting" className="text-white">
                  NFT 발급
                </Link>
                <Link to="/MyPage" className="text-white">
                  My Page
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

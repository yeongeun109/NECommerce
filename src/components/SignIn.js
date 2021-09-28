import React, { useState, useEffect } from "react";

import { Button, Form, Card, Modal } from "react-bootstrap";
import { useForm, Controller } from "react-hook-form";
import { Spinner } from "react-bootstrap";
import axios from "axios";

import "./SignIn.css";
import SignUp from "./SignUp";
const SignIn = () => {
  const [token, setToken] = useState("");
  const [nowLoading, setNowLoading] = useState(false);
  // const dispatch = useDispatch();
  const [show, setShow] = useState(false);
  const [clickvalue, setClickvalue] = useState("");
  // const [className, setClassName] = useState("");
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  
  const components = {
    signup: SignUp,
  };
  
  const ActiveComponent = components[clickvalue];

  const {
    watch,
    handleSubmit,
    control,
    formState: { errors },
  } = useForm();

  useEffect(() => {
    // console.log(onHide);
    window.localStorage.setItem("token", JSON.stringify(token));
  }, [token]);

  const signin = () => {
    setNowLoading(true);
    axios
      .post('api/v1/users/login', {
        email: watch("email", ""),
        password: watch("password", ""),
      })
      .then(async (response) => {
        console.log(response)
        setTimeout(() => {
          setNowLoading(false);
          setToken(response.data.jwtToken);
          // onHide();
          // let userName = "";
          // axios
          //   .get(`${process.env.REACT_APP_SERVER_BASE_URL}/api/user/myname`, {
          //     headers: {
          //       Authorization:
          //         "Bearer " + JSON.parse(window.localStorage.getItem("token")),
          //     },
          //   })
          //   .then((response) => {
          //     dispatch(
          //       allActions.userActions.loginUser(watch("id", ""), response.data)
          //     );
          //   });
          // console.log(userName);
        }, 1000);
      })
      .catch(() => {
        alert("아이디 혹은 비밀번호를 확인해주세요.");
        setNowLoading(false);
      });
  };

  return (
    <>
    <Card>

      <Card.Header>
        <Card.Title>"로그인"</Card.Title>
      </Card.Header>

      <Form
        className="signin-form"
        onSubmit={handleSubmit(signin)}
        >
        {nowLoading ? (
          <div className="spinner">
            <Spinner animation="border" />
          </div>
        ) : (
          <Card.Body>
            <div className="input-box">
              <Form.Label>
                Email {errors.email && <span>{errors.email.message}</span>}
              </Form.Label>

              <Controller
                name="email"
                control={control}
                rules={{
                  required: { value: true, message: "필수 항목입니다" },
                  pattern: {
                    value:
                    /^[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i,
                    message: "이메일 형식에 맞게 입력해주세요",
                  },
                }}
                render={({ field }) => (
                  <Form.Control
                  placeholder="Email을 입력해 주세요"
                  {...field}
                  />
                  )}
                  />
            </div>

            <div className="input-box">
                <div>
                  <Form.Label>
                    비밀번호
                    {errors.password && <span>{errors.password.message}</span>}
                  </Form.Label>

                  <Controller
                    name="password"
                    control={control}
                    rules={{
                      required: {
                        value: true,
                        message: "필수 입력 항목 입니다",
                      },
                      pattern: {
                        value:
                        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*()\-_=+\\|[\]{};:'",.<>/?])/,
                        message:
                        "영문 / 숫자 / 특수문자가 반드시 포함되어야 합니다",
                      },
                    }}
                    defaultValue=""
                    render={({ field }) => (
                      <Form.Control
                      type="password"
                      placeholder="비밀번호를 입력해 주세요"
                      {...field}
                      />
                      )}
                      />
                </div>

            </div>
          </Card.Body>
        )}
        <Card.Footer>
            <Button variant="primary" type="submit">
              Sign in
            </Button>
          <Button variant="secondary" 
          onClick={() => {
            handleShow();
            setClickvalue(`signup`);
          }}>
            회원가입
          </Button>
        </Card.Footer>
      </Form>
      
      <Modal show={show} onHide={handleClose}>
        <ActiveComponent onHide={handleClose} />
      </Modal>
    </Card>
    </>
  );
};

export default SignIn;

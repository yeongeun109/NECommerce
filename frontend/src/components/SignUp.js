import React, { useRef, useState } from "react";
import axios from "axios";
import { Button, Form, Modal, InputGroup } from "react-bootstrap";
import { useForm, Controller } from "react-hook-form";

import "./SignUp.css";

const SignUp = (props) => {
  // const [checkedId, setCheckedId] = useState("");
  // const [isChecked, setIsChecked] = useState(false);
  // const [available, setAvailable] = useState(false);
  const {
    trigger,
    watch,
    handleSubmit,
    control,
    formState: { errors },
  } = useForm();

  const email = useRef();
  const password = useRef();
  const passwordConfirm = useRef();

  email.current = watch("email", "");
  password.current = watch("password", "");
  passwordConfirm.current = watch("passwordConfirm", "");

  // const checkId = async () => {
  //   setIsChecked(true);
  //   if (id.current !== "") {
  //     await axios
  //       .get(
  //         `${process.env.REACT_APP_SERVER_BASE_URL}/api/user/signup/${id.current}`
  //       )
  //       .then((response) => {
  //         if (response.data === "Can use this Id") {
  //           setCheckedId(id.current);
  //           setAvailable(true);
  //         } else if (response.data === "Existing User Id") {
  //           setAvailable(false);
  //         }
  //         setCheckedId(id.current);
  //       })
  //       .catch(() => {
  //         setAvailable(false);
  //       });
  //   }

  // if (id.current === "123") {
  //   setCheckedId(id.current);

  //   setAvailable(true);
  // } else {
  //   setAvailable(false);
  // }
  // };

  const signup = () => {
    axios
      .post("api/v1/users/register", {
        email: watch("email", ""),
        name: watch("name", ""),
        password: watch("password", ""),
      })
      .then(() => {
        alert("회원 가입에 성공하였습니다.");
        props.onHide();
      })
      .catch(() => {
        alert("회원 가입 실패하였습니다.");
      });
  };

  return (
    <>
      <Modal.Header>
        <Modal.Title>회원 가입</Modal.Title>
      </Modal.Header>

      <Form onSubmit={handleSubmit(signup)}>
        <Modal.Body>
          <Form.Group>
            <div className="input-box">
              <Form.Label>Email {errors.email && <span>{errors.email.message}</span>}</Form.Label>

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
                  <Form.Control placeholder="Email을 입력해 주세요" {...field} />
                )}
              />
            </div>
            <div className="input-box">
              <Form.Label>
                비밀번호
                {errors.password && <span>{errors.password.message}</span>}
              </Form.Label>

              <Controller
                name="password"
                control={control}
                rules={{
                  required: { value: true, message: "필수 항목입니다" },

                  pattern: {
                    value: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*\-_=+\\|;:'",./?])/,
                    message: "영문 / 숫자 / 특수문자가 반드시 포함되어야 합니다",
                  },
                }}
                defaultValue=""
                render={({ field }) => (
                  <Form.Control type="password" placeholder="비밀번호를 입력해 주세요" {...field} />
                )}
              />
            </div>

            <div className="input-box">
              <Form.Label>
                비밀번호 확인
                {password.current === passwordConfirm.current ? (
                  <></>
                ) : (
                  <span>비밀번호가 일치하지 않습니다</span>
                )}
              </Form.Label>
              <Controller
                name="passwordConfirm"
                control={control}
                rules={{
                  required: true,
                  validate: (value) => value === watch("password", ""),
                }}
                defaultValue=""
                render={({ field }) => (
                  <Form.Control
                    type="password"
                    placeholder="비밀번호를 다시 입력해 주세요."
                    {...field}
                  />
                )}
              />
            </div>
            <div className="input-box">
              <Form.Label>이름 {errors.name && <span>{errors.name.message}</span>}</Form.Label>

              <Controller
                name="name"
                control={control}
                rules={{
                  required: { value: true, message: "필수 항목입니다" },
                  maxLength: {
                    value: 30,
                    message: "30글자 이내로 작성해 주세요",
                  },
                }}
                render={({ field }) => (
                  <Form.Control placeholder="이름을 입력해 주세요" {...field} />
                )}
              />
            </div>
          </Form.Group>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" type="submit">
            Sign Up
          </Button>
          <Button variant="secondary" onClick={props.onHide}>
            Close
          </Button>
        </Modal.Footer>
      </Form>
    </>
  );
};

export default SignUp;

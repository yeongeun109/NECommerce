import React, {useState, useRef} from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import { useForm, Controller } from "react-hook-form";
import MDEditor from "@uiw/react-md-editor";
import { faImage } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const MintingNFT = ({history}) => {
    const [imgFile, setImgFile] = useState("");
    const [imgBase64, setImgBase64] = useState(""); // 파일 base64
    const categoryValue = useRef();
    const [value, setValue] = useState();
    const introValue = useRef()
    const {
        watch,
        handleSubmit,
        control,
        formState: { errors },
    } = useForm();
    
    const onClickDelete = () => {
        setImgBase64("");
        setImgFile("");
    };
    
    const handleChangeFile = (event) => {
        let reader = new FileReader();

        reader.onloadend = () => {
            const base64 = reader.result;
            if (base64) {
                setImgBase64(base64);
            }
        };

        if (event.target.files[0]) {
        reader.readAsDataURL(event.target.files[0]);
        setImgFile(event.target.files[0]);
        }
    };

    const onSubmit = () => {
        const formData = new FormData();
        formData.append("category", categoryValue.current.value);
        formData.append("nftName", watch("name", ""));
        formData.append("thumbnail", imgFile);
        formData.append("price", watch("price", ""));
        axios
          .post(
            '/api/nft/create',
            formData,
            {
              headers: {
                Authorization:
                   JSON.parse(window.localStorage.getItem("token")),
                "Content-Type": `multipart/form-data`,
              },
            }
          )
          .then((response) => {
            alert("상품 등록에 성공하였습니다");
            history.push(`/detail/${response.data}`);
            //response.data = code값
          })
          .catch((error) => {
            alert("상품을 등록하지 못햇습니다");
          });
      };
    
  
    return(
        <div>
            <div className="title">
                <h2>상품 등록</h2>
            </div>
            <Form onSubmit={handleSubmit(onSubmit)}>
                <div className="nft-detail">
                    <div className="content">
                        <div className="input-box">
                            <Controller
                                name="name"
                                control={control}
                                defaultValue=""
                                rules={{
                                required: { value: true, message: "필수 항목입니다" },
                                }}
                                render={({ field }) => (
                                    <div>
                                        <h4>
                                        상품 이름
                                        {errors.title && <span>{errors.title.message}</span>}
                                        </h4>

                                        <Form.Control
                                        type="text"
                                        placeholder="상품의 이름을 입력하세요"
                                        {...field}
                                        />
                                    </div>
                                )}
                            />
                        </div>
                        <div className="input-box">
                            <Controller
                                name="price"
                                control={control}
                                defaultValue=""
                                rules={{
                                required: { value: true, message: "필수 항목입니다" },
                                }}
                                render={({ field }) => (
                                <div>
                                    <h4>
                                    가격
                                    {errors.price && <span>{errors.price.message}</span>}
                                    </h4>

                                    <Form.Control
                                    type="number"
                                    placeholder="상품의 가격을 입력해주세요"
                                    {...field}
                                    />
                                </div>
                                )}
                            />
                        </div>

                        <div className="input-box thumbnail">
                            <h4>상품 이미지</h4>
                            <div className="input-box-thumbnail-content">
                                <div
                                    className={
                                    imgBase64 === "" ? "default-image" : "image-preview"
                                    }
                                >
                                    {imgBase64 === "" ? (
                                    <>
                                        <FontAwesomeIcon
                                        className={
                                            imgBase64 === "" ? "before-icon" : "after-icon"
                                        }
                                        icon={faImage}
                                        size="10x"
                                        />
                                    </>
                                    ) : (
                                    <>
                                        <img src={imgBase64} alt="img" />
                                    </>
                                    )}
                                </div>
                                <div>
                                    <h5>이미지를 업로드 해주세요</h5>

                                    {imgBase64 === "" ? (
                                    <>
                                        <label htmlFor="imgFile" className="btn btn-primary ">
                                        사진 업로드
                                        </label>
                                        <input
                                        type="file"
                                        className="image-upload"
                                        accept="image/*"
                                        name="imgFile"
                                        id="imgFile"
                                        onChange={handleChangeFile}
                                        />
                                    </>
                                    ) : (
                                    <label
                                        onClick={onClickDelete}
                                        className="btn btn-primary "
                                    >
                                        이미지 제거
                                    </label>
                                    )}
                                </div>
                            </div>
                        </div>

                        <div className="input-box">
                            <h4>상품 설명</h4>

                            <Form.Group>
                                <Form.Control type="text" rows={3} ref={introValue} />
                            </Form.Group>
                        </div>
                        <div>

                        <Form.Control
                        as="select"
                        custom
                        ref={categoryValue}>
                            <option>Check the Category</option>
                            <option value="1">ART</option>
                            <option value="2">PHOTO</option>
                        </Form.Control>
                        </div>

                    
                    <Button type="submit" style={{ marginTop: "15px", float: "right" }}>
                        상품등록
                    </Button>
                </div>
            </div>
            </Form>

        </div>
    )
};

export default MintingNFT
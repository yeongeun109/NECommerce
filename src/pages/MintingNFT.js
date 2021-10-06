import React, {useState, useRef} from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import { useForm, Controller } from "react-hook-form";
// import MDEditor from "@uiw/react-md-editor";
import { faImage } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import TransactNFT from "../assets/scripts/mint-nft";

import { uploadFile } from 'react-s3';
import GetUserPK from "../assets/GetUserPK";


const MintingNFT = ({history}) => {
    const [imgFile, setImgFile] = useState("");
    const [imgBase64, setImgBase64] = useState(""); // 파일 base64
    const [obj, setObj] = useState({ });
    // let obj = TransactNFT(nftName, imgURI, intro, category);
    const categoryValue = useRef();
    // const [value, setValue] = useState();
    const introValue = useRef()
    const [imageURL, setImageURL] = useState("")
    const userPK = GetUserPK()
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
    const S3_BUCKET = process.env.REACT_APP_S3_BUCKET;
    const REGION = process.env.REACT_APP_REGION;
    const ACCESS_KEY = process.env.REACT_APP_ACCESS_KEY;
    const SECRET_ACCESS_KEY = process.env.REACT_APP_SECRET_ACCESS_KEY;
    // const EXAMPLE = process.env.REACT_APP_EXAMPLE;
    const config = {
        bucketName: S3_BUCKET,
        region: REGION,
        accessKeyId: ACCESS_KEY,
        secretAccessKey: SECRET_ACCESS_KEY
    }

    const handleUpload = async () => {
        // console.log(file)
        uploadFile(imgFile, config)
            .then(data => {
                
                setImageURL(data.location)
                console.log(data.location)
                onSubmit(data.location)
            })

            .catch(error => console.error(error))
    }

    const onSubmit = (imgURI) => {
        const formData = new FormData();
        const nftName = watch("nftName", "")
        
        const intro = introValue.current.value
        const category = categoryValue.current.value
        // formData.append("category", categoryValue.current.value);
        // formData.append("nftName", watch("name", ""));
        // formData.append("thumbnail", imgFile);
        // formData.append("price", watch("price", ""));
        // formData.append("URI", watch("tokenURI", ""))

        const obj = TransactNFT(nftName, imgURI, intro, category) // 상품명, 상품 이미지 URI, 상품설명, 상품 카테고리
        // .then((respons) => {
        //     console.log(respons)
        // })
        if(obj) history.push(`/MyPage`);
      }

    return(
        <div>
            <div className="title">
                <h2>NFT 생성</h2>
            </div>
            <Form onSubmit={handleSubmit(handleUpload)}>
                <div className="nft-detail">
                    <div className="content">
                        <div className="input-box">
                            <Controller

                                name="nftName"
                                control={control}
                                defaultValue=""
                                rules={{
                                required: { value: true, message: "필수 항목입니다" },
                                }}
                                render={({ field }) => (
                                    <div>
                                        <h4>
                                        상품명
                                        {errors.title && <span>{errors.title.message}</span>}
                                        </h4>

                                        <Form.Control
                                        type="text"
                                        placeholder="상품명을 입력해주세요"

                                        {...field}
                                        />
                                    </div>
                                )}
                            />
                        </div>

                        <div className="input-box thumbnail">
                            <h4> 이미지</h4>
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
                            <option value="0">ART</option>
                            <option value="1">PHOTO</option>
                        </Form.Control>
                        </div>

                    
                        <div>
                            <Button type="submit">Upload</Button>
                        </div>
                </div>
            </div>
            </Form>

        </div>
    )
};

export default MintingNFT
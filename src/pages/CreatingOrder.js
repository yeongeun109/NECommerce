import React, {useState, useEffect} from "react";
import {Button, Form} from "react-bootstrap";
import axios from "axios";
import {useForm, Controller} from "react-hook-form";
// import MDEditor from "@uiw/react-md-editor";
import GetUserPK from "../assets/GetUserPK";
import {useParams} from "react-router";

const CreatingOrder = ({history}) => {

    const {
        watch,
        handleSubmit,
        control,
        formState: {errors},
    } = useForm();
    const [NFT, setNFT] = useState([]);
    const [selectedNFT, setSelectedNFT] = useState({})
    const userPK = GetUserPK()
    const {NFTId} = useParams()
    const [imageURL, setImageURL] = useState("")
    const prices = watch("price", "")
    const formData = {nftId: NFTId, price: prices, userId: userPK}
    useEffect(() => {
        const fetchData = async () => {
            const result = await axios.post(
                `api/v1/nft/detail/${NFTId}`,
                {token: window.localStorage.getItem("token")}
            );
            console.log(result)
            setNFT(result.data.success);
        };
        fetchData();
    }, []);

    const onSubmit = () => {
        axios
            .post(
                'api/v1/product/register',
                formData
            )
            .then((response) => {
                alert("상품 등록에 성공하였습니다");
                history.push('/');
                //response.data = NFT의 ID값
            })
            .catch((error) => {
                alert("상품을 등록하지 못햇습니다");
            })
    };
    const wrapper = {
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center"
    }

    const textcenter = {
        textAlign : "center"
    }

    return (
        <div style={wrapper}>
            <div>
                <div className="title pb-3" style={textcenter}>
                    <h2>판매 등록</h2>
                </div>

                <div className="input-box thumbnail pb-3">
                    <h4> 이미지</h4>
                    <div className="input-box-thumbnail-content">
                        <img src={NFT.imageUrl} alt="img"/>
                    </div>
                </div>

                <Form onSubmit={handleSubmit(onSubmit)}>
                    <div className="nft-detail pt-3">
                        <div className="content">
                            <>
                                <div className="input-box">
                                    <Controller
                                        name="price"
                                        control={control}
                                        defaultValue=""
                                        rules={{
                                            required: {value: true, message: "필수 항목입니다"},
                                        }}
                                        render={({field}) => (
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
                                <div>
                                    <Button type="submit" style={{marginTop: "15px", float: "right"}}>
                                        판매등록
                                    </Button>
                                </div>
                            </>
                        </div>
                    </div>
                </Form>
            </div>
            {/*)*/}

        </div>
    )
};

export default CreatingOrder
import React, {useState, useRef, useEffect} from "react";
import { Button, Form, Image } from "react-bootstrap";
import axios from "axios";
import { useForm, Controller, set } from "react-hook-form";
// import MDEditor from "@uiw/react-md-editor";
import GetUserPK from "../assets/GetUserPK";

const SellingNFT = ({history}, props) => {
    const [NFTs, setNFTs] = useState([]);
    const [NFTData, setNFTData] = useState({});
    const [loading, setLoading] = useState(true);
    const introValue = useRef()
    const userPK = GetUserPK()
    useEffect(() => {
        const fetchData = async () => {
            const result = await axios.get(
            `api/v1/nft/list/${userPK}`,
          );
          setNFTs(result.data);
          setLoading(false);
        };
    
        fetchData();
      }, []);
    
    const {
        watch,
        handleSubmit,
        control,
        formState: { errors },
    } = useForm();

    const selectNFT = (NFT) => {
        setNFTData(NFT)
        return <option>{NFT.Title}</option>
    }

    const onSubmit = () => {
        const formData = new FormData();
        formData.append("seller_id", userPK);
        formData.append("NFTId", NFTData.NFTid)
        formData.append("price", watch("price", ""));
        
        axios
          .post(
            'api/v1/nft/sell',
            formData,
          )
          .then((response) => {
            alert("상품 등록에 성공하였습니다");
            history.push(`/detail/${response.data}`);
            //response.data = NFT의 ID값
          })
          .catch((error) => {
            alert("상품을 등록하지 못햇습니다");
          })
    };
    
  
    return(
        <div>
            {loading ? (
                <div>Loading...</div>
            )
            : (
            <div>
        
                <div className="title">
                    <h2>판매 등록</h2>
                </div>
                <Form onSubmit={handleSubmit(onSubmit)}>
                    <div className="nft-detail">
                        <div className="content">
                            <div className="select-box">
                                <Form.Select>
                                    {NFTs.map(selectNFT)}
                                </Form.Select>
                            </div>
                            {NFTData === "" ? 
                            (<></>)
                            :
                            (
                            <>
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
                            <h4> 이미지</h4>
                            <div className="input-box-thumbnail-content">
                                <Image src={NFTData.ImageUrl} alt="img" />        
                            </div>
                        </div>
                        
                        <div>
                            <Button type="submit" style={{ marginTop: "15px", float: "right" }}>
                                판매등록
                            </Button>
                        </div>
                        </>)
                        }
                            
                        </div>
                    </div>
                </Form>
            </div>)}

        </div>
    )
};

export default SellingNFT
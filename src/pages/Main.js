import React, {useState} from "react";
import { Form, InputGroup  } from "react-bootstrap";
import NFTList from "../components/NFTList";
import {Icon} from "semantic-ui-react"
import "./Main.css"
const Main = () => {
    const [searchText, setSearchText] = useState("");
    const [searchCategory, setSearchCategory] = useState("");
    const onChangeInput = (event) => {
      setSearchText(event.target.value);
    };
    const onClickCategory = (event) => {
      if (event.target.id === "전체") {
        setSearchCategory("");
      } else {
        setSearchCategory(event.target.id);
      }
    };
  

    return(
        <div>
            <div>
                <div className="main-explore">

                <InputGroup id="main-search" className="mb-5">
                    <Form.Control
                    placeholder=""
                    value={searchText}
                    onChange={onChangeInput}
                    />

                </InputGroup>
                    
                <Icon name='search plus' color='green' fitted/>
                    
                <div className="main-explore-option justify-content-end">
                    <div className="btn-group mt-3 mb-3">
                        <input
                            className="btn-check btn-info"
                            autoComplete="off"
                            onClick={onClickCategory}
                            id="전체"
                        />
                        <label className="btn fs-4" htmlFor="전체">
                            전체
                        </label>

                        <input
                            className="btn-check btn-dark"
                            autoComplete="off"
                            onClick={onClickCategory}
                            id="Art"
                        />
                        <label className="btn fs-4" htmlFor="Art">
                            ART
                        </label>
                        <input
                            className="btn-check btn-light"
                            autoComplete="off"
                            onClick={onClickCategory}
                            id="Photo"
                        />
                        <label className="btn fs-4" htmlFor="Photo">
                            Photo
                        </label>
                    
                    </div>
                </div>
                </div>

                <NFTList searchText={searchText} searchCategory={searchCategory} />
            </div>
        </div>
    )
}

export default Main
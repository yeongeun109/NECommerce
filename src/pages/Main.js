import React, { useState } from "react";
import { Form, InputGroup } from "react-bootstrap";
import NFTList from "../components/NFTList";
import { Icon } from "semantic-ui-react";
import "./Main.css";
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

  return (
    <div>
      <div>
        <div className="main-explore m-5">
          <InputGroup id="main-search" className="mb-5">
            <Form.Control
              placeholder="제목 또는 소유자를 검색하세요."
              value={searchText}
              onChange={onChangeInput}
            />
          </InputGroup>

          <div className="main-explore-option justify-content-end">
            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
              <input
                type="radio"
                class="btn-check"
                name="btnradio"
                id="전체"
                autocomplete="off"
                onClick={onClickCategory}
                checked=""
              />
              <label class="btn btn-outline-primary" for="전체">
                전체
              </label>
              <input
                type="radio"
                class="btn-check"
                name="btnradio"
                id="Art"
                autocomplete="off"
                onClick={onClickCategory}
                checked=""
              />
              <label class="btn btn-outline-primary" for="Art">
                Art
              </label>
              <input
                type="radio"
                class="btn-check"
                name="btnradio"
                id="Photo"
                autocomplete="off"
                onClick={onClickCategory}
                checked=""
              />
              <label class="btn btn-outline-primary" for="Photo">
                Photo
              </label>
            </div>
          </div>
        </div>

        <NFTList searchText={searchText} searchCategory={searchCategory} />
      </div>
    </div>
  );
};

export default Main;

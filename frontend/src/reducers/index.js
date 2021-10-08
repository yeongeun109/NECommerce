import currentUser from "./userReducers";
import isEnter from "./confirmReducers";
import scrollDown from "./headerReducers";
import { combineReducers } from "redux";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["currentUser"],
};

const rootReducer = combineReducers({
  currentUser,
  isEnter,
  scrollDown,
});

export default persistReducer(persistConfig, rootReducer);

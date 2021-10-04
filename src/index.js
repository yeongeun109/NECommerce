import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import { Provider } from "react-redux";
import { persistStore } from "redux-persist";
import { PersistGate } from "redux-persist/integration/react";
import { createStore } from "redux";
import rootReducer from "./reducers";
import { composeWithDevTools } from "redux-devtools-extension";
import "./index.css";
import "./bootswatch/Minty.css";
import { MetaMaskProvider } from "metamask-react";

const store = createStore(rootReducer, composeWithDevTools());
const persistor = persistStore(store);

ReactDOM.render(
      <Provider store={store}>
        <PersistGate loading={null} persistor={persistor}>
          <React.StrictMode>
            <MetaMaskProvider>  
          <App />
            </MetaMaskProvider>
          </React.StrictMode>
        </PersistGate>
      </Provider>,
  document.getElementById("root")
);

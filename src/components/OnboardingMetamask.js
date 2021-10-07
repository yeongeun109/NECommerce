import React, { useEffect, useRef, useState } from "react";
import MetaMaskOnboarding from "@metamask/onboarding";

const ONBOARD_TEXT = "여기를 눌러 메타마스크를 설치해주세요";
const CONNECT_TEXT = "연결이 완료되면 눌러주세요";
const CONNECTED_TEXT = "연결이 완료되었습니다";

const OnboardingButton = () => {
  const [buttonText, setButtonText] = useState(ONBOARD_TEXT);
  const [isDisabled, setDisabled] = useState(false);
  const [accounts, setAccounts] = useState([]);
  const [status, setStatus] = useState("info");
  const onboarding = useRef();
  const statusClass = "btn-" + status;

  useEffect(() => {
    if (!onboarding.current) {
      onboarding.current = new MetaMaskOnboarding();
    }
  }, []);

  useEffect(() => {
    if (MetaMaskOnboarding.isMetaMaskInstalled()) {
      if (accounts.length > 0) {
        setButtonText(CONNECTED_TEXT);
        setDisabled(true);
        setStatus("danger");
        onboarding.current.stopOnboarding();
      } else {
        setButtonText(CONNECT_TEXT);
        setStatus("warning");
        setDisabled(false);
      }
    }
  }, [accounts]);

  useEffect(() => {
    function handleNewAccounts(newAccounts) {
      setAccounts(newAccounts);
    }

    if (MetaMaskOnboarding.isMetaMaskInstalled()) {
      window.ethereum.request({ method: "eth_requestAccounts" }).then(handleNewAccounts);
      window.ethereum.on("accountsChanged", handleNewAccounts);
      return () => {
        //window.ethereum.off('accountsChanged', handleNewAccounts);
      };
    }
  }, []);

  const onClick = () => {
    if (MetaMaskOnboarding.isMetaMaskInstalled()) {
      window.ethereum
        .request({ method: "eth_requestAccounts" })
        .then((newAccounts) => setAccounts(newAccounts))
        .then(() => window.location.reload());
    } else {
      onboarding.current.startOnboarding();
    }
  };
  return (
    <div class="d-grid gap-2 m-3">
      <button
        className={"btn btn-lg " + statusClass}
        type="button"
        disabled={isDisabled}
        onClick={onClick}
      >
        {buttonText}
      </button>
    </div>
  );
};

export default OnboardingButton;

import React, {useEffect, useRef, useState} from "react";
import MetaMaskOnboarding from '@metamask/onboarding'

const ONBOARD_TEXT = "여기를 눌러 메타마스크를 설치해주세요";
const CONNECT_TEXT = "연결중입니다";
const CONNECTED_TEXT = "연결이 완료되었습니다";

const OnboardingButton = () => {
    const [buttonText, setButtonText] = useState(ONBOARD_TEXT)
    const [isDisabled, setDisabled] = useState(false)
    const [accounts, setAccounts] = useState([]);
    const onboarding = useRef();

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
                onboarding.current.stopOnboarding();
            } else {
                setButtonText(CONNECT_TEXT);
                setDisabled(false);
            }
        }
    }, [accounts]);

    useEffect(() => {
        function handleNewAccounts(newAccounts) {
            setAccounts(newAccounts);
        }

        if (MetaMaskOnboarding.isMetaMaskInstalled()) {
            window.ethereum
                .request({method: 'eth_requestAccounts'})
                .then(handleNewAccounts);
            window.ethereum.on('accountsChanged', handleNewAccounts);
            return () => {
                window.ethereum.off('accountsChanged', handleNewAccounts);
            };
        }
    }, []);

    const onClick = () => {
        if (MetaMaskOnboarding.isMetaMaskInstalled()) {
            window.ethereum
                .request({method: 'eth_requestAccounts'})
                .then((newAccounts) => setAccounts(newAccounts));
        } else {
            onboarding.current.startOnboarding();
        }
    };
    return (
        <button disabled={isDisabled} onClick={onClick}>
            {buttonText}
        </button>
    );
}

export default OnboardingButton
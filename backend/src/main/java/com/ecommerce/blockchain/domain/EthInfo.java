package com.ecommerce.blockchain.domain;

public class EthInfo {
    private String ethUrl;
    private String latestBlockNumber;

    public EthInfo() {
    }

    public EthInfo(String ethUrl, String latestBlockNumber) {
        this.ethUrl = ethUrl;
        this.latestBlockNumber = latestBlockNumber;
    }

    public String getEthUrl() {
        return ethUrl;
    }

    public void setEthUrl(String ethUrl) {
        this.ethUrl = ethUrl;
    }

    public String getLatestBlockNumber() {
        return latestBlockNumber;
    }

    public void setLatestBlockNumber(String latestBlockNumber) {
        this.latestBlockNumber = latestBlockNumber;
    }
}

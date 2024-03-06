
package com.verificer.biz.biz.config;

import com.verificer.base_user.config.LimitedConfig;
import com.verificer.utils.SDateUtil;
import com.verificer.utils.SStringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 35336 on 2020/12/26.
 */
@Component
@PropertySource(value = {"classpath:properties/config.properties"})
@ConfigurationProperties(prefix = "config")
public class BizConfig {

    private String platformAddress;
    private String pinataApiKey;
    private String pinataApiSecret;
    private String platformName;
    private String mintContractAddress;
    private String platformPrivateKey;
    /**
     * 注意⚠️，该配置属性返回行进行了toLowCase()
     */
    private String exContractAddress;
    private long confirmMaxSec;
    private int currencyPrecision;
    private int currencyOrigPrecision;
    private String ipfsNetProtocol;

    private boolean needRefreshChainTime;
    private String refreshChainTimePrivateKey;
    private String refreshChainTimeContractAddr;

    private String qutCurrencyContractAddr;
    private int qutCurrencyPrecision;

    private long rebateChangeCount;
    private int smallRate;
    private int BigRate;

    private boolean isRunTask;

    private String s3EndPoint;
    private String s3ApiKey;
    private String s3SecretKey;
    private String s3Region;
    private String s3BucketName;

    private int pricePrecision;

    private String bidMethodSign;
    private String createOrderMethodSign;
    private String auctionConfirmMethodSign;

    private String historyExContracts;
    private List<String> historyExContractList;
    private String expireExContracts;

    private boolean recordRepairFlag;
    private String recordMaxTimeStr;
    private Long recordMaxTime;

    private String retryPeriods;

    private String blockTimeUnit;

    private Long genBlockTime;

    private Long maxAuctionStartPrice;

    private boolean needCancelFlag;

    private boolean openSell;

    private long stopSellBlockHeight;

    private long chainConfirmCount;

    private String erc721ContractAddress;
    private String erc1155ContractAddress;

    private String ipfsDownloadApi;

    public String getPinataApiKey() {
        return pinataApiKey;
    }

    public void setPinataApiKey(String pinataApiKey) {
        this.pinataApiKey = pinataApiKey;
    }

    public String getPinataApiSecret() {
        return pinataApiSecret;
    }

    public void setPinataApiSecret(String pinataApiSecret) {
        this.pinataApiSecret = pinataApiSecret;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getMintContractAddress() {
        return mintContractAddress;
    }

    public void setMintContractAddress(String mintContractAddress) {
        this.mintContractAddress = mintContractAddress;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public void setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
    }

    public String getPlatformPrivateKey() {
        return platformPrivateKey;
    }

    public void setPlatformPrivateKey(String platformPrivateKey) {
        this.platformPrivateKey = platformPrivateKey;
    }

    public String getExContractAddress() {
        return exContractAddress.toLowerCase();
    }

    public void setExContractAddress(String exContractAddress) {
        this.exContractAddress = exContractAddress;
    }

    public long getConfirmMaxSec() {
        return confirmMaxSec;
    }

    public void setConfirmMaxSec(long confirmMaxSec) {
        this.confirmMaxSec = confirmMaxSec;
    }

    public int getCurrencyPrecision() {
        return currencyPrecision;
    }

    public void setCurrencyPrecision(int currencyPrecision) {
        this.currencyPrecision = currencyPrecision;
    }

    public String getIpfsNetProtocol() {
        return ipfsNetProtocol;
    }

    public void setIpfsNetProtocol(String ipfsNetProtocol) {
        this.ipfsNetProtocol = ipfsNetProtocol;
    }

    public int getCurrencyOrigPrecision() {
        return currencyOrigPrecision;
    }

    public void setCurrencyOrigPrecision(int currencyOrigPrecision) {
        this.currencyOrigPrecision = currencyOrigPrecision;
    }

    public boolean isNeedRefreshChainTime() {
        return needRefreshChainTime;
    }

    public void setNeedRefreshChainTime(boolean needRefreshChainTime) {
        this.needRefreshChainTime = needRefreshChainTime;
    }

    public String getRefreshChainTimePrivateKey() {
        return refreshChainTimePrivateKey;
    }

    public void setRefreshChainTimePrivateKey(String refreshChainTimePrivateKey) {
        this.refreshChainTimePrivateKey = refreshChainTimePrivateKey;
    }

    public String getRefreshChainTimeContractAddr() {
        return refreshChainTimeContractAddr;
    }

    public void setRefreshChainTimeContractAddr(String refreshChainTimeContractAddr) {
        this.refreshChainTimeContractAddr = refreshChainTimeContractAddr;
    }

    public String getQutCurrencyContractAddr() {
        return qutCurrencyContractAddr;
    }

    public void setQutCurrencyContractAddr(String qutCurrencyContractAddr) {
        this.qutCurrencyContractAddr = qutCurrencyContractAddr;
    }

    public int getQutCurrencyPrecision() {
        return qutCurrencyPrecision;
    }

    public void setQutCurrencyPrecision(int qutCurrencyPrecision) {
        this.qutCurrencyPrecision = qutCurrencyPrecision;
    }

    public long getRebateChangeCount() {
        return rebateChangeCount;
    }

    public void setRebateChangeCount(long rebateChangeCount) {
        this.rebateChangeCount = rebateChangeCount;
    }

    public int getSmallRate() {
        return smallRate;
    }

    public void setSmallRate(int smallRate) {
        this.smallRate = smallRate;
    }

    public int getBigRate() {
        return BigRate;
    }

    public void setBigRate(int bigRate) {
        BigRate = bigRate;
    }

    public boolean isRunTask() {
        return isRunTask;
    }

    public void setRunTask(boolean runTask) {
        isRunTask = runTask;
    }

    public String getS3EndPoint() {
        return s3EndPoint;
    }

    public void setS3EndPoint(String s3EndPoint) {
        this.s3EndPoint = s3EndPoint;
    }

    public String getS3ApiKey() {
        return s3ApiKey;
    }

    public void setS3ApiKey(String s3ApiKey) {
        this.s3ApiKey = s3ApiKey;
    }

    public String getS3SecretKey() {
        return s3SecretKey;
    }

    public void setS3SecretKey(String s3SecretKey) {
        this.s3SecretKey = s3SecretKey;
    }

    public String getS3Region() {
        return s3Region;
    }

    public void setS3Region(String s3Region) {
        this.s3Region = s3Region;
    }

    public String getS3BucketName() {
        return s3BucketName;
    }

    public void setS3BucketName(String s3BucketName) {
        this.s3BucketName = s3BucketName;
    }

    public int getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(int pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public String getBidMethodSign() {
        return bidMethodSign;
    }

    public void setBidMethodSign(String bidMethodSign) {
        this.bidMethodSign = bidMethodSign;
    }

    public String getCreateOrderMethodSign() {
        return createOrderMethodSign;
    }

    public void setCreateOrderMethodSign(String createOrderMethodSign) {
        this.createOrderMethodSign = createOrderMethodSign;
    }

    public String getAuctionConfirmMethodSign() {
        return auctionConfirmMethodSign;
    }

    public void setAuctionConfirmMethodSign(String auctionConfirmMethodSign) {
        this.auctionConfirmMethodSign = auctionConfirmMethodSign;
    }

    public String getHistoryExContracts() {
        return historyExContracts;
    }

    public void setHistoryExContracts(String historyExContracts) {
        this.historyExContracts = historyExContracts;
        this.historyExContractList =  SStringUtils.split(historyExContracts,",");
    }

    public boolean isRecordRepairFlag() {
        return recordRepairFlag;
    }

    public void setRecordRepairFlag(boolean recordRepairFlag) {
        this.recordRepairFlag = recordRepairFlag;
    }

    public String getRecordMaxTimeStr() {
        return recordMaxTimeStr;
    }

    public void setRecordMaxTimeStr(String recordMaxTimeStr) {
        this.recordMaxTimeStr = recordMaxTimeStr;
        if(this.isRecordRepairFlag()){
            try {
                this.recordMaxTime = SDateUtil.parse(recordMaxTimeStr,"yyyy-MM-dd HH:mm:ss").getTime();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("配置错误，请检查recordMaxTimeStr配置项");
                System.exit(-1);
            }

        }
    }

    public Long getRecordMaxTime() {
        return recordMaxTime;
    }


    public String getExpireExContracts() {
        return expireExContracts;
    }

    public void setExpireExContracts(String expireExContracts) {
        this.expireExContracts = expireExContracts;
    }

    public void setRecordMaxTime(Long recordMaxTime) {
        this.recordMaxTime = recordMaxTime;
    }

    public String getRetryPeriods() {
        return retryPeriods;
    }

    public void setRetryPeriods(String retryPeriods) {
        this.retryPeriods = retryPeriods;
    }

    public List<String> getHistoryExContractList() {
        return historyExContractList;
    }

    public boolean isSupportedExContract(String contractAddr){
        for(String addr : historyExContractList){
            if(addr.equalsIgnoreCase(contractAddr))
                return true;
        }

        return false;
    }


    public String getBlockTimeUnit() {
        return blockTimeUnit;
    }

    public void setBlockTimeUnit(String blockTimeUnit) {
        this.blockTimeUnit = blockTimeUnit;
    }

    public void setHistoryExContractList(List<String> historyExContractList) {
        this.historyExContractList = historyExContractList;
    }



    public Long getGenBlockTimeMs() {
        return SDateUtil.parseUnit(genBlockTime+blockTimeUnit);
    }

    public void setGenBlockTime(Long genBlockTime) {
        this.genBlockTime = genBlockTime;
    }

    public Long getMaxAuctionStartPrice() {
        return maxAuctionStartPrice;
    }

    public void setMaxAuctionStartPrice(Long maxAuctionStartPrice) {
        this.maxAuctionStartPrice = maxAuctionStartPrice;
    }

    public boolean isNeedCancelFlag() {
        return needCancelFlag;
    }

    public void setNeedCancelFlag(boolean needCancelFlag) {
        this.needCancelFlag = needCancelFlag;
    }

    public boolean isOpenSell() {
        return openSell;
    }

    public void setOpenSell(boolean openSell) {
        this.openSell = openSell;
    }

    public long getStopSellBlockHeight() {
        return stopSellBlockHeight;
    }

    public void setStopSellBlockHeight(long stopSellBlockHeight) {
        this.stopSellBlockHeight = stopSellBlockHeight;
    }

    public long getChainConfirmCount() {
        return chainConfirmCount;
    }

    public void setChainConfirmCount(long chainConfirmCount) {
        this.chainConfirmCount = chainConfirmCount;
    }

    public String getErc721ContractAddress() {
        return erc721ContractAddress;
    }

    public void setErc721ContractAddress(String erc721ContractAddress) {
        this.erc721ContractAddress = erc721ContractAddress;
    }

    public String getErc1155ContractAddress() {
        return erc1155ContractAddress;
    }

    public void setErc1155ContractAddress(String erc1155ContractAddress) {
        this.erc1155ContractAddress = erc1155ContractAddress;
    }

    public String getIpfsDownloadApi() {
        return ipfsDownloadApi;
    }

    public void setIpfsDownloadApi(String ipfsDownloadApi) {
        this.ipfsDownloadApi = ipfsDownloadApi;
    }
}

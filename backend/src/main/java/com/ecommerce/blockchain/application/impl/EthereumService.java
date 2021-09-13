package com.ecommerce.blockchain.application.impl;

import com.ecommerce.blockchain.application.IEthereumService;
import com.ecommerce.blockchain.domain.Address;
import com.ecommerce.blockchain.domain.repository.ITransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import java.math.BigInteger;

@Service
public class EthereumService implements IEthereumService {

	private static final Logger log = LoggerFactory.getLogger(EthereumService.class);

	public static final BigInteger GAS_PRICE = BigInteger.valueOf(1L);
	public static final BigInteger GAS_LIMIT = BigInteger.valueOf(21_000L);

	// 사용할 이더리움 지갑의 주소
	@Value("${eth.admin.address}")
	private String ADMIN_ADDRESS;
	// 지갑의 암호화된 패스워드
	@Value("${eth.encrypted.password}")
	private String PASSWORD;
	// 사용할 이더리움 지갑의 키스토어
	@Value("${eth.admin.wallet.filename}")
	private String ADMIN_WALLET_FILE;

	private ITransactionRepository transactionRepository;

	@Autowired
	private Web3j web3j;

//	@Autowired
//	public EthereumService(ITransactionRepository transactionRepository) {
//		this.transactionRepository = transactionRepository;
//	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * 이더리움으로부터 해당 주소의 잔액을 조회한다.
	 * @param address
	 * @return BigInteger
	 */
	@Override
	public BigInteger getBalance(String address){
		return null;
	}

	/**
	 * TODO Sub PJT Ⅱ 과제 1
	 * [주소]로 시스템에서 정한 양 만큼 이더를 송금한다.
	 * 이더를 송금하는 트랜잭션을 생성, 전송한 후 결과인
	 * String형의 트랜잭션 hash 값을 반환한다.
	 * @param address
	 * @return String 생성된 트랜잭션의 hash 반환 (참고, TransactionReceipt)
	 */
	@Override
	public String requestEth(final String address) // 특정 주소로 테스트 특정 양(5Eth) 만큼 충전해준다.
	{
		return null;
	}

	/**
	 * TODO Sub PJT Ⅲ 추가과제
	 * 이더리움으로부터 해당 주소의 잔액을 조회하고
	 * 동기화한 트랜잭션 테이블로부터 Address 정보의 trans 필드를 완성하여
	 * 정보를 반환한다.
	 * @param addr
	 * @return Address
	 */
	@Override
	public Address getAddress(String addr){
		return null;
	}

}

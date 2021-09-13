package com.ecommerce.blockchain.application;

import com.ecommerce.blockchain.domain.Wallet;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * TODO Sub PJT Ⅱ 과제 1, 과제 3
 * 과제1: 지갑 관련 기능 구현
 * 1) 지갑 등록, 2) 지갑 조회, 3) 충전
 * 과제3: 지갑 관련 기능 확장 구현
 * 1) 지갑 토큰 잔액 조회 추가
 *
 * 아래 메소드를 오버라이드하거나
 * 필요함수를 추가하여 WalletService.java에 구현합니다.
 */
public interface IWalletService
{
	Wallet get(long userId);

	@Transactional
	Wallet register(Wallet wallet);

	@Transactional
	Wallet syncBalance(String walletAddress, BigDecimal balance, int cash);

	@Transactional
	Wallet requestEth(String walletAddress);
}

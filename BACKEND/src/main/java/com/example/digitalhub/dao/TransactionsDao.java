package com.example.digitalhub.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.digitalhub.vo.AccountVO;
import com.example.digitalhub.vo.request.TransferRequestVO;

@Repository
public interface TransactionsDao {
	
	public boolean applyTransfer(TransferRequestVO transfer);
	public List<TransferRequestVO> findAll(BigInteger account);
	public List<TransferRequestVO> findFromTransactions(BigInteger account);
	public List<TransferRequestVO> findToTransactions(BigInteger account);
	public AccountVO getBalance(BigInteger account);
	
}

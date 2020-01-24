package com.example.digitalhub.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.digitalhub.dao.TransactionsDao;
import com.example.digitalhub.vo.AccountVO;
import com.example.digitalhub.vo.request.TransferRequestVO;

@Service
public class TransactionsService {
	
	@Autowired
	private TransactionsDao transactionsDao;
	
	public boolean transferMoney(TransferRequestVO request) {
		return transactionsDao.applyTransfer(request);
	}
	
	public List<TransferRequestVO> findAllTransactions(BigInteger account) {
		return transactionsDao.findAll(account);
	}
	
	public List<TransferRequestVO> findFromTransactions(BigInteger account) {
		return transactionsDao.findFromTransactions(account);
	}
	
	public List<TransferRequestVO> findToTransactions(BigInteger account) {
		return transactionsDao.findToTransactions(account);
	}
	
	public AccountVO getBalance(BigInteger account) {
		return transactionsDao.getBalance(account);
	}

}

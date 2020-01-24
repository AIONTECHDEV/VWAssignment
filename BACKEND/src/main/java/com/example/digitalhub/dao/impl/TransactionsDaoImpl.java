package com.example.digitalhub.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.digitalhub.dao.TransactionsDao;
import com.example.digitalhub.vo.AccountVO;
import com.example.digitalhub.vo.request.TransferRequestVO;

@Repository
public class TransactionsDaoImpl implements TransactionsDao {
	
	private static ConcurrentMap<BigInteger, AccountVO> accountManager = new ConcurrentHashMap<BigInteger, AccountVO>();
	private static ConcurrentMap<BigInteger, List<TransferRequestVO>> transactionsHistory = new ConcurrentHashMap<BigInteger, List<TransferRequestVO>>();
	
	public boolean applyTransfer(TransferRequestVO transfer) {
		boolean isTransactionApplied = true;
		
		AccountVO accountTo = null;
		AccountVO accountFrom = null;
		
		if( !accountManager.containsKey(transfer.getToAccount()) ) {
			accountTo = new AccountVO();
			accountTo.setAccount(transfer.getToAccount());
			accountTo.setBalance(new BigDecimal(0));
			accountTo.setCreatedAt(new Date());
			accountTo.setOwner(new BigInteger(32, new SecureRandom()));
			accountManager.put(transfer.getToAccount(), accountTo);
		} else {
			accountTo = accountManager.get(transfer.getToAccount());
		}
		
		if( !accountManager.containsKey(transfer.getFromAccount()) ) {
			accountFrom = new AccountVO();
			accountFrom.setAccount(transfer.getFromAccount());
			accountFrom.setBalance(new BigDecimal(0));
			accountFrom.setCreatedAt(new Date());
			accountFrom.setOwner(new BigInteger(32, new SecureRandom()));
			accountManager.put(transfer.getFromAccount(), accountFrom);
		} else {
			accountFrom = accountManager.get(transfer.getFromAccount());
		}
		
		BigDecimal amountToTransfer = transfer.getAmount();
		
		// Check the amount is not below -500
		BigDecimal threshold = new BigDecimal(-500);
		BigDecimal newBalance = accountFrom.getBalance().subtract(amountToTransfer);
		if(newBalance.compareTo(threshold) >= 0) {
			// Add amount to account
			accountTo.setBalance(accountTo.getBalance().add(amountToTransfer));
			
			// Decrease amount from account
			accountFrom.setBalance(accountFrom.getBalance().subtract(amountToTransfer));
			
			// update accounts
			accountManager.put(accountTo.getAccount(), accountTo);
			accountManager.put(accountFrom.getAccount(), accountFrom);
			
			addTransferRequestToHistory(transfer);
		} else 
			isTransactionApplied = false;
		
		return isTransactionApplied;
	}
	
	private void addTransferRequestToHistory(TransferRequestVO transfer) {
		
		List<TransferRequestVO> accountHistoryTo = transactionsHistory.get(transfer.getToAccount());
		List<TransferRequestVO> accountHistoryFrom = transactionsHistory.get(transfer.getFromAccount());
		
		if(Objects.isNull(accountHistoryTo))
			accountHistoryTo = new ArrayList<TransferRequestVO>();
		if(Objects.isNull(accountHistoryFrom))
			accountHistoryFrom = new ArrayList<TransferRequestVO>();
		
		accountHistoryFrom.add(transfer);
		accountHistoryTo.add(transfer);
		
		transactionsHistory.put(transfer.getFromAccount(), accountHistoryFrom);
		transactionsHistory.put(transfer.getToAccount(), accountHistoryTo);
		
	}

	@Override
	public List<TransferRequestVO> findAll(BigInteger account) {
		List<TransferRequestVO> items = new ArrayList<TransferRequestVO>();
		if( transactionsHistory.containsKey(account) )
			items = transactionsHistory.get(account);
		items.sort( (TransferRequestVO o1, TransferRequestVO o2) -> o1.getSentAt().compareTo(o2.getSentAt()) );
		return items;
	}
	
	@Override
	public List<TransferRequestVO> findFromTransactions(BigInteger account) {
		List<TransferRequestVO> items = new ArrayList<TransferRequestVO>();
		if( transactionsHistory.containsKey(account) )
			items = transactionsHistory.get(account)
						.stream()
						.filter(item -> item.getFromAccount().equals(account))
						.collect(Collectors.toList());
		items.sort( (TransferRequestVO o1, TransferRequestVO o2) -> o1.getSentAt().compareTo(o2.getSentAt()) );
		return items;
	}
	
	@Override
	public List<TransferRequestVO> findToTransactions(BigInteger account) {
		List<TransferRequestVO> items = new ArrayList<TransferRequestVO>();
		if( transactionsHistory.containsKey(account) )
			items = transactionsHistory.get(account)
					.stream()
					.filter(item -> item.getToAccount().equals(account))
					.collect(Collectors.toList());
		items.sort( (TransferRequestVO o1, TransferRequestVO o2) -> o1.getSentAt().compareTo(o2.getSentAt()) );
		return items;
	}
	
	@Override
	public AccountVO getBalance(BigInteger account) {
		AccountVO theAccount = accountManager.get(account);
		return theAccount;
	}

}

package com.example.digitalhub.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.digitalhub.service.TransactionsService;
import com.example.digitalhub.vo.AccountVO;
import com.example.digitalhub.vo.request.TransferRequestVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/accounts")
@Api(value = "Transfer service", description = "Transfer amount between accounts, includes history")
public class TransferController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
	
	@Autowired
	private TransactionsService transactionsService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Transfer an amount between 2 accounts")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operation success"),
		@ApiResponse(code = 400, message = "Operation fails"),
	})
	public ResponseEntity<Object> transferMoney(@ApiParam(value = "Object containing data to apply a transaction between accounts", required = true) @RequestBody TransferRequestVO request) {
		
		Date transactionDate = new Date();
		request.setSentAt(transactionDate);
		boolean applied = transactionsService.transferMoney(request);
		logger.info("Applying transaction from " + request.getFromAccount() + " to " + request.getToAccount() + " was applied: " + applied);
		
		HttpStatus status = HttpStatus.OK;
		if(!applied)
			status = HttpStatus.BAD_REQUEST;
		
		return ResponseEntity.status(status).build();
		
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/history/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all transactions for a given account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation success"),
			@ApiResponse(code = 204, message = "No transactions found"),
			@ApiResponse(code = 400, message = "Operation fails")
		})
	public ResponseEntity<Map<String, Object>> getAllTransactions(@ApiParam(value = "Account to get transaction history", required = true) @PathVariable("account") String account) {
		
		HttpStatus status = HttpStatus.OK;
		BigInteger _account = null;
		boolean badRequest = false;
		try {
			_account = new BigInteger(account);
		} catch (NumberFormatException numberFormatException) {
			status = HttpStatus.BAD_REQUEST;
			badRequest = true;
		}
		
		logger.info("Get all account transaction history for " + _account);
		List<TransferRequestVO> history = new ArrayList<TransferRequestVO>();
		if(!badRequest)
			history = transactionsService.findAllTransactions(_account);

		Map<String, Object> response = new HashMap<String, Object>();
		if(history.isEmpty() && !badRequest)
			status = HttpStatus.NO_CONTENT;
		
		response.put("transactions", history);
		return ResponseEntity.status(status).body(response);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/history/from/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all transactions sent from a given account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation success"),
			@ApiResponse(code = 204, message = "No transactions found"),
			@ApiResponse(code = 400, message = "Operation fails")
		})
	public ResponseEntity<Map<String, Object>> getFromTransactions(@ApiParam(value = "Account to get transaction history", required = true) @PathVariable("account") String account) {
		
		HttpStatus status = HttpStatus.OK;
		BigInteger _account = null;
		boolean badRequest = false;
		try {
			_account = new BigInteger(account);
		} catch (NumberFormatException numberFormatException) {
			status = HttpStatus.BAD_REQUEST;
			badRequest = true;
		}
		
		logger.info("Get all transactions sent from account " + _account);
		List<TransferRequestVO> history = new ArrayList<TransferRequestVO>();
		if(!badRequest)
			history = transactionsService.findFromTransactions(_account);

		Map<String, Object> response = new HashMap<String, Object>();
		if(history.isEmpty() && !badRequest)
			status = HttpStatus.NO_CONTENT;
		
		response.put("transactions", history);
		return ResponseEntity.status(status).body(response);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/history/to/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all transactions sent to a given account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation success"),
			@ApiResponse(code = 204, message = "No transactions found"),
			@ApiResponse(code = 400, message = "Operation fails")
		})
	public ResponseEntity<Map<String, Object>> getToTransactions(@ApiParam(value = "Account to get transaction history", required = true) @PathVariable("account") String account) {
		
		HttpStatus status = HttpStatus.OK;
		BigInteger _account = null;
		boolean badRequest = false;
		try {
			_account = new BigInteger(account);
		} catch (NumberFormatException numberFormatException) {
			status = HttpStatus.BAD_REQUEST;
			badRequest = true;
		}
		
		logger.info("Get all transactions sent to account " + _account);
		List<TransferRequestVO> history = new ArrayList<TransferRequestVO>();
		if(!badRequest)
			history = transactionsService.findToTransactions(_account);

		Map<String, Object> response = new HashMap<String, Object>();
		if(history.isEmpty() && !badRequest)
			status = HttpStatus.NO_CONTENT;
		
		response.put("transactions", history);
		return ResponseEntity.status(status).body(response);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/balance/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get balance form a given account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Operation success"),
			@ApiResponse(code = 204, message = "No balance found"),
			@ApiResponse(code = 400, message = "Operation fails")
		})
	public ResponseEntity<Map<String, Object>> getBalance(@ApiParam(value = "Account to get balance", required = true) @PathVariable("account") String account) {
		
		HttpStatus status = HttpStatus.OK;
		BigInteger _account = null;
		boolean badRequest = false;
		try {
			_account = new BigInteger(account);
		} catch (NumberFormatException numberFormatException) {
			status = HttpStatus.BAD_REQUEST;
			badRequest = true;
		}
		
		logger.info("Get balance from account " + _account);
		AccountVO theAccount = null;
		if(!badRequest)
			theAccount = transactionsService.getBalance(_account);

		Map<String, Object> response = new HashMap<String, Object>();
		if(theAccount == null) {
			status = HttpStatus.NOT_FOUND;
			logger.info("Account " + _account + " not found");
		} else {
			response.put("balance", theAccount);
		}
		return ResponseEntity.status(status).body(response);
	}
	

}

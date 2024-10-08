package com.ducthang.ManagerUsers.controller;


import com.ducthang.ManagerUsers.dto.BankTransferDto;
import com.ducthang.ManagerUsers.service.TransactionHistoryService;
import com.ducthang.ManagerUsers.dto.BankDTO;
import com.ducthang.ManagerUsers.model.Bank;
import com.ducthang.ManagerUsers.dto.response.ResponseData;
import com.ducthang.ManagerUsers.service.BankServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BanksController {

    private final BankServices bankServices;
    private final TransactionHistoryService transactionHistoryService;

    @PostMapping("/addBank/{id}")
    public ResponseData<?> addBank(@RequestBody Bank bank, @PathVariable int id) {
        try{
            Bank bank1 = bankServices.addBank(bank,id);
            return new ResponseData<>(HttpStatus.OK.value(), "Add bank for " + id +  " Success", LocalDateTime.now(),bank1);
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @GetMapping("/getBanks")
    public ResponseData<?> getAllBanks() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "get All Banks", LocalDateTime.now(),bankServices.getBanks());
        }
        catch (Exception e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    
    @PutMapping("/updateBank/{id}")
    public ResponseData<?> updateBank(@RequestBody BankDTO bankDTO, @PathVariable int id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Update successfully",LocalDateTime.now(),bankServices.updateBank(bankDTO, id));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
    }


    @PostMapping("/transfer/{source}")
    public ResponseData<?> bankTransaction(@PathVariable String source, @RequestBody Map<String, Object> transactionDetails){
        try {
            String destination = (String) transactionDetails.get("destination");
            Number amountNumber = (Number) transactionDetails.get("amount");
            long amount = amountNumber.longValue();
            return new ResponseData<>(HttpStatus.OK.value(),String.valueOf(bankServices.bankTransaction(source,destination,amount)));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
    }

    @GetMapping("/transfer/{id}")
    public ResponseData<?> bankTransaction(@PathVariable int id) {
        return new ResponseData<>(HttpStatus.OK.value(),"Get successfully!",LocalDateTime.now(),transactionHistoryService.getTransactionHistoryByUser(id));
    }

    @GetMapping("/transaction-csv")
    public ResponseEntity<String> downloadCsv() {
        String csvData = transactionHistoryService.generateCsvData();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transaction_history.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvData);
    }

    @GetMapping("getBanks/{id}")
    public ResponseData<?> getBanks(@PathVariable int id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),"get success",LocalDateTime.now(),bankServices.getBankByUser(id));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("transfer/otherBanks")
    public ResponseData<?> transferOtherBanks(@RequestBody BankTransferDto transferDto) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Transfer success", LocalDateTime.now(), bankServices.TransferOtherBank(transferDto));
        }
        catch (RuntimeException e){
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }



}

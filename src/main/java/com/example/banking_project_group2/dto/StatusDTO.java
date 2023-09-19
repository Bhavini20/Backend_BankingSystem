package com.example.banking_project_group2.dto;

public class StatusDTO {

    private boolean status;
    private int account_no;

    public StatusDTO(boolean status, int accountNo) {
        this.status = status;
        account_no = accountNo;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public int getAccount_no(){
        return this.account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }
}

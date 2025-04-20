package com.hugo83.mybatlog.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserDto {
    private String email;
    private String pwd;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
    private Date reg_date;

    public UserDto() {
    }

    public UserDto(String email, String pwd, String name, Date birth, Date reg_date) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.reg_date = reg_date;
    }
    @Override
    public String toString() {
        return "UserDto [" 
                + "email=" + this.email + "/ "
                + "pwd=" + this.pwd + "/ " 
                + "name=" + this.name + "/ "
                + "birth=" + this.birth + "/ "
                + "reg_date=" + this.reg_date 
                + "]";
    }

    // Getter/Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getRegDate() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
}

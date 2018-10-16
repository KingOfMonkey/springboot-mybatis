package com.sailing.springbootmybatis.bean;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class Userinfo implements Serializable {
    private Integer id;
    
    private String userId;

    @NotBlank(message = "用户姓名不能为空")
    @Size(min = 2,max = 10,message = "年龄长度在2到10位之间")
    private String userName;

    @NotNull(message = "用户年龄不能为null")
    @Max(value = 120, message = "年龄在1到120之间")
    @Min(value = 1,message = "年龄在1到120之间")
    private Integer age;

    private String gender;

    private String address;

    private String userPass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
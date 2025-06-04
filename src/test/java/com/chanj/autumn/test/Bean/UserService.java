package com.chanj.autumn.test.Bean;
public class UserService {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId)+", 公司："+company+", 地点"+location;
    }

    // ...get/set

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getuId() {
        return uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

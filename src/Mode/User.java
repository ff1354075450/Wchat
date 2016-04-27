package Mode;

import java.util.Date;

/**
 * Created by ff on 16-3-8.
 */
public class User {
    private String userid=null;//用户id
    private String username=null;//用户名
    private String password=null;//密码
    private String age=null;//年龄
    private String sex=null;//性别
    private Date birthday=null;//生日
    private String home=null;//居住地
    private String picture=null;//头像图片

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status=0;

    public User() {
    }

    public User(String userid, String username, String password, String age, String sex, Date birthday, String home, String picture) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.home = home;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}

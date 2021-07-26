package com.kosmo.a30xmljsonparser02;

// 영화배우 정보를 저장하기 위한 VO객체
public class ActorVO {

    // 멤버변수
    private String name;
    private String age;
    private String hobbys;
    private String login;
    private int profileImg;

    // 생성자
    public ActorVO() {}

    // 인자 생성자
    public ActorVO(String name, String age, String hobbys, String login, int profileImg) {
        this.name = name;
        this.age = age;
        this.hobbys = hobbys;
        this.login = login;
        this.profileImg = profileImg;
    }

    // getter/setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHobbys() {
        return hobbys;
    }

    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }
}

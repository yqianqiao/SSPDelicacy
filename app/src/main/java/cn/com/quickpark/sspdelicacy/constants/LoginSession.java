package cn.com.quickpark.sspdelicacy.constants;

/**
 * Created by y on 2018/7/10.
 */

public class LoginSession {

    private LoginSession(){}

    private static class Single {
        private static final LoginSession loginSession = new LoginSession();
    }

    public static LoginSession getSession(){
        return Single.loginSession;
    }

    private User user ;


    public User getUser() {
        return user;
    }

   public void setUser(User user) {
        this.user = user;
    }


}

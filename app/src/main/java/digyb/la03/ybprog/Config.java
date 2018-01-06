package digyb.la03.ybprog;

/**
 * Created by Quantum Higgs on 1/6/2018.
 */

public class Config {

    //URL REQUEST
    public static final String URL_LOGIN = "http://192.168.100.2/yb/login.php";
    public static final String URL_REG = "http://192.168.100.2/yb/register.php";

    //POST REQUEST

    // YG DI DALAM STRING SESUAIKAN DENGAN $_POST DI SIDE SERVER
    public static final String LOGIN_USER = "user";
    public static final String LOGIN_PASS = "pass";

    public static final String REG_USER = "rUser";
    public static final String REG_PASS = "rPass";
    public static final String REG_EMAIL = "rEmail";

    //STATUS
    public static final String LOGIN_ISLOG = "success";
    public static final String REG_ISREG = "success";

}

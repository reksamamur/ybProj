package digyb.la03.ybprog;

/**
 * Created by Quantum Higgs on 1/6/2018.
 */

public class Config {

    //URL REQUEST
    public static final String URL_LOGIN = "https://portly-drainage.000webhostapp.com/isi/login.php";
    public static final String URL_REG = "https://portly-drainage.000webhostapp.com/isi/register.php";
    public static final String URL_CODE = "https://portly-drainage.000webhostapp.com/isi/tambah_kode.php";
    public static final String URL_LIST = "https://portly-drainage.000webhostapp.com/isi/list_murid.php";

    //POST REQUEST

    // YG DI DALAM STRING SESUAIKAN DENGAN $_POST DI SIDE SERVER
    public static final String LOGIN_USER = "user";
    public static final String LOGIN_PASS = "pass";

    public static final String REG_USER = "rUser";
    public static final String REG_PASS = "rPass";
    public static final String REG_EMAIL = "rEmail";

    public static final String CODE_KODE = "kode";

    //STATUS
    public static final String LOGIN_ISLOG = "success";
    public static final String REG_ISREG = "success";
    public static final String CODE_ISACC = "success";

    //TEMP DATA
    public String nama = "";
    public static String sNama = "";

}

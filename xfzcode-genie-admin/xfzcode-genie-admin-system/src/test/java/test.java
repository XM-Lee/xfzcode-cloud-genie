import cn.dev33.satoken.secure.BCrypt;

/**
 * @Author: XMLee
 * @Date: 2023/8/17 16:24
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("123456", BCrypt.gensalt(12)));
    }
}

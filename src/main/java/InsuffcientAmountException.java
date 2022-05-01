/**
 * @description: 余额不足异常
 * @author: 刘帅彪
 * @create: 2022-05-01 13:13
 **/


public class InsuffcientAmountException extends Exception{
    public InsuffcientAmountException(String message) {
        super(message);
    }
}

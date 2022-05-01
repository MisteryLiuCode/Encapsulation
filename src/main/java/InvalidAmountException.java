/**
 * @description: 自定义异常
 * @author: 刘帅彪
 * @create: 2022-05-01 12:58
 **/


public class InvalidAmountException extends Exception{

    public InvalidAmountException(String message) {
        super(message);
    }

}

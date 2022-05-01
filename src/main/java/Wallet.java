import java.math.BigDecimal;

/**
 * @description: 钱包类
 * @author: 刘帅彪
 * @create: 2022-05-01 12:44
 **/


public class Wallet {
    private int id;
    private long createTime;
    //    钱包余额
    private BigDecimal balance;
    //    余额修改时间
    private long balanceLastModifiedTime;

    //    生成get方法
    public int getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getBalanceLastModifiedTime() {
        return balanceLastModifiedTime;
    }

     /* 方法描述
       **
       * @Description: 构造函数初始化钱包
       * @Param: * @param null
       * @return:
       * @Author: misteryliu
       * @Date: 2022/5/1
       */
    public Wallet() {
        this.id =(int)Math.random()*1000;
        this.createTime=System.currentTimeMillis();
        this.balance=BigDecimal.ZERO;
        this.balanceLastModifiedTime=System.currentTimeMillis();
    }

    /* 方法描述
     **
     * @Description: 增加钱包里的钱
     * @Param: * @param increaseAmount
     * @return:
     * @Author: misteryliu
     * @Date: 2022/5/1
     */
    public void incrementBalance(BigDecimal increaseAmount) throws InvalidAmountException {
        /**
         * BigDecimal.compareTo()方法适用于金额的比较，可以忽略精度只比较数值
         * 相等返回0，小于返回-1，大于返回1
         */
        if (increaseAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("增加的金额不能为负数");
        }
//        把金额加上去
        this.balance.add(increaseAmount);
//        添加修改时间
        balanceLastModifiedTime = System.currentTimeMillis();
    }

    public void decrementBalance(BigDecimal decrementAmount) throws InsuffcientAmountException, InvalidAmountException {
        /**
         * BigDecimal.compareTo()方法适用于金额的比较，可以忽略精度只比较数值
         * 相等返回0，小于返回-1，大于返回1
         */
        if (decrementAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("增加的金额不能为负数");
        }
//        余额不足
        if (decrementAmount.compareTo(balance) > 0) {
            throw new InsuffcientAmountException("余额不足");
        }
        balance.subtract(decrementAmount);
        balanceLastModifiedTime = System.currentTimeMillis();
    }
}

# 封装

封装是面对对象三大特性之一，封装也被叫做，信息隐藏或者信息访问保护。类通过暴露有限的访问接口，授权外部通过类授权你的函数访问内部信息或者数据。

## 例子：

一个钱包系统，对于每一个用户都会有一个虚拟钱包，用来记录系统中的虚拟货币交易量。下面一个钱包类。

```java
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
```

## 分析	

​	对于钱包余额这个属性，从业务的角度来讲，只能增加或者减少，并不会被重新设置，只有在初始化的时候才会被设置，所以只暴露了incrementBalance()和decrementBalance()方法，对于balanceLastModifiedTime修改时间的属性是跟操作这两个方法进行绑定的，保证了balance和balanceModified两个数据的一致性。而创建时间和钱包id再初始化钱包的时候才会创建，后续不需要进行修改，所以只保留了get方法。

## 总结：

​	对于封装这个特性，我们需要编程语言本身提供一定的语法机制来支持。这个语法机制就是访 问权限控制。例子中的private、public等关键字就是Java语言中的访问权限控制语法。 private关键字修饰的属性只能类本身访问，可以保护其不被类之外的代码直接访问。如果Java语言没有提供访问权限控制语法，所有的属性默认都是public的，那任意外部代码都可 以通过类似wlet.id=123;这样的方式直接访问、修改属性，也就没办法达到隐藏信息和保护 数据的目的了，也就无法支持封装特性了。
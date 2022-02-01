package ru.example.threadpool.transaction;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction implements Runnable {

    final static Logger logger = org.apache.log4j.Logger.getLogger(Transaction.class);

    AtomicInteger count = new AtomicInteger(0);

    Random random = new Random();

    private List<Account> list;

    public Transaction(List<Account> list) {
        this.list = list;
    }

    @Override
    public void run() {

            while (count.get() < 30) {

                transaction(
                        list.get(random.nextInt(list.size())),
                        list.get(random.nextInt(list.size())),
                        random.nextInt(5_000));

                try {
                    Thread.sleep(1_000 + random.nextInt(1_001));
                } catch (InterruptedException exc) {
                    logger.error(exc);
                }

                logger.info(count);
            }
    }

    public void transaction(Account account1, Account account2, int i) {

        Object ob1 = account1.getLock();
        Object ob2 = account2.getLock();

        if (account1 != account2){
            if (account2.getMoney() > 0 && account2.getMoney() >= i) {


                synchronized (ob1) {
                    synchronized (ob2) {
                        account1.setMoney(account1.getMoney() + i);
                        account2.setMoney(account2.getMoney() - i);

                        logger.info(Thread.currentThread().getName() + " перевод со счета: " +
                                account2.getId() + " на счет: " + account1.getId() + " - сумма: "
                                + i);

                    }
                }
                count.incrementAndGet();
            }
            else logger.error("на счете : " + account2.getMoney() + " требуется перевести: " + i);
        }
    }
}

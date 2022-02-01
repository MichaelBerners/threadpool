package ru.example.threadpool.transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {

        ListAccount listAccount = new ListAccountImpl(4);
        List<Account> list = listAccount.getListAccount();

        ExecutorService executor = Executors.newFixedThreadPool(8);
        Runnable worker = new Transaction(list);

        for (int i = 0; i < 8; i++) {
            executor.execute(worker);
        }

        executor.shutdown();
    }
}

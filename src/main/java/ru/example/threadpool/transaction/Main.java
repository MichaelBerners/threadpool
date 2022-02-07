package ru.example.threadpool.transaction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Main implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

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

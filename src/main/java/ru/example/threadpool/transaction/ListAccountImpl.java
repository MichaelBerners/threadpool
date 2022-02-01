package ru.example.threadpool.transaction;

import java.util.ArrayList;
import java.util.List;

public class ListAccountImpl implements ListAccount{

    private final List<Account> listAccount = new ArrayList<>();

    public ListAccountImpl(int size) {

        for (int i = 0; i < size; i++) {
            Account account = new Account(RandomId.getRandomString(5), 10_000);
            listAccount.add(account);
        }
    }

    @Override
    public List<Account> getListAccount() {
        return listAccount;
    }

    @Override
    public void printList() {
        listAccount.stream().forEach(System.out::println);
    }
}

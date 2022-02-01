package ru.example.threadpool.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Account {

    private String id;
    private int money;
    private final Object lock;

    public Account(String id, int money) {

        this.id = id;
        this.money = money;
        lock = new Object();

    }
}

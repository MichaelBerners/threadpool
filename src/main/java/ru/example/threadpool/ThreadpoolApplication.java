package ru.example.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ThreadpoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadpoolApplication.class, args);


    }

}

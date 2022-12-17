package Cryptoo.com.example.Cryptoo.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/conecteduser")
public class ConectedUser {

    static public final AtomicInteger counter = new AtomicInteger();

    @RequestMapping("/connect")
    public int connectUser() {
        return counter.incrementAndGet();
    }

    @RequestMapping("/disconnect")
    public int disconnectUser() {
        return counter.decrementAndGet();
    }
}

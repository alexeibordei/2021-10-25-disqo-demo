package com.disqo.demo.NotesApp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        String names[] = {"admin", "alex", "alice", "john", "louis"};
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for(String name: names) {
            String encodedPassword = encoder.encode(name);
            System.out.println(encodedPassword);
        }
    }
}

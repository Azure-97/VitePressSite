package org.example;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;
public class Main {


    public static void main(String[] args) {

        ServiceLoader<Search> sl = ServiceLoader.load(Search.class);
        for (Search search : sl) {
            search.searchDoc("hello world");
        }
    }
}
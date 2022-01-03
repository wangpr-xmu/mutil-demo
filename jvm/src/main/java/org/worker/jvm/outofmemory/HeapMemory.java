package org.worker.jvm.outofmemory;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author peiru wang
 * @date 2021/8/30
 */
public class HeapMemory {

    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String nextLine = scanner.nextLine();

        if("begin".equals(nextLine)) {
            while(true) {
                list.add(new Object());
            }
        }else {
            System.out.println("end");
        }

    }
}

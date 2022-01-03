package org.worker.jvm.markword;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author peiru wang
 * @date 2021/8/29
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        ObjectHeadDemo demo = new ObjectHeadDemo();
        System.out.println("hashcode: " + Integer.toHexString(demo.hashCode()));

        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
    }
}

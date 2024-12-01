package cn.lcx.design.pattern.singleton;

/**
 * @description:
 * @author: L1Chenxv
 * @create: 2024-12-01 18:43
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(IdGenerator.SINGLE.getId());
        System.out.println(IdGenerator.SINGLE.getId());
        System.out.println(IdGenerator.INSTANCE.getId());
    }
}

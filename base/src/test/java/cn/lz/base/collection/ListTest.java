package cn.lz.base.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    static class Human {
        int height;

        public Human(int height) {
            this.height = height;
        }
    }


    @Test
    public void test() {
        List<Human> humanList = new ArrayList<>();
//        humanList.add(new Human(10));
//        humanList.add(new Human(20));
        int heightCount = humanList.stream().mapToInt(human -> human.height).sum();
        Assert.assertEquals(30, heightCount);
    }

}

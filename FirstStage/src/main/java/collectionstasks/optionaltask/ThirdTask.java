package collectionstasks.optionaltask;

import java.util.*;

//   5. Не используя вспомогательных объектов, переставить отрицательные элементы данного списка в конец,
//   а положительные — в начало списка.

public class ThirdTask {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-2,6,-3,-1,0,4,7,9);
        System.out.println(list.toString());
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list.toString());

    }
}

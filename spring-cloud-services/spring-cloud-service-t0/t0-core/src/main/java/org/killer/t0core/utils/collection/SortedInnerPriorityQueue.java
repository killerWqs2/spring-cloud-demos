package org.killer.t0core.utils.collection;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 使用TreeSet解决，，
 *
 * @author killer
 * @date 2020/06/16 - 10:11
 */
public class SortedInnerPriorityQueue extends PriorityQueue {

    public static void main(String[] args) {
        // 我吐了 siftupbycomparator 是private
        TreeMap<String, Object> treeMap = new TreeMap<>();
        // 行不通， TreeMap里面的都是包作用域级别的
    }

    @Override
    public boolean offer(Object o) {
        return super.offer(o);
    }
}

package com.zk.codetop;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class _460_LFUCache {
    public static void main(String[] args) {
        // cnt(x) = 键 x 的使用计数
        // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));  // 返回1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));  // 返回-1
        System.out.println(lfu.get(3));  // 返回3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));  // 返回-1
        System.out.println(lfu.get(3));  // 返回3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));  // 返回4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }

}

class LFUCache {
    int capacity;
    int minFreq;
    Map<Integer, Node> keyMap;
    Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minFreq = 0;
        keyMap = new HashMap<>(capacity);
        freqMap= new HashMap<>();
    }

    public int get(int key) {
        if(!keyMap.containsKey(key)){
            return -1;
        }
        Node node = keyMap.get(key);
        int val = node.val;
        int freq = node.freq;
        DoublyLinkedList list = freqMap.get(freq);
        list.removeNode(node);
        if(list.size == 0){
            freqMap.remove(freq);
            if(minFreq == freq){
                minFreq += 1;
            }
        }
        DoublyLinkedList linkedList = freqMap.getOrDefault(freq + 1, new DoublyLinkedList());
        linkedList.addToHead(new Node(key, val, freq + 1));
        freqMap.put(freq + 1, linkedList);
        keyMap.put(key, freqMap.get(freq + 1).getHead());
        return val;
    }

    public void put(int key, int value) {
        if(keyMap.containsKey(key)){
            Node node = keyMap.get(key);
            int freq = node.freq;
            freqMap.get(freq).removeNode(node);
            if(freqMap.get(freq).size == 0){
                freqMap.remove(freq);
                if(minFreq == freq){
                    minFreq += 1;
                }
            }
            DoublyLinkedList list = freqMap.getOrDefault(freq + 1, new DoublyLinkedList());
            list.addToHead(new Node(key, value, freq + 1));
            freqMap.put(freq + 1, list);
            keyMap.put(key, freqMap.get(freq + 1).getHead());
            return;
        }
        if(keyMap.size() == capacity){
            Node node = freqMap.get(minFreq).removeTail();
            keyMap.remove(node.key);
            if(freqMap.get(minFreq).size == 0){
                freqMap.remove(minFreq);
            }
        }
        DoublyLinkedList list = freqMap.getOrDefault(1, new DoublyLinkedList());
        list.addToHead(new Node(key, value, 1));
        freqMap.put(1, list);
        keyMap.put(key, freqMap.get(1).getHead());
        minFreq = 1;
    }
}

class Node{
    int key;
    int val;
    int freq;
    Node next, prev;
    public Node(int key, int value, int freq){
        this.key = key;
        this.val = value;
        this.freq = freq;
    }
}

class DoublyLinkedList{
    Node head, tail;
    int size;
    public DoublyLinkedList(){
        head = new Node(-1, -1, 0);
        tail = new Node(-1, -1, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    public void addToHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        size++;
    }

    public Node getHead(){
        return head.next;
    }

    public Node getTail(){
        return tail.prev;
    }

    public Node removeTail() {
        Node node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        size--;
        return node;
    }
}












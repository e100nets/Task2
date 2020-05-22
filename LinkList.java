package ilya.ignatov;

import java.util.Random;

public class LinkList {
    private class Node {
        private int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public void clear () {
        head = null;
        tail = null;
        size = 0;
    }

    public void addElement (int value){
        if (size == 0){
            head = tail = new Node(value, null);
        }
        else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
        size++;
    }

    public int [] randomList(int k) {
        Random random = new Random ();
        int [] array = new int [k];
        for (int i = 0; i < k; i++) {
            array[i] = random.nextInt(20)+1;
        }
        return array;
    }

    public void listFromArray (int [] array) {
        for (int i = 0; i<array.length; i++){
            addElement(array [i]);
        }
    }

    public int findMax () {
        int max = 0;
        Node current = head;
        if (size == 1)
            return current.value;
        else {
            for (int i = 0; i < size - 1; i++) {
                if (current.value > max)
                    max = current.value;
                current = current.next;
            }
            return max;
        }
    }
    public  int findCount () {
        int count = 0;
        Node current = head;
        if (size == 1)
            return 1;
        else {
            for (int i = 0; i < size; i++) {
                if (current.value == findMax())
                    count++;
                current = current.next;
            }
            return count;
        }
    }
}

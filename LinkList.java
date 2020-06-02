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

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
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

    public void deleteElement () {
        Node current = head;
        if (size == 0) {
            return;
        }
        if (size == 1) {
            clear();
        }
        else {
            while (current.next != null) {
                if(current.next == tail) {
                    tail = current;
                    break;
                }
                current = current.next;
            }
            size--;
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
        int maxElement = findMax();
        if (size == 1)
            return 1;
        else {
            for (int i = 0; i < size; i++) {
                if (current.value == maxElement)
                    count++;
                current = current.next;
            }
            return count;
        }
    }

    public int[] toArray () {
        Node current = head;
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = current.value;
            current = current.next;
        }
        return array;
    }
}

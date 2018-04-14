package cn.com.jdkdemo.mycollection;

/**
 * Author:   shenjx
 * Date:     2018/4/9 14:03
 * Description:
 */
public class MyLinkedList<E> {


    public MyLinkedList() {
        size = 0;
    }


    private Node<E> head;
    private Node<E> foot;

    private int size;

    public int size() {
        return size;
    }


    public void add(E e) {
        if (head == null) {
            head = new Node<E>(e, null, null);
            foot = head;
        } else {
            Node<E> node = new Node<E>(e, foot, head);
            foot.next = node;
            foot = node;
        }
        size++;
    }

    public void set(int index, E e) throws Exception {
        if (index >= size) {
            throw new Exception("超出范围");
        }
        if (index == 0) {
            head.e = e;
        } else {
            Node<E> tmp = head;
            for (int i = 1; i <= index; i++) {
                tmp = tmp.next;
            }
            tmp.e = e;
        }

    }

    public E get(int index) throws Exception {
        if (index >= size) {
            throw new Exception("超出范围");
        }
        int cnt = 0;
        try {
            if (index == 0) {
                return head.e;
            } else if (index == size - 1) {
                return foot.e;
            } else {
                int mid = (size - 1) / 2;
                if (index <= mid) {//小于等于中间位置，从前往后找
                    Node<E> tmp = head;
                    for (int i = 1; i <= index; i++) {
                        cnt++;
                        tmp = tmp.next;
                    }
                    return tmp.e;
                } else {//从后往前找
                    Node<E> tmp = foot;
                    for (int i = 1; i <= size - index - 1; i++) {
                        cnt++;
                        tmp = tmp.previous;
                    }
                    return tmp.e;
                }
            }
        } finally {
            System.out.println("循环了" + cnt + "次");
        }
    }

    public void clear() {
        head = null;
        foot = null;
        size = 0;
    }

    public void removeAt(int index) throws Exception {
        if (index >= size) {
            throw new Exception("超出范围");
        }
        if (index == 0) {
            head = head.next;
            foot.next = head;
            head.previous = foot;
        } else {
            Node<E> tmp = head;
            for (int i = 1; i <= index; i++) {
                tmp = tmp.next;
            }
            tmp.previous.next = tmp.next;
            tmp.next.previous = tmp.previous;
        }
        size--;
    }


    private class Node<E> {
        E e;
        Node<E> next;
        Node<E> previous;

        public Node(E e, Node<E> previous, Node<E> next) {
            this.e = e;
            this.next = next;
            this.previous = previous;
        }

        public E getValue() {
            return e;
        }
    }


}

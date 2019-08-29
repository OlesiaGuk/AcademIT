package guk.List;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;
        count++;
    }

    public int getSize() {
        return count;
    }

    public T getHeadData() {
        if (count == 0) {
            throw new IllegalArgumentException("Список пустой");
        }

        return head.getData();
    }

    private ListItem<T> getListItemByIndex(int index) {
        ListItem<T> p = head;
        int i = 0;
        while (i < index) {
            p = p.getNext();
            i++;
        }
        return p;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }
    }

    public T getDataByIndex(int index) {
        checkIndex(index);
        return getListItemByIndex(index).getData();
    }

    public T setDataByIndex(T data, int index) {
        checkIndex(index);
        ListItem<T> p = getListItemByIndex(index);
        T oldData = p.getData();
        p.setData(data);
        return oldData;
    }

    public T deleteFirstItem() {
        if (count == 0) {
            throw new IllegalArgumentException("Список пустой");
        }

        ListItem<T> p = head;
        head = head.getNext();
        count--;
        return p.getData();
    }

    public T deleteItemByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirstItem();
        }

        ListItem<T> prev = getListItemByIndex(index - 1);
        ListItem<T> p = prev.getNext();
        prev.setNext(p.getNext());
        count--;
        return p.getData();
    }

    public void addToStart(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        if (index == 0) {
            addToStart(data);
        } else {
            ListItem<T> prev = getListItemByIndex(index - 1);

            ListItem<T> q = new ListItem<>(data, prev.getNext());
            prev.setNext(q);
            count++;
        }
    }

    public boolean deleteItemByData(T data) {
        if (count == 0) {
            return false;
        }

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                if (prev == null) {
                    head = p.getNext();
                    count--;
                    return true;
                }
                prev.setNext(p.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public SinglyLinkedList<T> copy() {
        if (count == 0) {
            return new SinglyLinkedList<>(null);
        }

        SinglyLinkedList<T> copy = new SinglyLinkedList<>(new ListItem<>(getHeadData(), null));

        for (ListItem<T> p = head.getNext(), q = copy.head; p != null; p = p.getNext()) {
            ListItem<T> newItem = new ListItem<>(p.getData(), null);
            q.setNext(newItem);
            q = newItem;
        }

        copy.count = count;
        return copy;
    }

    public void reverse() {
        if (count == 0) {
            return;
        }

        for (ListItem<T> p = head, q = p.getNext(), prev = null; ; q = q.getNext()) {
            p.setNext(prev);
            if (q == null) {
                head = p;
                return;
            }
            prev = p;
            p = q;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            s.append(p.getData());
            if (p.getNext() != null) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}

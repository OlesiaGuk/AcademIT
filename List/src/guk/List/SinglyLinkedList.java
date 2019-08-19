package guk.List;

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
        return head.getData();
    }

    public T getDataByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        ListItem<T> p = head;
        int i = 0;
        while (i < index) {
            p = p.getNext();
            i++;
        }
        return p.getData();
    }

    public T setDataByIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        ListItem<T> p = head;
        int i = 0;
        while (i < index) {
            p = p.getNext();
            i++;
        }
        T oldData = p.getData();
        p.setData(data);
        return oldData;
    }

    public T deleteFirstItem() {
        ListItem<T> p = head;
        head = head.getNext();
        count--;
        return p.getData();
    }

    public T deleteItemByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        if (index == 0) {
            return deleteFirstItem();
        } else {
            ListItem<T> p = head;
            ListItem<T> prev = null;

            int i = 0;
            while (i < index) {
                prev = p;
                p = p.getNext();
                i++;
            }
            prev.setNext(p.getNext());

            count--;
            return p.getData();
        }
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
        } else if (index == count) {
            addToEnd(data);
        } else {
            ListItem<T> p = head;
            ListItem<T> prev = null;

            int i = 0;
            while (i < index) {
                prev = p;
                p = p.getNext();
                i++;
            }

            ListItem<T> q = new ListItem<>(data, p.getNext());
            q.setNext(prev.getNext());
            prev.setNext(q);
            count++;
        }
    }

    public boolean deleteItemByData(T data) {
        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                if (prev == null) {
                    head = p.getNext();
                    count--;
                    return true;
                } else if (p.getNext() == null) {
                    prev.setNext(null);
                    count--;
                    return true;
                } else {
                    prev.setNext(p.getNext());
                    count--;
                    return true;
                }
            }
        }
        return false;
    }

    public SinglyLinkedList<T> copy() {
        ListItem<T> p = head;
        SinglyLinkedList<T> copy = new SinglyLinkedList<>(new ListItem<>(p.getData(), null));
        for (p = head.getNext(); p != null; p = p.getNext()) {
            copy.addToEnd(p.getData());
        }
        return copy;
    }

    public void addToEnd(T data) {
        ListItem<T> p = head;
        ListItem<T> newItem = new ListItem<>(data, null);

        if (p == null) {
            head = newItem;
            count++;
        } else if (p.getNext() == null) {
            p.setNext(newItem);
            count++;
        } else {
            ListItem<T> prev = null;

            while (p != null) {
                prev = p;
                p = p.getNext();
            }
            prev.setNext(newItem);
            count++;
        }
    }

    public void reverse() {
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
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            s.append(p.getData());
            if (p.getNext() != null) {
                s.append(", ");
            }
        }
        return s.toString();
    }
}

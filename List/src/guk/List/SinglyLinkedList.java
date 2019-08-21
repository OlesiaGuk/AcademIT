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
        if (count == 0) {
            throw new IllegalArgumentException("Список пустой");
        }

        return head.getData();
    }

    private ListItem<T> getPointerByIndex(int index) {
        ListItem<T> p = head;
        int i = 0;
        while (i < index) {
            p = p.getNext();
            i++;
        }
        return p;
    }

    public T getDataByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        return getPointerByIndex(index).getData();
    }

    public T setDataByIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        ListItem<T> p = getPointerByIndex(index);
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
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс превышает размер списка");
        }

        if (index == 0) {
            return deleteFirstItem();
        } else {
            ListItem<T> prev = getPointerByIndex(index - 1);
            ListItem<T> p = prev.getNext();
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
        } else {
            ListItem<T> prev = getPointerByIndex(index - 1);

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
            if (data == null ? p.getData() == null : data.equals(p.getData())) {
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
        if (count == 0) {
            throw new IllegalArgumentException("Список пустой");
        }

        SinglyLinkedList<T> copy = new SinglyLinkedList<>(new ListItem<>(getHeadData(), null));
        for (ListItem<T> p = head.getNext(), q = copy.head; p != null; p = p.getNext()) {
            ListItem<T> newItem = new ListItem<>(p.getData(), null);
            q.setNext(newItem);
            q = newItem;
            copy.count++;
        }
        return copy;
    }

    public void reverse() {
        if (count == 0) {
            throw new IllegalArgumentException("Список пустой");
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
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            s.append(p.getData());
            if (p.getNext() != null) {
                s.append(", ");
            }
        }
        return s.toString();
    }
}

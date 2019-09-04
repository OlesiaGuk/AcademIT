package guk.myArrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList(int capacity) {
        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Значение индекса превышает количество элементов в списке");
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E replacedElement = items[index];
        items[index] = element;
        return replacedElement;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= items.length) {
            return;
        }
        items = Arrays.copyOf(items, items.length * 2);

        if (items.length >= requiredCapacity) {
            return;
        }
        ensureCapacity(requiredCapacity);//на случай, если емкость списка увеличена недостаточно
    }

    @Override
    public void add(int index, E element) { //вставка по индексу
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Значение индекса превышает размер списка");
        }

        ensureCapacity(size + 1);
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean add(E e) { //вставка в конец списка
        add(size, e);
        modCount++;
        return true;
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int savedModCount = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public E next() {
            checkModCount();

            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            currentIndex++;
            return items[currentIndex];
        }

        private void checkModCount() {
            if (savedModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась!");
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public boolean contains(Object o) {//проверяет, есть ли в списке указанный элемент
        for (E e : items) {
            if (Objects.equals(e, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removingElement = items[index];
        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }
        size--;
        modCount++;
        return removingElement;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
        }
        modCount++;
        return false;
    }

    @Override
    public int indexOf(Object o) { // индекс первого вхождения
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {//удаление всех элементов
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
        modCount++;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) { //добавляет в конец все элементы из переданной коллекции
        Object[] addedArray = c.toArray();
        int addedArraySize = addedArray.length;

        if (addedArraySize == 0) {
            return false;
        }

        ensureCapacity(size + addedArraySize);
        System.arraycopy((E[]) addedArray, 0, items, size, addedArraySize);
        size += addedArraySize;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть >= 0");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Значение индекса превышает количество элементов в списке");
        }

        Object[] addedArray = c.toArray();
        int addedArraySize = addedArray.length;

        if (addedArraySize == 0) {
            return false;
        }

        ensureCapacity(size + addedArraySize);
        System.arraycopy(items, index, items, index + addedArraySize, size - index);
        System.arraycopy((E[]) addedArray, 0, items, index, addedArraySize);
        size += addedArraySize;
        modCount++;
        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, size);
        return a;
    }

    private void trimToSize() {
        if (items.length == size) {
            return;
        }
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] checkingArray = c.toArray();
        for (Object e : checkingArray) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) { //удаление всех элементов списка, которые есть в указанной коллекции
        int currentModCount = modCount;
        trimToSize();
        for (E e : items) {
            if (c.contains(e)) {
                remove(e);
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int currentModCount = modCount;
        trimToSize();
        for (E e : items) {
            if (!c.contains(e)) {
                remove(e);
            }
        }

        return currentModCount != modCount;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size; i++) {
            s.append(items[i]);
            if (i != size - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public ListIterator<E> listIterator() { //согласно заданию, не реализовываем
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {//согласно заданию, не реализовываем
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {//согласно заданию, не реализовываем
        return null;
    }
}


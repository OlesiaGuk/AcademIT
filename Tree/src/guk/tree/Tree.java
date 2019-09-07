package guk.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public Tree(TreeNode<T> root) {
        this.root = root;
        size++;
    }

    public T getRootData() {
        if (size == 0) {
            throw new IllegalArgumentException("В дереве нет элементов");
        }
        return root.getData();
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> element = stack.pollLast();

            try {
                s.append(element.getData()).append("  ");
                if (element.getRight() != null) {
                    stack.addLast(element.getRight());
                }
                if (element.getLeft() != null) {
                    stack.addLast(element.getLeft());
                }
            } catch (NullPointerException e) {
                s.append("Дерево не содержит элементов!");
            }
        }
        return s.toString();
    }

    public void add(T newData) {
        TreeNode<T> newNode = new TreeNode<>(newData);

        for (TreeNode<T> currentNode = root; ; ) {
            if (newData.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }
                currentNode.setLeft(newNode);
                size++;
                return;
            }
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                continue;
            }
            currentNode.setRight(newNode);
            size++;
            return;
        }
    }

    public TreeNode<T> findNode(T value) {
        for (TreeNode<T> currentNode = root; ; ) {
            if (value.compareTo(currentNode.getData()) == 0) {
                return currentNode;
            }
            if (value.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }
                return null;
            }
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                continue;
            }
            return null;
        }
    }

    public boolean deleteNode(T value) {//todo: стр.196
        TreeNode<T> foundNode = null;
        TreeNode<T> parentNode = null;

        //ищем узел с заданным значением, а также его родителя
        for (TreeNode<T> currentNode = root; ; ) {
            if (value.compareTo(currentNode.getData()) == 0) {
                foundNode = currentNode;
                break;
            }
            if (value.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    parentNode = currentNode;
                    currentNode = currentNode.getLeft();
                    continue;
                }
                break;
            }
            if (currentNode.getRight() != null) {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
                continue;
            }
            break;
        }

        // если узел с искомым значением есть в дереве
        if (foundNode != null) {
            // если удаляем лист
            if (foundNode.getLeft() == null && foundNode.getRight() == null) {
                if (parentNode == null) {//если удаляем корень, у которого нет детей
                    root = null;
                    size--;
                    return true;
                }

                if (parentNode.getLeft() == foundNode) {
                    parentNode.setLeft(null);
                    size--;
                    return true;
                }
                parentNode.setRight(null);
                size--;
                return true;
            }

            // если у удаляемого элемента есть только левый сын
            if (foundNode.getLeft() != null && foundNode.getRight() == null) {
                if (parentNode == null) { // если удаляем корень
                    root = foundNode.getLeft();
                    size--;
                    return true;
                }

                if (parentNode.getLeft() == foundNode) {
                    parentNode.setLeft(foundNode.getLeft());
                    size--;
                    return true;
                }
                parentNode.setRight(foundNode.getLeft());
                size--;
                return true;
            }

            //если у удаляемого элемента есть только правый сын
            if (foundNode.getLeft() == null && foundNode.getRight() != null) {
                if (parentNode == null) {
                    root = foundNode.getRight();
                    size--;
                    return true;
                }

                if (parentNode.getLeft() == foundNode) {
                    parentNode.setLeft(foundNode.getRight());
                    size--;
                    return true;
                }
                parentNode.setRight(foundNode.getRight());
                size--;
                return true;
            }

            //если у удаляемого элемента есть 2 детей
            if (foundNode.getLeft() != null && foundNode.getRight() != null) {
                TreeNode<T> minLeftNode = foundNode.getRight();
                TreeNode<T> minLeftNodeParent = foundNode;

            /*    if (parentNode == null && foundNode.getRight().getLeft() == null) {
                    minLeftNode.setLeft(foundNode.getLeft());
                    root = minLeftNode;
                    size--;
                    return true;
                    }*/


                //ищем самый левый элемент в правом поддереве
                while (minLeftNode.getLeft() != null) {
                    minLeftNodeParent = minLeftNode;
                    minLeftNode = minLeftNode.getLeft();
                }

                if (minLeftNodeParent == foundNode) {
                    //todo: реализация для узла с 2 детьми, но без левого элемента в правом поддереве
                }
                // убираем из дерева самый левый элемент
                if (minLeftNode.getRight() != null) { //если у самого левого элемента есть правый сын
                    minLeftNodeParent.setLeft(minLeftNode.getRight());
                } else { //если у самого левого элемента нет правого сына
                    minLeftNodeParent.setLeft(null);
                }

                //удаляем узел с искомым значением
                minLeftNode.setLeft(foundNode.getLeft());
                minLeftNode.setRight(foundNode.getRight());
                if (parentNode == null) { // если удаляемый узел - это корень
                    root = minLeftNode;
                    size--;
                    return true;
                }
                if (parentNode.getLeft() == foundNode) {
                    parentNode.setLeft(minLeftNode);
                    size--;
                    return true;
                }
                parentNode.setRight(minLeftNode);
                size--;
                return true;
            }
        }

        //если нет узла с заданным значением
        return false;
    }

    public void breadthFirstSearch() { //обход в ширину
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> element = queue.poll();
            System.out.println(element);
            if (element.getLeft() != null) {
                queue.add(element.getLeft());
            }
            if (element.getRight() != null) {
                queue.add(element.getRight());
            }
        }
    }

    public void depthFirstSearch() { // обход в глубину
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> element = stack.pollLast();
            System.out.println(element);
            if (element.getRight() != null) {
                stack.addLast(element.getRight());
            }
            if (element.getLeft() != null) {
                stack.addLast(element.getLeft());
            }
        }
    }

    public void depthFirstSearchWithRecursion() {//todo: переименовать
        depthFirstSearchWithRecursionMethod(root);
    }

    private void depthFirstSearchWithRecursionMethod(TreeNode<T> node) { //todo: переименовать
        System.out.println(node.getData());

        for (TreeNode<T> leftChild = node.getLeft(); node.getLeft() != null; ) {
            depthFirstSearchWithRecursionMethod(leftChild);
        }

        if (node.getRight() != null) {
            for (TreeNode<T> rightChild = node.getRight(); node.getRight() != null; ) {
                depthFirstSearchWithRecursionMethod(rightChild);
            }
        }
    }
}
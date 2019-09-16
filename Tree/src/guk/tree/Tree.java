package guk.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
    }

    public Tree(T rootValue) {
        root = new TreeNode<>(rootValue);
        size++;
    }

    public Tree(T rootValue, Comparator<T> comparator) {
        root = new TreeNode<>(rootValue);
        this.comparator = comparator;
        size++;
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

            if (element != null) {
                s.append(element.getData()).append("  ");
                if (element.getRight() != null) {
                    stack.addLast(element.getRight());
                }
                if (element.getLeft() != null) {
                    stack.addLast(element.getLeft());
                }
            }
        }
        return s.toString();
    }

    private int getCompare(T data1, T data2, Comparator<T> comparator) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }
        return ((Comparable<T>) data1).compareTo(data2);
    }

    public void add(T newData) {
        TreeNode<T> newNode = new TreeNode<>(newData);

        for (TreeNode<T> currentNode = root; ; ) {
            int compareResult = getCompare(newData, currentNode.getData(), comparator);
            if (compareResult < 0) {
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

    public boolean findNode(T value) {
        for (TreeNode<T> currentNode = root; ; ) {
            int compareResult = getCompare(value, currentNode.getData(), comparator);
            if (compareResult == 0) {
                return true;
            }
            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                    continue;
                }
                return false;
            }
            if (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
                continue;
            }
            return false;
        }
    }

    public boolean deleteNode(T value) {
        TreeNode<T> deleteNode = null;
        TreeNode<T> deleteNodeParent = null;

        //ищем узел с заданным значением, а также его родителя
        for (TreeNode<T> currentNode = root; ; ) {
            int compareResult = getCompare(value, currentNode.getData(), comparator);
            if (compareResult == 0) {
                deleteNode = currentNode;
                break;
            }
            if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    deleteNodeParent = currentNode;
                    currentNode = currentNode.getLeft();
                    continue;
                }
                break;
            }
            if (currentNode.getRight() != null) {
                deleteNodeParent = currentNode;
                currentNode = currentNode.getRight();
                continue;
            }
            break;
        }

        if (deleteNode != null) {
            // если удаляем лист
            if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
                if (deleteNodeParent != null) {
                    if (deleteNodeParent.getLeft() == deleteNode) {
                        deleteNodeParent.setLeft(null);
                    } else {
                        deleteNodeParent.setRight(null);
                    }
                } else {
                    root = null; // если удаляем корень, у которого нет детей
                }

                size--;
                return true;
            }

            // если у удаляемого элемента только 1 ребенок
            if ((deleteNode.getLeft() != null && deleteNode.getRight() == null) || (deleteNode.getLeft() == null && deleteNode.getRight() != null)) {
                TreeNode<T> deleteNodeChild = deleteNode.getLeft() != null ? deleteNode.getLeft() : deleteNode.getRight();

                if (deleteNodeParent == null) {
                    root = deleteNodeChild;
                } else if (deleteNodeParent.getLeft() == deleteNode) {
                    deleteNodeParent.setLeft(deleteNodeChild);
                } else {
                    deleteNodeParent.setRight(deleteNodeChild);
                }

                size--;
                return true;
            }

            //если у удаляемого элемента есть 2 детей
            if (deleteNode.getLeft() != null && deleteNode.getRight() != null) {
                TreeNode<T> minLeftNode = deleteNode.getRight();
                TreeNode<T> minLeftNodeParent = deleteNode;

                if (minLeftNode.getLeft() != null) {
                    //ищем самый левый элемент в правом поддереве
                    while (minLeftNode.getLeft() != null) {
                        minLeftNodeParent = minLeftNode;
                        minLeftNode = minLeftNode.getLeft();
                    }

                    // убираем из дерева самый левый элемент
                    if (minLeftNode.getRight() != null) { //если у самого левого элемента есть правый сын
                        minLeftNodeParent.setLeft(minLeftNode.getRight());
                    } else { //если у самого левого элемента нет правого сына
                        minLeftNodeParent.setLeft(null);
                    }

                    minLeftNode.setRight(deleteNode.getRight());
                }

                minLeftNode.setLeft(deleteNode.getLeft());

                if (deleteNodeParent == null) { // если удаляем корень
                    root = minLeftNode;
                } else if (deleteNodeParent.getLeft() == deleteNode) {
                    deleteNodeParent.setLeft(minLeftNode);
                } else {
                    deleteNodeParent.setRight(minLeftNode);
                }
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
            System.out.println(element.getData());
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
            if (element != null) {
                System.out.println(element.getData());

                if (element.getRight() != null) {
                    stack.addLast(element.getRight());
                }
                if (element.getLeft() != null) {
                    stack.addLast(element.getLeft());
                }
            }
        }
    }

    public void depthFirstSearchRec() { // обход в глубину с рекурсией
        depthFirstSearchRecMethod(root);
    }

    private void depthFirstSearchRecMethod(TreeNode<T> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getData());

        TreeNode<T> leftChild = node.getLeft();
        depthFirstSearchRecMethod(leftChild);
        TreeNode<T> rightChild = node.getRight();
        depthFirstSearchRecMethod(rightChild);
    }
}
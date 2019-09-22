package guk.treeMain;

import guk.tree.Tree;
import org.w3c.dom.ls.LSOutput;

import java.util.function.Consumer;

public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> binaryTree = new Tree<>(5);
        int[] array = new int[]{2, 10, 1, 3, 6, 15, 14, 20, 12, 13};

        for (int e : array) {
            binaryTree.add(e);
        }

        System.out.println(binaryTree);
        System.out.println("Размер дерева = " + binaryTree.size());

        Consumer<Integer> printer = System.out::println;
        binaryTree.breadthFirstSearch(printer);
        binaryTree.depthFirstSearch(printer);
        binaryTree.depthFirstSearchRec(printer);

        System.out.println(binaryTree);
        System.out.println("Количество узлов в дереве = " + binaryTree.size());

        binaryTree.deleteNode(2);
        System.out.println("Удалили 2: " + binaryTree); //удаляем элемент без левого поддерева в правом потомке
        System.out.println("Количество узлов в дереве = " + binaryTree.size());

        binaryTree.deleteNode(10);
        System.out.println("Удалили 10: " + binaryTree);
        System.out.println("Количество узлов в дереве = " + binaryTree.size());

        binaryTree.deleteNode(5);
        System.out.println("Удалили корень: " + binaryTree);
        System.out.println("Количество узлов в дереве = " + binaryTree.size());

        binaryTree.add(null);
        System.out.println(binaryTree);
    }
}

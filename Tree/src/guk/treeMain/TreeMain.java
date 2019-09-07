package guk.treeMain;

import guk.tree.Tree;
import guk.tree.TreeNode;

public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> binaryTree = new Tree<>(new TreeNode<>(5, null, null));
        int[] array = new int[]{2, 10, 1, 3, 6, 15, 14, 20, 12, 13};
        for (int e : array) {
            binaryTree.add(e);
        }
        System.out.println(binaryTree);
        System.out.println("Размер дерева = " + binaryTree.size());

       /* binaryTree.deleteNode(5);
        System.out.println(binaryTree);*/

   binaryTree.depthFirstSearchWithRecursion();

      /*  binaryTree.add(2);
        binaryTree.add(10);
        binaryTree.add(6);
        binaryTree.add(15);
        System.out.println(binaryTree);

        binaryTree.deleteNode(10);
        System.out.println(binaryTree);*/

    }
}

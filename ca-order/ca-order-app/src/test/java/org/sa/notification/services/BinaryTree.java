package org.sa.notification.services;

/**
 * Created by Vivek on 25-02-2017.
 */
public class BinaryTree {

    private static int counter=0;

    public static void main(String[] args){
        counter=0;
        int[] keys={2,1,3};
        createBST(keys);
    }


    public static void createBST(int[] keys) {
        Node tree=null;
        for(int key:keys){

            if(tree!=null){
                insertNode(tree,new BinaryTree.Node(key));
            }else{
                tree=new BinaryTree.Node(key);
            }

            System.out.println(counter);
            //  tree = insertNode(tree, new Node(key));
        }
    }




// insertRootKey

    private static  Node insertNode(Node currentParent, Node newNode) {
        counter++;
        if (newNode.key > currentParent.key) {
            currentParent.right = insertNode(currentParent.right, newNode);
        } else if (newNode.key < currentParent.key) {
            currentParent.left = insertNode(currentParent.left, newNode);
        }
        return currentParent;
    }

    class Node{
        public Integer key;
        public Node right;
        public Node left;

        public Node(){
            super();
        }

        public Node(int key) {
            this.key=key;
        }
    }

}


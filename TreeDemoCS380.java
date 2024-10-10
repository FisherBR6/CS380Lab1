import java.util.ArrayList;
import java.util.Stack;

class Node{
    int value;
    Node left, right;

    public Node(int value){
        this.value = value;
        left = null;
        right = null;
    }

}

class BinarySearchTree{

    Node root;

    /**
     * inserts a node into the tree
     * @param value Value to be inserted into the BST
     **/
    public void insert(int value){
        //tree is empty
        if(root == null){
            root = new Node(value);
            return;
        }else{
            Node current = root;
            Node parent = null;

            while(true){
                parent = current;

                if(value < current.value){
                    current = current.left;
                    if(current == null){
                        parent.left = new Node(value);
                        return;
                    }
                }else{
                    current = current.right;
                    if(current == null){
                        parent.right = new Node(value);
                        return;
                    }
                }

            }//closing while

        }//closing main if-else
    }

    /**
     * Use pre-order traversal to display the BST
     * @param root  root value of the BST
     **/
    public void preOrderTraversal(Node root){
        //base case
        if(root == null){
            return;
        }
        //print the current node value
        System.out.print(root.value+" ");
        //go left recursively
        preOrderTraversal(root.left);
        //go right recursively
        preOrderTraversal(root.right);
    }



    /**
     * Use in-order traversal to display the BST
     * @param root  root value of the BST
     **/
    public void inOrderTraversal(Node root){
        //base case
        if(root == null){
            return;
        }

        //go left recursively
        inOrderTraversal(root.left);
        //print the current node value
        System.out.print(root.value+" ");
        //go right recursively
        inOrderTraversal(root.right);
    }


    /**
     * Use post-order traversal to display the BST
     * @param root  root value of the BST
     **/
    public void postOrderTraversal(Node root){
        //base case
        if(root == null){
            return;
        }

        //go left recursively
        postOrderTraversal(root.left);
        //go right recursively
        postOrderTraversal(root.right);
        //print the current node value
        System.out.print(root.value+" ");

    }



    /**
     * Use pre-order traversal to find the desired key
     * @param root root value of the BST
     * @param key the value to search for in the BST
     **/
    public boolean find(Node root, int key){
        //if root is empty or desired value is not found return false
        if(root == null){
            return false;
        }
        //if desired value is found, return true
        if(root.value == key){
            return true;
        }
        //otherwise, traverse the tree
        else if(key < root.value){
            return find(root.left, key);
        }else{
            return find(root.right, key);
        }

    }



    /**
     * Retrieve the min value of the BST
     * @param root root value of the BST
     * @return return the min value as an int
     **/
    public int getMin(Node root){
        //initialize the min variable with the tree root value
        int min = root.value;
        if (root == null) return Integer.MIN_VALUE;

        //traverse the BST using a stack and an arraylist
        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();
        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;
                node = stack.pop();
                //if the current node value is less than the min value, update the min value
                if (node.value < min)
                    min = node.value;
                orderList.add(node.value);
                node = node.right;
            }
            else
            {
                stack.push(node);
                node = node.left;
            }
        }
        return min;
    }



    /**
     * Retrieve the max value of the BST
     * @param root root value of the BST
     * @return return the max value as an int
     **/
    public int getMax(Node root){
        //initialize the max value with the current root value
        int max = root.value;
        if (root == null) return Integer.MIN_VALUE;

        //traverse the BST using a stack and an arraylist
        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();
        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;

                node = stack.pop();
                //if the current node value is greater than the max value, update the max value
                if (node.value > max)
                    max = node.value;
                orderList.add(node.value);
                node = node.right;
            }
            else
            {
                stack.push(node);
                node = node.left;
            }
        }
        return max;
    }



    /**
     * Delete the desired value from the BST
     * @param root root value of the BST
     * @param key the BST value to be deleted
     * @return return the min value as an int
     **/
    public Node delete(Node root, int key){

        if(root == null){
            return root;
        }else if(key < root.value){
            root.left = delete(root.left, key);
        }else if(key > root.value){
            root.right = delete(root.right, key);
        }else{
            //node has been found
            if(root.left==null && root.right==null){
                //case #1: leaf node
                root = null;
            }else if(root.right == null){
                //case #2 : only left child
                root = root.left;
            }else if(root.left == null){
                //case #2 : only right child
                root = root.right;
            }else{
                //case #3 : 2 children
                root.value = getMax(root.left);
                root.left = delete(root.left, root.value);
            }
        }
        return root;
    }



}



public class TreeDemoCS380{
    public static void main(String[] args){
        BinarySearchTree t1  = new BinarySearchTree();
        t1.insert( 24);
        t1.insert(80);
        t1.insert(18);
        t1.insert(9);
        t1.insert(90);
        t1.insert(22);

        //display the results of the implemented functions
        System.out.print("\nin-order: ");
        t1.inOrderTraversal(t1.root);
        System.out.print("\npre-order: ");
        t1.preOrderTraversal(t1.root);
        System.out.print("\npost-order: ");
        t1.postOrderTraversal(t1.root);
        System.out.print("\nMin Value: " + t1.getMin(t1.root));
        System.out.print("\nMax Value: " + t1.getMax(t1.root));
        int findVal = 9;
        System.out.print("\nDoes Value " + findVal + " Exist in the BST? " + t1.find(t1.root, findVal));




    }
}

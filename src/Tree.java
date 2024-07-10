public class Tree {
    public TreeNode root;

    public static void preOrder(TreeNode node){
        if(node != null){
            System.out.print(node.name);
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public static void inOrder(TreeNode node){
        if(node != null){
            preOrder(node.left);
            System.out.print(node.name);
            preOrder(node.right);
        }

    }
    public static void postOrder(TreeNode node){
        if(node != null){
            preOrder(node.left);
            preOrder(node.right);
            System.out.print(node.name);
        }
    }
}

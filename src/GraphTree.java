//solutions => 252
//questions => 120
public class GraphTree {

    //Route Between Nodes: Given a directed graph,
    // design an algorithm to find out whether there is a
    //route between two nodes.
    //O(V+E) O(V)
    public boolean routeBetweenNodes(Graph g, Node start, Node end){
        if(start == end)
            return true;
        Queue<Node> queue = new LinkedList();
        queue.add(start);
        for(Node current : g.getNodes()){
            current.state = "unvisited";
        }
        start.state = "visiting";
        while(!queue.isEmpty()){
            Node current = queue.remove();
            for(Node neighbour : current.adjacent){
                if(neighbour.state == "unvisited"){
                    if(neighbour == end)
                        return true;
                    else {
                        neighbour.state = "visiting";
                        queue.add(neighbour);
                    }
                }
            }
            current.state = "visited";
        }
        return false;
    }

    //Minimal Tree: Given a sorted (increasing order) array with unique integer elements,
    // write an algorithm to create a binary search tree with minimal height.
    //[1,2,3,4,5,6,7,8,9]
    //          5
    //      3       7
    //    2   4   6   8
    //  1               9
    //binary search here!
    //left = binarySearch(leftHalf)
    //right = binarySearch(rightHalf)
    //108. Convert Sorted Array to Binary Search Tree
    //O(N) O(log(N))
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0)
            return null;
        return helper(nums,0,nums.length - 1);
    }
    public TreeNode helper(int[] nums, int low, int high){
        if(low > high)
            return null;
        int middle = low + (high - low)/2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = helper(nums,low,middle-1);
        root.right = helper(nums,middle+1,high);
        return root;
    }

}

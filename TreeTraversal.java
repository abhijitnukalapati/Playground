public static class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}

// pre-order traversal using recursion
private static void depthFirstSearch(TreeNode node) {
    if(node == null) {
      return;
    }
    System.out.println(node.value);

    depthFirstSearch(node.left);
    depthFirstSearch(node.right);
}

// queue implementation
public static void breadthFirstSearch(TreeNode node) {
    if(node == null) {
        return;
    }

    LinkedList<TreeNode> queue = new LinkedList<>();
    queue.add(node);

    while(!queue.isEmpty()) {
        TreeNode treeNode = queue.removeFirst();
        System.out.println(treeNode.value);

        if(treeNode.left != null) {
            queue.add(treeNode.left);
        }
        if(treeNode.right != null) {       
            queue.add(treeNode.right);
        }
    }
}

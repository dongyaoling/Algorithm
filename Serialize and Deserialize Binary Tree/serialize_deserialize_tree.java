/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. 
 * There is no restriction on how your serialization/deserialization algorithm should work. 
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure. 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    private void dfs(TreeNode root, StringBuilder sb){
        if (root == null){
            sb.append("null").append(",");
            return;
        }
        sb.append(String.valueOf(root.val)).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> list = new LinkedList<>();
        for (String s : data.split("\\,"))
            list.offer(s);
        return helper(list);
    }
    
    private TreeNode helper(Queue<String> list){
        String cur = list.poll();
        if (cur.equals("null"))
            return null;
        TreeNode node = new TreeNode(Integer.parseInt(cur));
        node.left = helper(list);
        node.right = helper(list);
        return node;
    }
}
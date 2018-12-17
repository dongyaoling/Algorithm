/**
 * Given an array of strings, group anagrams together.
 */

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i ++){
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String s = String.valueOf(c);
            if (map.containsKey(s)) map.get(s).add(strs[i]);
            else{
                map.put(s, new ArrayList<String>());
                map.get(s).add(strs[i]);
            }
        }
        for (String key : map.keySet()){
            res.add(map.get(key));
        }
        return res;
    }
}
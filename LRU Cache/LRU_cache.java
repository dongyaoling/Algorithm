class LRUCache {

    HashMap<Integer, Node> map;
    int capacity, count;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        map = new HashMap<Integer, Node>();
        this.capacity = capacity;
        count = 0;
        head = new Node(0, 0);
        head.pre = null;
        tail = new Node(0, 0);
        tail.next = null;
        head.next = tail;
        tail.pre = head;
    }
    
    public void delete(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //map.remove(node.key);
    }
    
    public void addHead(Node node){
        Node cur = head.next;
        head.next = node;
        node.next = cur;
        cur.pre = node;
        node.pre = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)){
            Node res= map.get(key);
            delete(res);
            addHead(res);
            return res.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            delete(map.get(key));
            addHead(map.get(key));
            map.get(key).value = value;
        } else if (count < capacity){
            count ++;
            Node node = new Node(key, value);
            addHead(node);
            map.put(key, node);
        } else {
            map.remove(tail.pre.key);
            delete(tail.pre);
            Node node = new Node(key, value);
            addHead(node);
            map.put(key, node);
        }
    }
}

class Node {
    int key;
    int value;
    Node pre;
    Node next;
    
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCache {

    int capacity;
    int len;
    HashMap<Integer, DoubleLinkedNode> map;
    DoubleLinkedNode head;
    DoubleLinkedNode tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.len = 0;
        this.map = new HashMap<Integer, DoubleLinkedNode>();
    }
    
    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        } else {
            DoubleLinkedNode node = map.get(key);
            removeNode(node);
            setHead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            DoubleLinkedNode oldNode = map.get(key);
            oldNode.value = value;
            removeNode(oldNode);
            setHead(oldNode);
        } else if(this.len < this.capacity) {
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            setHead(newNode);
            map.put(key, newNode);
            this.len++;
        } else {
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            map.remove(this.tail.key);
            removeTail();
            setHead(newNode);
            map.put(key, newNode);
        }
    }
    
    public void setHead(DoubleLinkedNode node) {
        node.next = this.head;
        node.prev = null;
        
        if(this.head != null) {
            this.head.prev = node;
        }
        
        this.head = node;
        
        if(this.tail == null) {
            this.tail = node;
        }
    }
    
    public void removeNode(DoubleLinkedNode node) {
        DoubleLinkedNode prev = node.prev;
        DoubleLinkedNode next = node.next;
        
        //SET: prev.next = next
        if (prev == null) {
            this.head = next;
        } else {
            prev.next = next;
        }
        
        //SET: next.pre = prev
        if (next == null) {
            this.tail = prev;
        } else {
            next.prev = prev;
        }
    }
    
    public void removeTail() {
        this.tail = this.tail.prev;
        if(this.tail != null) {
            this.tail.next = null;
        }
    }
    
    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode prev;
        DoubleLinkedNode next;
        DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
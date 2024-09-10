import java.security.PublicKey;
import java.util.LinkedList;
import java.util.*;
class myHashMap<K,V>{
    private final int INITIAL_CAPACITY=16;
    private LinkedList<Node<K,V>> []buckets=new LinkedList[INITIAL_CAPACITY];
    class Node<K,V>{
        K key;
        V value;
        Node(K key, V value)
        {
            this.key=key;
            this.value=value;
        }
    }
    public myHashMap()
    {
        for(int i=0;i<INITIAL_CAPACITY;i++)
        {
            buckets[i]=new LinkedList<>();
        }
    }
   private int getBucketIndex(K key)
   {
       return Math.abs(key.hashCode()%INITIAL_CAPACITY);
   }
   //(Alice,1)
   public void put(K key,V value)
   {
       int index=getBucketIndex(key);
       LinkedList<Node<K,V>>bucket=buckets[index];
       for(Node<K,V>node:bucket)
       {
           if(node.key.equals(key))
           {
               node.value=value;
               return;
           }
       }
       bucket.add(new Node<>(key,value));
   }
   public V get(K key)
   {
       int index=getBucketIndex(key);
       LinkedList<Node<K,V>>bucket=buckets[index];
       for(Node<K,V>node:bucket)
       {
           if(node.key.equals(key))
           {
               return node.value;
           }
       }
       return null;
   }
   public void remove(K key)
   {
       int index=getBucketIndex(key);
       LinkedList<Node<K,V>>bucket=buckets[index];
       for (Node<K, V> node : bucket) {
           if (node.key.equals(key)) {
               bucket.remove(node);
               return;
           }
       }
   }
}

public class Disjoint {
    public static void main(String[] args) {
        myHashMap<String,Integer>hm=new myHashMap<>();
        hm.put("deepak",1);
        hm.put("anil",2);
        System.out.println(hm.get("deepak"));
        System.out.println(hm.get("anil"));
        hm.remove("anil");
        System.out.println(hm.get("anil"));    }
}

// Reashing
import java.util.LinkedList;

class MyHashMap<K, V> {
    private final float LOAD_FACTOR = 0.75f; 
    private int size = 0; 
    private int capacity; 
    private LinkedList<Node<K, V>>[] buckets;

    static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public MyHashMap() {
        this.capacity = 16; 
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
        size++;
        if ((float) size / capacity > LOAD_FACTOR) {
            rehash();
        }
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return node.value; 
            }
        }

        return null; 
    }
    public void remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];
        Node<K, V> toRemove = null;
        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                toRemove = node;
                break;
            }
        }

        if (toRemove != null) {
            bucket.remove(toRemove);
            size--;
        }
    }
    public boolean containsKey(K key) {
        int index = getBucketIndex(key);
        LinkedList<Node<K, V>> bucket = buckets[index];

        for (Node<K, V> node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }
    private void rehash() {
        System.out.println("Rehashing...");
        int newCapacity = capacity * 2;
        LinkedList<Node<K, V>>[] newBuckets = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }

        for (LinkedList<Node<K, V>> bucket : buckets) {
            for (Node<K, V> node : bucket) {
                int newIndex = Math.abs(node.key.hashCode() % newCapacity);
                newBuckets[newIndex].add(new Node<>(node.key, node.value));
            }
        }

        buckets = newBuckets;
        capacity = newCapacity;
    }
}

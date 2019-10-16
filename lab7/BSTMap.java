import java.util.Iterator;
import java.util.Set;

/** K Key must implement hashcode & equals */
public class BSTMap<K,V> implements Map61B {
    private class Tree<K,V> {
        K key;
        V value;
        private Tree left;
        private Tree right;

        public Tree(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private Tree bstmap;
    private int size;
    /**  storage the height of the tree  */
    private int height;

    public BSTMap() {
        size = 0;
        height = 0;
    }


    /**
     * Thought it should be iterate to save memory
     * However I couln't reject the elegant of the recursion so that't is
     * Height should set to 1
     */

    private void leafBuilder(Tree tree, int height,int size) {
        Tree pointer = tree;
        if (tree.left == null || tree.right == null) {
            return;
        }
        for (int i = 0;;i++) {
            if (i == size / Math.pow(2, height + 1)) {
                tree.left = pointer;
            }
            pointer = pointer.left;
        }
        for (int i = 0;;i++)
            if (i == size / Math.pow(2, height + 1) + Math.pow(2, height)) {
                tree.right = pointer;
            }
        leafBuilder(tree.left, height + 1, size);
        leafBuilder(tree.right,height + 1, size);
    }

    public void treeBuilder(BSTMap bst, int height) {
        Tree temp,pointer = bst.bstmap;
        if (bst.size == 0) {
            return;
        }
        for (int i = 0; ; i++) {
            if (i == bst.size / Math.pow(2, height)) {
                bst.bstmap = pointer;
                break;
            }
        }
        leafBuilder(bst.bstmap,height,bst.size);
    }
    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        bstmap = null;
        System.gc();
        size = 0;
        height = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Tree pointer = bstmap;
        int temp;
        while (true) {
            if (pointer == null) {
                return false;
            } else {
                temp = pointer.key.compareTo(key);
                if (temp == 0) {
                    return true;
                } else if (temp > 0) {
                    pointer = pointer.left;
                } else pointer = pointer.right;
            }
        }
    }

    /** Return the value of input key;
     * @Warning if key is not contain in the BST it will return null
     */
    @Override
    public Object get(Object key) {
        Tree pointer = bstmap;
        while (true) {
            if (pointer == null) {
                return null;
            } else if (pointer.key.equals(key)) {
                return pointer.value;
            } else if (pointer.key.hashCode() > key.hashCode()) {
                pointer = pointer.left;
            } else pointer = pointer.right;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Object key, Object value) {
        Tree pointer = bstmap;
        Tree previous = null;
        while (true) {
            if (pointer == null) {
                pointer = new Tree(key,value);
                    if (previous.key.hashCode() > key.hashCode()) {
                        previous.left = pointer;
                    } else {
                        previous.right = pointer;
                    }
                return;
            } else if (pointer.key.equals(key)) {
                return;
            } else if (pointer.key.hashCode() > key.hashCode()) {
                previous =pointer;
                pointer = pointer.left;
            } else {
                previous = pointer;
                pointer = pointer.right;
            }
        }
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
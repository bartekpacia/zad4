package zad4;

public class Node {
    /**
     * data for the node
     */
    private int value;
    /**
     * next node in the list
     */
    private Node next;

    /**
     * number of times this node was accessed
     */
    private int accessCount;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    public void addAccess() {
        this.accessCount++;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public int delete(int value) {
        int deleted = 0;
        while (next != null && next.getValue() == value) {
            deleted++;
            next = next.getNext();
        }
        return deleted;
    }
}
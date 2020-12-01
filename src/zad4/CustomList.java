package zad4;

import java.util.Objects;

public class CustomList {
    private Node root;
    private int size;

    public CustomList() {
        root = null;
        size = 0;
    }

    /**
     * Adds the new node to the list
     *
     * @param value value that will be added to the list
     */
    public void add(int value) {
        Node node = root;
        Node newNode = new Node(value);
        // we have the empty list, so add the new node as the root
        if (root == null) {
            root = newNode;
        } else {
            // traverse through the list to find the last node
            while (node.getNext() != null) {
                node = node.getNext();
            }
            // add the new node after the last one
            node.setNext(new Node(value));
        }
        // increase the size of the list
        size++;
    }

    /**
     * Removes all items from the list with a given value
     *
     * @param - value present in nodes
     * @return number of removed elements
     */
    public int remove(int value) {
        int rozmiar = size;//we define rozmiar as the size before removing any nodes
        //we decide which elements are the same as the given value
        while (root.getValue() == value) {
            Node d = Objects.requireNonNull(root, "The list is empty!");
            root = d.getNext();
            size--;
        }
        Node current = root;
        //then we delete them from the list and create a smaller list
        while (current != null) {
            int del = current.delete(value);
            size -= del;
            current = current.getNext();
        }
        //finally, we return the number of removed elements by subtracting
        // the size of the list before removing elements from the size at the end.
        return rozmiar - size;
    }

    /**
     * Removes the root from the list.
     *
     * @return true if the operations was done correctly, false otherwise
     */
    public boolean pop() {
        int rozmiar = size;//we define rozmiar the same way we did it in remove method
        boolean popped = false;//we define a boolean
        Node d = Objects.requireNonNull(root, "false");//error if the list is empty
        root = d.getNext();//we remove the root
        size--;
        if (rozmiar - size == 1) {
            return !popped;//we return true if we removed ne element (root)
        } else {
            return popped;//otherwise we return false
        }
    }

    /**
     * Returns the size of the list
     *
     * @return the integer saying, how many elements the list contains
     */
    public int length() {
        return size;
    }

    /**
     * Finds a node in the list with the given value
     *
     * @param value what value should be found
     * @return first node with a given value or null if none is present in the list
     */
    public Node getNode(int value) {
        Node prev = null;
        Node current = root;

        while (current != null) {
            if (prev != null && (prev.getAccessCount() < current.getAccessCount())) {
                int savedPrev = prev.getValue();
                prev.setValue(current.getValue());
                current.setValue(savedPrev);
            }

            if (current.getValue() == value) {
                current.addAccess();
                return current;
            }

            prev = current;
            current = current.getNext();
        }
        return null;
    }

    public Node getRoot() {
        root.addAccess();
        return root;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node node = root;
        while (node != null) {
            s.append(node.getValue());
            s.append(" ");
            node = node.getNext();
        }
        return s.toString();
    }

    public static void main(String[] args) {
        CustomList customList = new CustomList();
        customList.add(5);
        customList.add(7);
        customList.add(9);
        customList.add(5);
        customList.add(1);
        customList.add(3);
        customList.add(9);

        System.out.println("List: " + customList);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        customList.getNode(1);
        System.out.println("List: " + customList);


        ////

        System.out.println("Size: " + customList.length());
        int value = 9;
        System.out.println("Looking for node with value: " + value);
        Node node = customList.getNode(value);
        System.out.println("Found node: " + node);
        int newValue = 56;
        System.out.println("List before adding value " + newValue + " : " + customList);
        node.setValue(newValue);
        System.out.println("List after adding value " + newValue + " : " + customList);
        // TODO: Write tests in here:
        System.out.println("Number of removed nodes: " + customList.remove(5));//removing multiple elements
        System.out.println("List after removing 5 : " + customList);
        System.out.println("Size:" + customList.length());
        System.out.println("We removed the root: " + customList.pop());//removing root
        System.out.println("List after removing root: " + customList);
        System.out.println("Size: " + customList.length());
        System.out.println("Number of removed nodes: " + customList.remove(45));//removing non-existing element
        System.out.println("List: " + customList);
        customList.remove(9);//removing existing element
        customList.add(9);//add after remove
        System.out.println("List: " + customList);
        System.out.println(customList.getRoot());
        System.out.println(customList);
    }
}

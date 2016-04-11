public class LinkedList<T>  {


    Node<T> itsFirstNode;
    Node<T> itsLastNode;
    private int size;


    public LinkedList() {

        itsFirstNode = null;
        itsLastNode = null;
        size = 0;
    }

    
    public Iterator<T> getIterator() {
        return new Iterator(this);
    }

    // THIS WILL NEED TO BE MODIFIED FOR DOUBLY LINKED LIST
    public void add(T element) {

        Node<T> node = new Node(element);

        if (itsFirstNode == null) {
            itsFirstNode = node;
            itsLastNode = node;
        }
        else {
            itsLastNode.setNextNode(node);
            Node<T> temp = itsLastNode;
            itsLastNode = node;
            itsLastNode.setPriorNode(temp);
        }
        size++;
    }

    // THIS WILL NEED TO BE MODIFIED FOR DOUBLY LINKED LIST
    public void add(T element, int index) {
        int counter = 0;
        Node<T> newNode = new Node(element);
        Node<T> current = itsFirstNode;
        while (current.getNextNode() != null ) {
            if (counter == index - 1 )
                break;
            current = current.getNextNode();
            counter++;
        }
        newNode.setNextNode(current.getNextNode());
        current.getNextNode().setPriorNode(newNode);
        current.setNextNode(newNode);
        newNode.setPriorNode(current);

        size++;
    }

    public T get(int index) {

        int counter = 0;
        Node<T> current = itsFirstNode;
        while (current.getNextNode() != null ) {
            if (counter == index)
                break;
            current = current.getNextNode();
            counter++;
        }
        return current.getData();
    }

    // TO BE IMPLEMENTED
    // returns true if element is in the list, false if not
    public boolean contains(T element) {
        Node<T> currNode = itsFirstNode;
        while (currNode != null) {
            if (currNode.data == element) return true;
            currNode = currNode.getNextNode();
        }
        return false;
    }

    // returns the index of the element if it is in the list, -1 if not found
    public int indexOf(T element) {
        Node<T> currNode = itsFirstNode;
        int ix = 0;
        while (ix < size) {
            if (currNode.data == element) return ix;
            currNode = currNode.getNextNode();
            ix++;
        }
        return -1;
    }

    // returns an Iterator at the location of the element if it is in the list
    // returns the null reference if the element is not found
    public Iterator<T> iteratorAt(T element) {
        int index = indexOf(element);
        if (index == -1) return null;
        Iterator<T> it = getIterator();
        for (int i = 0; i < index; i++){
            it.next();
        }
        return it;
    }

    public String toString() {
        String returnVal = "";
        Node<T> current = itsFirstNode;
        if (size != 0 ) {
            while (current.getNextNode() != null ) {
                returnVal += current.toString();
                returnVal += "\n";
                current = current.getNextNode();
            }
            returnVal += current.toString();
        }
        return returnVal;
    }  // end toString

    class Node<T> {
    
        private T data;
        private Node<T> itsNext;
        private Node<T> itsPrior;
    
        public Node(T data) {
            itsNext = null;
            itsPrior = null;
            this.data = data;
        }
    
    
        public T getData() {
            return this.data;
        }
    
        public Node<T> getNextNode() {
            return itsNext;
        }

        // TO BE IMPLEMENTED
        public Node<T> getPriorNode() {
            return itsPrior;
        }
    
        public void setNextNode(Node<T> next) {
            itsNext = next;
        }

        public void setPriorNode(Node<T> prior) {
            itsPrior = prior;
        }
    
        public String toString() {
            return data.toString();
        }
    
    }  // end of Node<T>
}

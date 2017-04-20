
/**
 * Created by olwin on 3/5/17.
 */
public class linkedListQueue {
    listNode head;
    listNode tail;

    linkedListQueue() {
        listNode dummy = new listNode(0);
        head = tail = dummy;
    }

    void addTail(listNode node) {
        node.next = tail.next;
        tail.next = node;
        tail = node;
    }


    listNode deleteHead() {
        listNode toDeleteNode = head.next;
        head.next = toDeleteNode.next;
        if (tail == toDeleteNode) tail = head;
        return toDeleteNode;
    }

    public boolean isEmpty() {
        return (head == tail);
    }

    String printQueue() {
        String print="";
        if (!isEmpty()) {
            listNode node = head.next;
            while (node != null) {
                print+= node.Data + " ";
                node = node.next;
            }
        }
        return print;
    }

}

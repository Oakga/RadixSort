/**
 * Created by olwin on 3/5/17.
 */
public class linkedListStack {
    listNode Top;

    linkedListStack() {
        Top = null;
    }

    void push(listNode node) {
        if (isEmpty()) {
            node.next = null;
        } else {
            node.next = Top;
        }
        Top = node;
    }

    listNode pop() {
        if(isEmpty())return null;
        else {
            listNode node=Top;
            Top=Top.next;
            return node;
        }
    }


    boolean isEmpty(){return (Top==null);}

    String printStack() {
        String print="";
        print+="Stack: ";
        if(!isEmpty()) {
            listNode node = Top;
            while (node.next != null) {
                print+=node.Data + " ";
                node = node.next;
                if (node.next == null) print+=node.Data + " ";//last node
            }
        }
        return print;
    }
}

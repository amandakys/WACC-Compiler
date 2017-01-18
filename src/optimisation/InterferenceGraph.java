package optimisation;

import back_end.data_type.register.Register;
import main.Visitor;

import java.util.ArrayList;
import java.util.List;

public class InterferenceGraph {
    private static List<IGNode> nodes = Visitor.ST.getNodes();

    public static void checkLiveness() {
        for(int i = 0; i < nodes.size(); i++) {
            IGNode first = nodes.get(i);

            //compare the an element to the rest of the list
            for(int j = i + 1; j < nodes.size(); j++) {
                IGNode next = nodes.get(j);

                //when two IGNodes have overlap live span
                if(doesOverlap(first, next)) {
                    first.addEdge(next);
                }
            }
        }
    }

    private static boolean doesOverlap(IGNode x, IGNode y) {
        IGNode first, next;
        //guarantee that first IGNode will always be smaller than the next IGNode
        if(x.getFrom() > y.getFrom()) {
            first = x;
            next = y;
        } else {
            first = y;
            next = x;
        }

        return first.getFrom() <= next.getTo() && next.getFrom() <= first.getTo()
                && next.getFrom() != first.getTo();
    }

    public static IGNode findIGNode(String name, int index) {
        //check every node that is in the Interference Graph
        for(IGNode n : nodes) {
            //when it has the same name
            if( n.getIdentifier() != null && n.getIdentifier().equals(name)) {
                return n;
            }
        }

        IGNode newNode = new IGNode(name);
        newNode.setFrom(index);
        nodes.add(newNode);
        return newNode;
    }

    public static List<IGNode> getNodes() {
        return nodes;
    }
}

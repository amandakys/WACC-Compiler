package optimisation;

import back_end.data_type.register.Register;

import java.util.ArrayList;
import java.util.List;

public class InterferenceGraph {
    private static List<IGNode> nodes = new ArrayList<>();

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

    private static boolean doesOverlap(IGNode first, IGNode next) {
        //guarantee that first IGNode will always be smaller than the next IGNode
        if(first.getFrom() > next.getFrom()) {
            IGNode tmp = first;
            first = next;
            next = tmp;
        }

        return first.getFrom() <= next.getTo() && next.getFrom() <= first.getTo()
                && next.getTo() != first.getFrom();
    }

    public static IGNode findIGNode(String name) {
        //check every node that is in the Interference Graph
        for(IGNode n : nodes) {
            //when it has the same name
            if( n.getIdentifier() != null && n.getIdentifier().equals(name)) {
                return n;
            }
        }

        IGNode newNode = new IGNode(name);
        nodes.add(newNode);
        return newNode;
    }

    public static List<IGNode> getNodes() {
        return nodes;
    }
}

package optimisation;

import back_end.data_type.register.Register;

import java.util.ArrayList;
import java.util.List;

public class InterferenceGraph {
    private List<IGNode> nodes = new ArrayList<>();

    public void checkLiveness() {
        for(int i = 0; i < nodes.size(); i++) {
            IGNode first = nodes.get(i);

            //compare the an element to the rest of the list
            for(int j = i + 1; j < nodes.size(); j++) {
                IGNode next = nodes.get(j);

                //when two IGNodes have overlap live span
                if(first.getFrom() <= next.getTo() && next.getFrom() <= first.getTo()
                        && (first.getFrom() != 0 && next.getFrom() != 0
                        && first.getTo() != 0 && next.getTo() != 0)) {
                    first.addEdge(next);
                }
            }
        }
    }

    public Register findRegister(String name) {
        IGNode node = findIGNode(name);
        if(node != null) {
            return node.getRegister();
        }

        return null;
    }

    public IGNode findIGNode(String name) {
        for(IGNode n : nodes) {
            if(n.getIdentifier().equals(name)) {
                return n;
            }
        }

        return null;
    }

    public void add(IGNode node) {
        nodes.add(node);
    }

    public List<IGNode> getNodes() {
        return nodes;
    }
}

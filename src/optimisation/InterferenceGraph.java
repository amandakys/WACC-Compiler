package optimisation;

import back_end.data_type.register.Register;
import front_end.symbol_table.IDENTIFIER;

import java.util.ArrayList;
import java.util.List;

public class InterferenceGraph {
    public static List<IGNode> nodes = new ArrayList<>();

    public static void checkLiveness() {
        for(int i = 0; i < nodes.size(); i++) {
            IGNode first = nodes.get(i);

            //compare the an element to the rest of the list
            for(int j = i + 1; j < nodes.size(); j++) {
                IGNode next = nodes.get(j);

                //when two IGNodes have overlap live span
                if(first.getFrom() <= next.getTo() && next.getFrom() <= first.getTo()) {
                    first.addEdge(next);
                }
            }
        }
    }

    public static Register findRegister(String name) {
        for(IGNode n : nodes) {
            if(n.getIdentifier().equals(name)) {
                return n.getRegister();
            }
        }

        return null;
    }
}

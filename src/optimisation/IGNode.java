package optimisation;

import back_end.data_type.register.Register;

import java.util.ArrayList;
import java.util.List;

/*
    The arbitrary node that represents the number of next register that will be used
 */
public class IGNode {
    private String identifier;
    //represent the designated register that will be used
    private Register register;
    //represent the range in which the object is alive
    private int from = 0, to = 0;
    private List<IGNode> neighbours = new ArrayList<>();

    public IGNode(String identifier) {
        this.identifier = identifier;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
       this.register = register;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addEdge(IGNode node) {
        if(!neighbours.contains(node) && node != this) {
            neighbours.add(node);
            node.neighbours.add(this);
        }

    }

    public void addAll(IGNode node) {
        addEdge(node);

        for(IGNode n : neighbours) {
            n.addEdge(node);
        }
    }

    public List<IGNode> getNeighbours() {
        return neighbours;
    }
}


package optimisation;

import back_end.data_type.register.Register;

import java.util.ArrayList;
import java.util.List;

/*
    The arbitrary node that represents the number of next register that will be used
 */
public class IGNode {
    private List<String> identifierList = new ArrayList<>();
    //represent the designated register that will be used
    private Register register;
    //represent the range in which the object is alive
    private int from = 0, to = 0;
    private List<IGNode> neighbours = new ArrayList<>();

    public IGNode(String identifier) {
        this.identifierList.add(identifier);
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

    public void addEdge(IGNode node) {
        if(!neighbours.contains(node) && node != this) {
            neighbours.add(node);
            node.neighbours.add(this);
        }
    }

    public List<IGNode> getNeighbours() {
        return neighbours;
    }

    public List<String> getIdentifierList() {
        return identifierList;
    }

    public void setNeighbours(List<IGNode> neighbours) {
        this.neighbours = neighbours;
    }

    public void addIdentifier(String identifier) {
        this.identifierList.add(identifier);
    }
}


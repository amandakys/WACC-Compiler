package optimisation;

import java.util.ArrayList;
import java.util.List;

/*
    The arbitrary node that represents the number of next register that will be used
 */
public class IGNode {
    //represent the name of the variable
    private String identifier;
    //represent the designated register that will be used
    private IGRegister register;
    //represent the range in which the object is alive
    private int from = 0, to = 0;
    private List<IGNode> neighbours = new ArrayList<>();

    public IGNode(String identifier) {
        this.identifier = identifier;
    }

    public IGRegister getRegister() {
        return register;
    }

    public void setRegister(IGRegister register) {
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
        neighbours.add(node);
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<IGNode> getNeighbours() {
        return neighbours;
    }
}


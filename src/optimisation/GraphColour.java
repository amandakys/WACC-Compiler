package optimisation;

import back_end.data_type.register.Register;

public class GraphColour {
    //represent how many registers are there in the program currently
    private static int currRegister = 0;

    //colour the graph using greedy algorithm
    public static void colouringGraph() {
        for(IGNode node : InterferenceGraph.nodes) {

            for(int i = 0; i <  currRegister; i++) {
                //if the current register can be used (i.e no neighbour use same register)
                if(canBeColored(node, i)) {
                    //use the next available register
                    node.setRegister(new Register(i));
                    break;
                }
            }

            //make another register and use it
            if(node.getRegister() == null) {
                node.setRegister(new Register(currRegister));
                currRegister++;
            }
        }
    }

    //this function returns true when the inputted regNum is not occupied by inputted node's neighbours
    private static boolean canBeColored(IGNode node, int regNum) {
        for(IGNode neighbour : node.getNeighbours()) {
            if(neighbour.getRegister() != null &&
                    neighbour.getRegister().getIndex() == regNum) {
                return false;
            }
        }

        return true;
    }
}

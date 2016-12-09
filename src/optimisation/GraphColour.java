package optimisation;

import back_end.data_type.register.Register;

public class GraphColour {
    //colour the graph using greedy algorithm
    public static void colouringGraph() {
        //represent how many registers are there in the program currently
        int currRegister = 0;

        for(int i = 0; i <  InterferenceGraph.getNodes().size(); i++) {
            IGNode node = InterferenceGraph.getNodes().get(i);

            for(int j = 0; j <  currRegister; j++) {
                //if the current register can be used (i.e no neighbour use same register)
                if(canBeColored(node, j)) {
                    //use the next available register
                    node.setRegister(new Register(j));
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

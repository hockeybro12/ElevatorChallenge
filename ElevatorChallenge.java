import java.util.HashMap;

public class ElevatorChallenge {
    
    public static void main(String[] args) {
        //Elevator(int elevatorID, int floorNumber, int goalFloorNumber, int currentDirection, int floorCount) {
        
        //we can create multiple elevators using a hashmap
        //we won't have any problems with having too many, since there are only 16
        //this will allow for a O(1) lookup
        //the elevator ID is the key and the elevator is the entry
        //here is a simple example
        
        HashMap<Integer, Elevator> elevatorMap = new HashMap<Integer, Elevator>();
        
        for (int i = 0; i < 16; i++) {
            Elevator elevator = new Elevator(i, 0, 3, 5, 6);
            elevator.pickup(2, 5);
            elevator.pickup(3, 8);
            elevator.step();
            elevator.step();
            elevator.pickup(1, 5);
            elevator.step();
            elevatorMap.put(i, elevator);
        }
        //get the elevator from the hasmap
        Elevator resultElevator = elevatorMap.get(4);
        
        int statusArray[] = resultElevator.status();
        for (int i = 0; i < statusArray.length; i++) {
            //id, floornumber, goalfloornumber
            System.out.println(statusArray[i]);
        }
        
    }

}
import java.util.Comparator;
import java.util.PriorityQueue;


public class Elevator {
    
    //which elevator this is
    private int elevatorID;
    //where the elevator is currently at
    private int floorNumber;
    //where the elevator is going
    private int goalFloorNumber;
    //keep track of if this elevator is moving up or down
    private boolean currentDirectionUp;
    //total number of floors of elevator
    private int totalFloorCount;
    
    Comparator<Integer> increasingOrdercomparator = new IncreasingOrderComparator();
    Comparator<Integer> decreasingOrdercomparator = new DecreasingOrderComparator();
    //priority queue to keep track of the upward direction
    PriorityQueue<Integer> upwardDirectionQueue;
    //priority queue to keep track of the downward direction
    PriorityQueue<Integer> downwardDirectionQueue; 
    
    public Elevator() {
        this.elevatorID = 0;
        this.floorNumber = 0;
        this.goalFloorNumber = 0;
        this.currentDirectionUp = true;
        this.upwardDirectionQueue = new PriorityQueue<Integer>(10, increasingOrdercomparator);
        this.downwardDirectionQueue = new PriorityQueue<Integer>(10, decreasingOrdercomparator);
        this.totalFloorCount = 10;
    }
    
    public Elevator(int elevatorID, int floorNumber, int goalFloorNumber, int currentDirection, int floorCount) {
        this.elevatorID = elevatorID;
        this.floorNumber = floorNumber;
        this.goalFloorNumber = goalFloorNumber;
        if (currentDirection > 0) {
            this.currentDirectionUp = true;
        } else {
            this.currentDirectionUp = false;
        }
        this.upwardDirectionQueue = new PriorityQueue<Integer>(floorCount, increasingOrdercomparator);
        this.downwardDirectionQueue = new PriorityQueue<Integer>(floorCount, decreasingOrdercomparator);
        this.totalFloorCount = floorCount;
    }
    
    //return the status of the elevator
    public int[] status() {
        int returnArray[] = {this.elevatorID, this.floorNumber, this.goalFloorNumber};
        
        return returnArray;
    }
    
    //change the elevator states 
    public void update(int elevatorID, int floorNumber, int goalFloorNumber) {
        this.elevatorID = elevatorID;
        this.floorNumber = floorNumber;
        this.goalFloorNumber = goalFloorNumber;
        
        if (this.goalFloorNumber > this.floorNumber) {
            currentDirectionUp = true;
        } else {
            currentDirectionUp = false;
        }
    }
    
    //pick up some person. 
    public void pickup(int floor, int direction) {
        if (floor > this.floorNumber) {
            //we are moving up so add it to the up queue
            //or we are moving down, so add it to the up queue since we will hit it when we start moving up again
            //add it to the upward direction queue
            this.upwardDirectionQueue.add(floor);
        } else if (floor < this.floorNumber) {
            //we are moving down so add it to the downward queue 
            //or we are moving up so add it to the downward queue so we will encounter it when we start moving down
            this.downwardDirectionQueue.add(floor);
        } else {
            //we are moving up 
            //same floor as we are at so do nothing
            
        }
    }
    
    public void step() {
        if (currentDirectionUp == true) {
            //we are going up
            if (this.upwardDirectionQueue.peek() != null) {
                int nextFloor = this.upwardDirectionQueue.peek();
                if (nextFloor < this.floorNumber) {
                    //the next floor is less than the last floor, so we should start moving down now
                    currentDirectionUp = false;
                } else if (nextFloor >= this.totalFloorCount) {
                    //or we have reached the max floor
                    //remove this floor
                    this.upwardDirectionQueue.remove();
                    currentDirectionUp = false;
                } else {
                    //remove this floor
                    this.upwardDirectionQueue.remove();
                }
                this.floorNumber = nextFloor;
            } else {
               //there are no more floors to go to anymore so start going down
                currentDirectionUp = false;
                if (this.downwardDirectionQueue.peek() != null) {
                    int nextFloor = this.downwardDirectionQueue.poll();
                    //the next floor is more than the last floor, so we should start moving up now
                    if (nextFloor < this.floorNumber) {
                        currentDirectionUp = true;
                    } else if ( nextFloor <= 0) {
                        this.downwardDirectionQueue.remove();
                        currentDirectionUp = true;
                    } else {
                        this.downwardDirectionQueue.remove();
                    }
                    this.floorNumber = nextFloor;
                }
                
            }
        } else {
            //look at the downward queue since we are moving down
            if (this.downwardDirectionQueue.peek() != null) {
                int nextFloor = this.downwardDirectionQueue.poll();
                //the next floor is more than the last floor, so we should start moving up now
                if (nextFloor < this.floorNumber) {
                    currentDirectionUp = true;
                } else if ( nextFloor <= 0) {
                    this.downwardDirectionQueue.remove();
                    currentDirectionUp = true;
                } else {
                    this.downwardDirectionQueue.remove();
                }
                this.floorNumber = nextFloor;
            } else {
                //no more floors to go so start going up
                currentDirectionUp = true;
                if (this.upwardDirectionQueue.peek() != null) {
                    int nextFloor = this.upwardDirectionQueue.peek();
                    if (nextFloor < this.floorNumber) {
                        //the next floor is less than the last floor, so we should start moving down now
                        currentDirectionUp = false;
                    } else if (nextFloor >= this.totalFloorCount) {
                        //or we have reached the max floor
                        //remove this floor
                        this.upwardDirectionQueue.remove();
                        currentDirectionUp = false;
                    } else {
                        //remove this floor
                        this.upwardDirectionQueue.remove();
                    }
                    this.floorNumber = nextFloor;
                }
            }
            
        }
    }

}
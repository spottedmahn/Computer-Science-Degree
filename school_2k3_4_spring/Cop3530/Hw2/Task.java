/***************************************************************

** Michael DePouw

** COP3530

** Assignment Number: 2

** Date:3-3-03

***************************************************************/

/****************Program Description***************************

** Greedy algorithm implementation
** Please see COP3530Hmk02.doc for what the algorithm does.

**************************************************************/

import java.io.*;
import java.util.*;

public class Task{
	//start time and end time
	private int startT, endT;
	//name of the task
	private String task;
	//3 parameter constructor
	public Task(String in, int start, int end){
		startT = start;
		endT = end;
		task = in;
	}
	//main
	public static void main(String [] args) throws IOException{
		//geting input file name
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter input file name");
		BufferedReader fin = new BufferedReader(new FileReader(stdin.readLine()));
		//number of tasks
		int numberOfTasks = Integer.parseInt(fin.readLine());
		//used to store tasks
		Task[] tasks = new Task[lines];
		//reading in tasks to array
		for(int i=0;i<lines;i++){
			StringTokenizer token = new StringTokenizer(fin.readLine());
			tasks[i] = new Task(token.nextToken(), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
		}
		//stores finish times for particular room, the particular room is the index, so rooms[1] will store the finish time for room 1
		int [] rooms = new int[numberOfTasks];
		//intializing rooms array to -1, so I can if the room was used or not
		for(int i=0;i<rooms.length;i++){
			rooms[i] = -1;
		}
		//scheduling tasks
		for(int j=0;j<lines;j++){
			int earliestTime = 1001;
			int taskToSchedule = -1;
			//finding earliest start time, making greedy decision
			for(int i=0;i<tasks.length;i++){
				if(tasks[i] != null){
					if(tasks[i].startT < earliestTime){
						earliestTime = tasks[i].startT;
						taskToSchedule = i;
					}
				}
			}
			//System.out.println("j = "+j+" taskToSchedule = "+taskToSchedule);
			//find where to schedule task and scheduling it
			boolean done = false;
			int x = 0;
			while(!done){
				//if room available for task then schedule else move to next room
				if(rooms[x] < tasks[taskToSchedule].startT){
					rooms[x] = tasks[taskToSchedule].endT;
					//System.out.println("rooms["+x+"] = "+tasks[taskToSchedule].endT);
					tasks[taskToSchedule] = null;
					done = true;
					break;
				}
				else{
					x++;
				}
			}
		}//end j for loop
		//counting how many rooms used
		int roomsNeeded = 0;
		for(int i=0;i<rooms.length;i++){
			if(rooms[i] != -1)
				roomsNeeded++;
		}
		System.out.println("Rooms needed to schedule all tasks would be "+roomsNeeded);
	}
}
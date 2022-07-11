# Task: Robot programming

The task is to program a controller to a robot. 
Input has the following form: 

5 5        --- Size of the room that the robot will walk in.
1 2 N      --- Starting position of the robot and which direction it is faced.  
FLR...     --- Command to the robot where F = walk forward, L = Turn left, R = turn right

When the last command is entered, the controller reports the output like this:

Report: 2 4 N --- Current position, (x, y) = (2, 4) and faced North

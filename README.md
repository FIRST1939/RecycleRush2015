# RecycleRush2015
## Java Robot Code for FRC 2015
![](http://www.chiefdelphi.com/media/img/2f5/2f590b25cc784dd7399f7528f397153a_l.jpg)
## About the Robot
The 2015 game, Recycle Rush required robots to create stacks of totes with recycling containers on top.  These stacks were then placed on scoring platforms.  Our robot, the Knight Who Says Ni (Or just Ni), had an internal stacking mechanism that placed totes on top of other totes to form stacks.  The lifter could also pickup recycling containers for extra points.
## Mechanisms
### Drivetrain
The drivetrain used mecanum wheels for maximum maneuverability.  A combination of encoders on the wheels and a gyro allowed for precise autonomous movement.  The robot could drive exact distances and turn a precise number of degrees.  In the off-season the traditional gyro was replaced with a navX sensor board for improved reliability of measurements.
### Lifter
The lifter used a lead screw driven by two sim motors to move a carriage up and down.  The carriage had flaps on both sides to allow it to slide down over totes and catch on the totes when going up.  An encoder on the gearbox and a system with minimal slippage allowed for moving the lifter to precise height.  During the autonomous the lifter could move to an exact position.
### Tails
During the official season, the robot had two tails designed to grab recycling containers from the step during autonomous.  Both tails were connected to a spool on a window motor that could be spun to move the tails in or out.  A lack of sensors on this mechanism made autonomous movement unpredictable.  This lead to the removal of the tails during the off-season to save weight.
### Roller Claws
The roller claws hung out in front of the robot to suck totes into the robot.  Adding this mechanism part way through the season marked a change in strategy from collecting totes from the human player station to collecting totes from the landfill.  Originally the roller claws were moved in and out with a servo on each claw.  The Servos proved to be weak and prone to breaking.  In the off-season the moving mechanism was replaced with a motor that spun in and out to move the roller claws.

#include "ros/ros.h"
#include "geometry_msgs/Twist.h"
#include "nav_msgs/Odometry.h"
#include <math.h>
#include "tf/transform_datatypes.h"
#include <iostream>
// robots' coordinates
double xi = 0.0;
double yi = 0.0;
double angle = 0.0;



void OdomCallBack(const nav_msgs::Odometry::ConstPtr& pose_message){	 
	//declaring type of message
	nav_msgs::Odometry od_msg;
	//storing position of robot
	xi = pose_message->pose.pose.position.x;
	yi = pose_message->pose.pose.position.y;
	//getting yaw angle
	od_msg.pose.pose.orientation.w=pose_message->pose.pose.orientation.w;
	od_msg.pose.pose.orientation.x=pose_message->pose.pose.orientation.x;
	od_msg.pose.pose.orientation.y=pose_message->pose.pose.orientation.y;
	od_msg.pose.pose.orientation.z=pose_message->pose.pose.orientation.z;
	tf::Quaternion q(pose_message->pose.pose.orientation.x,pose_message->pose.pose.orientation.y,pose_message->pose.pose.orientation.z,pose_message->pose.pose.orientation.w);
	tf::Matrix3x3 m(q);
	double r,p,y;
	m.getRPY(r,p,y);
	angle=y;   

}

int main(int argc, char **argv){
	ros::init(argc, argv, "goto");
	ros::NodeHandle nh;
	
	ros::Subscriber sub = nh.subscribe("odom", 10, OdomCallBack);
	ros::Publisher velocity_publisher = nh.advertise<geometry_msgs::Twist>("cmd_vel", 10);
	
	ros::Rate loop_rate(10);
  	geometry_msgs::Twist vel_msg;

    //our destination variables
    double goal_x;
    double goal_y;
    std::cout << "input value of x: ";
    std::cin >> goal_x;
    std::cout << "input value of y: ";
    std::cin >> goal_y;

    while(ros::ok){


    	//subtracting the current position for the goal position to get the remaining distance
    	
    	double dec_x = goal_x - xi;
    	double dec_y = goal_y - yi;

    	//the goal angle is the arc tangent of remaining y/x
    	double goal_ang = atan2(dec_y, dec_x);

    	//if the angle is wrong, rotate
    	if( abs(goal_ang - angle) > 0.1){
    		vel_msg.linear.x = 0.0;
    		vel_msg.angular.z = 0.4;
    	}
    	//if the angle is right, go straight
    	else{
    		vel_msg.linear.x = 0.3;
    		vel_msg.angular.z = 0.0;
    	}	

    	

    	velocity_publisher.publish(vel_msg);
   	 	ros::spinOnce();
    	loop_rate.sleep();


    }





	return 0;
}

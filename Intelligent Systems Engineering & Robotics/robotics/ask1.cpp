#include "ros/ros.h"
#include "nav_msgs/Odometry.h"
#include "tf/transform_datatypes.h"

double xi, yi, yaw;

void OdomCallBack(const nav_msgs::Odometry::ConstPtr& pose_message){	 
	//getting x, y coordinates of robot
	xi = pose_message->pose.pose.position.x;
	yi = pose_message->pose.pose.position.y;

	//creating odometry type msg
	nav_msgs::Odometry od_msg;
	//storing orientation of robot and then converting to roll pitch yaw
	od_msg.pose.pose.orientation.w=pose_message->pose.pose.orientation.w;
	od_msg.pose.pose.orientation.x=pose_message->pose.pose.orientation.x;
	od_msg.pose.pose.orientation.y=pose_message->pose.pose.orientation.y;
	od_msg.pose.pose.orientation.z=pose_message->pose.pose.orientation.z;
	tf::Quaternion q(pose_message->pose.pose.orientation.x,pose_message->pose.pose.orientation.y,pose_message->pose.pose.orientation.z,pose_message->pose.pose.orientation.w);
	tf::Matrix3x3 m(q);
	double r,p,y;
	m.getRPY(r,p,y);
	yaw=y;   
	
	//ROS_INFO("yaw= %f", yaw);
}


int main(int argc, char **argv)
{
	//initializing node
	ros::init(argc, argv, "loc");
	ros::NodeHandle nh;
	//creating subscriber
	ros::Subscriber sub = nh.subscribe("odom", 10, OdomCallBack);
	// calling callback until ctrl+c from user
	ros::spin();

	return 0;
}



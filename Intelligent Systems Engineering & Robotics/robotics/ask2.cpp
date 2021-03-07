#include "ros/ros.h"
#include "geometry_msgs/Twist.h"
#include "nav_msgs/Odometry.h"
#include "tf/transform_datatypes.h"


//global x, y coordinates and angle=yaw for our robot
double xi = 0.0;
double yi = 0.0;
double angle = 0.0;

void OdomCallBack(const nav_msgs::Odometry::ConstPtr& pose_message){     
    
    xi = pose_message->pose.pose.position.x;
    yi = pose_message->pose.pose.position.y;

    nav_msgs::Odometry od_msg;
    od_msg.pose.pose.orientation.w=pose_message->pose.pose.orientation.w;
    od_msg.pose.pose.orientation.x=pose_message->pose.pose.orientation.x;
    od_msg.pose.pose.orientation.y=pose_message->pose.pose.orientation.y;
    od_msg.pose.pose.orientation.z=pose_message->pose.pose.orientation.z;
    tf::Quaternion q(pose_message->pose.pose.orientation.x,pose_message->pose.pose.orientation.y,pose_message->pose.pose.orientation.z,pose_message->pose.pose.orientation.w);
    tf::Matrix3x3 m(q);
    double r,p,y;
    m.getRPY(r,p,y);
    angle = y;  



}    

int main(int argc, char **argv)
{
    ros::init(argc, argv, "squa");
    ros::NodeHandle nh;
    
    ros::Publisher velocity_publisher;
    ros::Subscriber sub = nh.subscribe("odom", 10, OdomCallBack);

    
	velocity_publisher = nh.advertise<geometry_msgs::Twist>("cmd_vel", 10);
    
    ros::Rate loop_rate(10);


geometry_msgs::Twist vel_msg;

//8 loops for the 2 squares.1 for every side
for (int i = 0; i < 8; i++)
{
    //setting the distance of first square
    double distance = 10.0;
    if (i>3)
    {
        //setting the distance of second square
        distance = 5;
    }
    //setting the speed of robot
    double speed = 0.5;
    vel_msg.linear.x= speed;
   


//1st side of both squares
    
    if (i==0 ||i==4)
    {
       
    //go straight until xi==distance
    while( xi < distance){
        //Publish the velocity
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
        loop_rate.sleep();
    }
   

    //After the loop, stops the robot
    vel_msg.linear.x = 0;
    //Force the robot to stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();

    //setting rotation speed
    vel_msg.angular.z = 0.3; 
   
   //turn 90 degrees
    while(angle < 1.55){
        //Publish the rotaion velocity
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
        loop_rate.sleep();

    } 
    // stop the robot
    vel_msg.angular.z = 0 ; 
    // force the stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();
    }
    


//2nd side of both squares
    else if (i==1 || i==5)
    {
        //go straigt until yi==distance
        while( yi < distance){
            //Publish the velocity
            velocity_publisher.publish(vel_msg);
            ros::spinOnce();
            loop_rate.sleep();
    }
   

    //After the loop, stops the robot
    vel_msg.linear.x = 0;
    //Force the robot to stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();

    //setting rotation speed
    vel_msg.angular.z = 0.3; 
   
   //90 degree turn
    while(angle < 3.1){
        //publish rotation speed
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
    }
    // stopping the robot
    vel_msg.angular.z = 0 ; 
    // force the stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();
    
    }
   
//3rd side of both squares
    else if (i==2 || i==6)
    {
        //go straight until xi==0
        while( xi > 0){
        //Publish the velocity
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
        loop_rate.sleep();
    }
   

    //After the loop, stops the robot
    vel_msg.linear.x = 0;
    //Force the robot to stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();
    //setting rotation speed
    vel_msg.angular.z = 0.3; 

    //yaw values to make 90 degree turn
    while((angle < -1.6 && angle > -3.2) || (angle > 2 && angle < 4.6)){
        //publish rotation speed
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
        loop_rate.sleep();
    }
    //stop the robot
    vel_msg.angular.z = 0 ; 
    //force stop robot
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();
    
    }
//4th side of both squares
    else if (i==3 || i==7)
    {
        //go straight until yi==0
        while( yi > 0){
        //Publish the velocity
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
        loop_rate.sleep();
    }
   

    //After the loop, stops the robot
    vel_msg.linear.x = 0;
    //Force the robot to stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();
    //setting rotation speed
    vel_msg.angular.z = 0.3; 
   
   //yaw value for 90 degree turn
    while(angle < 0){
        //publish rotation velocity
        velocity_publisher.publish(vel_msg);
        ros::spinOnce();
    }
    //stop the robot
    vel_msg.angular.z = 0 ; 
    //force stop
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();
    
    }
   

}

 // stop the robot after squares are done
    vel_msg.angular.z = 0 ; 
    vel_msg.linear.x = 0 ;
    velocity_publisher.publish(vel_msg);
    ros::spinOnce();
    loop_rate.sleep();   
	

    return 0;
}



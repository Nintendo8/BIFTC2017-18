package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.team8745.OmniDriveRobot;


/**
 * Created by rose on 11/26/17.
 */
@Autonomous(name="Jewel North Blue")

public class BlueNorthJewel
        extends LinearOpMode {


    /* Declare OpMode members. */
    OmniDriveRobot robot = new OmniDriveRobot();
    private ElapsedTime elapsedTime = new ElapsedTime();
    private ElapsedTime waitTime = new ElapsedTime();

    //Speed to drive the robot forward at.
    static final double     kFORWARD_SPEED = -0.6;

    //Speed to drive the robot backward at.
    static final double     kBACKWARD_SPEED = 0.6;

    //Right strafing speed
    static final double     kRIGHT_SPEED = -0.6;

    //Right strafing speed
    static final double     kLEFT_SPEED = 0.6;

    //Variable for if the robot turns, determines the speed it turns at.
    static final double     kTURN_SPEED = 0.5;

    //Changes where the jewel servo moves to, scale of 0.0 to 1.0
    static final double     kJEWEL_TARGET = 0.0;

    static final double     kJEWEL_TIME = 2.0; // was 1.0, time to raise and lower the servo.

    //Changing this determines how long it drives left to park, in seconds.
    static final double     kLeftTime = 0.7;

    //Changing this determines how long it drives forwards to park, in seconds.
    static final double     kForwardsTime = 1.5; // was 1.5
    //Changing this determines how long it drives to knock off the jewel, in seconds.
    static final double     kTimeToKnockOff = 0.3; // was 0.3

    //Speeds

    //Speed it knocks the jewel off at.
    static final double     kKnockOffSpeed = 0.5; // was 0.3

    //Speed it drives at to park.
    static final double     kParkSpeed = 0.8; // was 0.8

    //boolean canPark = false;

    public void waitTime (double seconds){
        waitTime.reset();
        while (waitTime.seconds()<seconds && opModeIsActive()) {
            sleep(1);
        }
    }

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Lowers jewelServo
        robot.jewelServo.setPosition(kJEWEL_TARGET);
        elapsedTime.reset();
        while(elapsedTime.seconds()<kJEWEL_TIME && opModeIsActive()){
            sleep(1);
        }

        if (robot.jewelSensor.red() > robot.jewelSensor.blue()){
            robot.driveForwards(kKnockOffSpeed);
            waitTime(kTimeToKnockOff);

            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);

        } else if (robot.jewelSensor.blue() > robot.jewelSensor.red()) {
            robot.driveBackwards(kKnockOffSpeed);
            waitTime(kTimeToKnockOff);

            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        //When done
        robot.driveStop();
        robot.jewelServo.setPosition(1.0);
        elapsedTime.reset();
        waitTime(kJEWEL_TIME);

        //Drive left a little.
        robot.driveLeft(kParkSpeed);
        waitTime(kLeftTime);

        // Drive backwards.
        robot.driveBackwards(kParkSpeed);
        elapsedTime.reset();
        waitTime(kForwardsTime);

        robot.A.setPower(0);
        robot.B.setPower(0);
        robot.C.setPower(0);
        robot.D.setPower(0);

        //Updates telemetry to what the sensor sees.
        telemetry.addData("Red",robot.jewelSensor.red());
        telemetry.addData("Green",robot.jewelSensor.green());
        telemetry.addData("Blue",robot.jewelSensor.blue());
        telemetry.update();

        robot.jewelServo.setPosition(1.0);
    }

}
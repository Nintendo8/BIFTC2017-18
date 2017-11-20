package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by rose on 11/10/17.
 */
@Autonomous(name="Jewel Red")

public class RedJewel extends LinearOpMode {


    /* Declare OpMode members. */
    OmniDriveRobot robot = new OmniDriveRobot();
    private ElapsedTime elapsedTime = new ElapsedTime();

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

    //Changing this determines how long it drives forwards to park, in seconds.
    static final double     kTimeToDrive = 3.0; // was 1.5
    //Changing this determines how long it drives to knock off the jewel, in seconds.
    static final double     kTimeToKnockOff = 0.3; // was 0.3

    //Speeds

    //Speed it knocks the jewel off at.
    static final double     kKnockOffSpeed = 0.3; // was 0.3

    //Speed it drives at to park.
    static final double     kParkSpeed = 0.4; // was 0.8

    //boolean canPark = false;

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

        if (robot.jewelSensor.blue() < robot.jewelSensor.red()){
            elapsedTime.reset();
            while (elapsedTime.seconds()<kTimeToKnockOff && opModeIsActive()){
                sleep(1);
                robot.driveBackwards(kKnockOffSpeed);}

            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        } else { //if (robot.jewelSensor.red() < robot.jewelSensor.blue())
            elapsedTime.reset();
            while (elapsedTime.seconds()<kTimeToKnockOff && opModeIsActive()){
                sleep(1);
                robot.driveForwards(kKnockOffSpeed);}
            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        //When done
        robot.driveStop();
        robot.jewelServo.setPosition(1.0);
        elapsedTime.reset();
        while (elapsedTime.seconds() < kJEWEL_TIME);   {
            sleep(1);
            //Do nothing for kJEWEL_TIME seconds.
        }

        // Drive forwards.
        robot.driveForwards(kParkSpeed);
        elapsedTime.reset();
        while (elapsedTime.seconds()<kTimeToDrive && opModeIsActive()) {
            sleep(1);
        }
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

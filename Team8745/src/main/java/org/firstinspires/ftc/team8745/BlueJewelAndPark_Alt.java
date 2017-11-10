package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by rose on 11/4/17.
 */
@Autonomous(name="Jewel and Park Blue")

public class BlueJewelAndPark_Alt extends LinearOpMode {


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
    static final double     kJEWEL_TARGET = 0.5;

    static final int        kJEWEL_TIME = 1000;

    //Changing this determines how long it drives forwards to park, in seconds.
    static final int     kTimeToDrive = 1500;
    //Changing this determines how long it drives to knock off the jewel, in seconds.
    static final double     kTimeToKnockOff = 1.5;

    static final double     kServoTime = 1.5;

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

        // Lowers jewelServo
        robot.jewelServo.setPosition(kJEWEL_TARGET);
        elapsedTime.reset();
        while(elapsedTime.seconds()<kTimeToKnockOff){

        }
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (robot.jewelSensor.blue() > robot.jewelSensor.red()){
            elapsedTime.reset();
            while (elapsedTime.seconds()<kServoTime){
                robot.driveStrafe(1.0);}

            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        if (robot.jewelSensor.red() > robot.jewelSensor.blue()){
            elapsedTime.reset();
            while (elapsedTime.seconds()<kServoTime){
                robot.driveStrafe(-1.0);}
            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        //When done
        /*robot.jewelServo.setPosition(0.0);

        while (robot.jewelServo.getPosition()!= 0.0);   {
            //Do nothing until the servo is down.

        }*/

        // Drive to the right.
        robot.driveStrafe(kLEFT_SPEED);
        elapsedTime.reset();
        while (elapsedTime.seconds()<kTimeToDrive) {

        }



        robot.A.setPower(0);
        robot.B.setPower(0);
        robot.C.setPower(0);
        robot.D.setPower(0);
    }
}

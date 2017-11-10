package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by rose on 11/3/17.
 */

@Autonomous(name="OLD NONFUNCTIONALJewel and Park Blue")

public class OldJewel extends LinearOpMode {


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

    //Changing this determines how long it drives forwards to park, in seconds.
    static final double     kTimeToDrive = 1.5;
    //Changing this determines how long it drives to knock off the jewel, in seconds.
    static final double     kTimeToKnockOff = 1.5; //Was 0.9

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

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



        while (robot.jewelServo.getPosition()!= kJEWEL_TARGET) {
            robot.doNothing();
        }

        if (robot.jewelSensor.blue() > robot.jewelSensor.red()){
            while (opModeIsActive() && (elapsedTime.seconds() < kTimeToKnockOff)) {
                robot.driveStrafe(1.0);
            }
            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        if (robot.jewelSensor.red() > robot.jewelSensor.blue()){
            while (opModeIsActive() && (elapsedTime.seconds() < kTimeToKnockOff)) {
                robot.driveStrafe(-1.0);
            }
            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        //When done
        robot.jewelServo.setPosition(0.0);

        while (robot.jewelServo.getPosition()!= 0.0);   {
            //Do nothing until the servo is down.

        }

        // Drive to the right.
        robot.driveStrafe(kLEFT_SPEED);

        elapsedTime.reset();
        //Changing value of the number elapsedTime must be greater than changes how long the robot drives forward.
        while (opModeIsActive() && (elapsedTime.seconds() < kTimeToDrive)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", elapsedTime.seconds());
            telemetry.update();
        }

        robot.A.setPower(0);
        robot.B.setPower(0);
        robot.C.setPower(0);
        robot.D.setPower(0);
    }
}

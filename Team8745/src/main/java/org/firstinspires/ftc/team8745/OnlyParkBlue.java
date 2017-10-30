package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by rose on 10/30/17.
 */

@Autonomous(name="Jewel and Park Blue")

public class OnlyParkBlue extends LinearOpMode {


    /* Declare OpMode members. */
    OmniDriveRobot robot = new OmniDriveRobot();
    private ElapsedTime elapsedTime = new ElapsedTime();


    static final double     kFORWARD_SPEED = -0.6;
    static final double     kBACKWARD_SPEED = 0.6;

    static final double     kRIGHT_SPEED = -0.6;
    static final double     kLEFT_SPEED = 0.6;

    static final double     kTURN_SPEED = 0.5;

    static final double     kTimeToDrive = 1.5;

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


        // Drive forward for 1.5 seconds
        robot.A.setPower(kFORWARD_SPEED);
        robot.D.setPower(kFORWARD_SPEED);

        robot.B.setPower(kFORWARD_SPEED);
        robot.C.setPower(kFORWARD_SPEED);

        elapsedTime.reset();
        //Changing value of the number elapsedTime must be greater than changes how long the robot drives forward.
        while (opModeIsActive() && (elapsedTime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", elapsedTime.seconds());
            telemetry.update();
        }

        robot.A.setPower(0);
        robot.B.setPower(0);
        robot.C.setPower(0);
        robot.D.setPower(0);
    }
}
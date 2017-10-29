package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by rose on 10/25/17.
 */
@Autonomous(name="Jewel and Park Blue")

public class BlueTeam_AutonomousJewelAndPark extends LinearOpMode {


    /* Declare OpMode members. */
    OmniDriveRobot robot = new OmniDriveRobot();
    private ElapsedTime elapsedTime = new ElapsedTime();


    static final double     FORWARD_SPEED = -0.6;
    static final double     BACKWARD_SPEED = 0.6;

    static final double     RIGHT_SPEED = -0.6;
    static final double     LEFT_SPEED = 0.6;

    static final double     TURN_SPEED    = 0.5;

    double timeToDrive = 1.5;

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
        robot.jewelServo.setPosition(0.5);

        if (robot.jewelSensor.blue() > robot.jewelSensor.red()){
            while (opModeIsActive() && (elapsedTime.seconds() < .3)) {
                robot.A.setPower(FORWARD_SPEED);
                robot.D.setPower(FORWARD_SPEED);

                robot.B.setPower(FORWARD_SPEED);
                robot.C.setPower(FORWARD_SPEED);
            }
            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        }

        if (robot.jewelSensor.red() > robot.jewelSensor.blue()){
            while (opModeIsActive() && (elapsedTime.seconds() < .3)) {
                robot.A.setPower(BACKWARD_SPEED);
                robot.D.setPower(BACKWARD_SPEED);

                robot.B.setPower(BACKWARD_SPEED);
                robot.C.setPower(BACKWARD_SPEED);
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

        // Drive forward for 1.5 seconds
        robot.A.setPower(FORWARD_SPEED);
        robot.D.setPower(FORWARD_SPEED);

        robot.B.setPower(FORWARD_SPEED);
        robot.C.setPower(FORWARD_SPEED);

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

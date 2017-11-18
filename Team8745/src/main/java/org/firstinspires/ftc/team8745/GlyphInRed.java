
package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.robot.Robot;
        import com.qualcomm.robotcore.util.ElapsedTime;


/**
* Created by rose on 11/18/17.
*/
@Autonomous(name="Jewel On Side Test")

public class GlyphInRed extends LinearOpMode {


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


    //Changing this determines how long it drives to knock off the jewel, in seconds.
    static final double     kTimeToKnockOff = 0.3; // was 0.3

    static final double     kKnockOffSpeed = 0.3; // was 0.5

    //Changing this determines how long it drives left before driving the glyph in, in seconds.
    static final double     kStrafeTime = 0.5;

    //Changing this determines how long it drives forwards to smush the glyph into the box, in seconds.
    static final double     kForwardsTime = 1.5;

    //Changing this determines the speed at which the robot drives when moving to position itself/push in the glyph, on a scale of 0.0 to 1.0
    static final double     kGlyphSpeed = 0.8;

    //Changing this determines the time the robot waits for the servos to finish moving.
    static final double     kGlyphServoTime = 1.0;

    //Changing this determines how fast the robot drives backwards after dropping the glyph.
    static final double     kBackwardsSpeed = 0.8;

    //Changing this determines how long the robot drives backwards after dropping the glyph.
    static final double     kBackwardsTime = 0.2;

    //Open and closed positions for the servos that hold glyphs, on a scale of 0.0 to 1.0
    final double kServoRightOpen = 0.2;
    final double kServoLeftOpen = 1.0;

    final double kServoRightClosed = 0.45;
    final double kServoLeftClosed = 0.65;

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

        robot.servoR.setPosition(kServoRightClosed);
        robot.servoL.setPosition(kServoLeftClosed);

        waitTime(kGlyphServoTime);

        // Lowers jewelServo
        /*robot.jewelServo.setPosition(kJEWEL_TARGET);
        elapsedTime.reset();
        while(elapsedTime.seconds()<kJEWEL_TIME && opModeIsActive()){
            sleep(1);
        }

        if (robot.jewelSensor.blue() < robot.jewelSensor.red()){
            elapsedTime.reset();
            while (elapsedTime.seconds()<kTimeToKnockOff && opModeIsActive()){
                sleep(1);
                robot.driveRight(kKnockOffSpeed);}

            robot.A.setPower(0);
            robot.D.setPower(0);

            robot.B.setPower(0);
            robot.C.setPower(0);
        } else { //if (robot.jewelSensor.red() < robot.jewelSensor.blue())
            elapsedTime.reset();
            while (elapsedTime.seconds()<kTimeToKnockOff && opModeIsActive()){
                sleep(1);
                robot.driveLeft(kKnockOffSpeed);}
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
        }*/

        // Drive to the left.
        robot.driveLeft(kGlyphSpeed);

        waitTime(kStrafeTime);

        //Smash the glyph in by driving forwards
        robot.driveForwards(kGlyphSpeed);
        waitTime(kForwardsTime);

        robot.servoL.setPosition(kServoLeftOpen);
        robot.servoR.setPosition(kServoRightOpen);

        waitTime(kGlyphServoTime);

        robot.driveBackwards(kBackwardsSpeed);

        waitTime(kBackwardsTime);

        robot.driveStop();

        //Updates telemetry to what the sensor sees.
        telemetry.addData("Red",robot.jewelSensor.red());
        telemetry.addData("Green",robot.jewelSensor.green());
        telemetry.addData("Blue",robot.jewelSensor.blue());
        telemetry.update();

        robot.jewelServo.setPosition(1.0);
    }

}

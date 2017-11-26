package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;


/**
 * Created by rose on 11/10/17.
 */

//For testing changed constants and new mechanisms

@TeleOp(name = "8K Relic TeleOp")

public class ModifiedMainTeleOp extends OpMode {

    private OmniDriveRobot robot = new OmniDriveRobot();

    final double kServoRightOpen = 0.0;
    final double kServoLeftOpen = 1.0;

    final double kServoRightClosed = 0.8;
    final double kServoLeftClosed = 0.2;

    final double kGrabberClosed = 1.0;
    final double kGrabberOpen = 0.0;

    final double kLeftStickXDeadzone = 0.1;
    final double kLeftStickYDeadzone = 0;

    final double kLeftStick2Deadzone = 0.1;

    final double kSpinDeadzone = 0.1;

    final double kFlipIn = 0.0;
    final double kFlipOut = 1.0;

    public DcMotor relicMover;

    public Servo grabber;
    public Servo flip;


    @Override
    public void init() {
        robot.init(hardwareMap);

        relicMover = hardwareMap.dcMotor.get("Relic Motor");
        grabber = hardwareMap.servo.get("Grabber Servo");
        flip = hardwareMap.servo.get("Flipper Servo");

        robot.servoL.setPosition(kServoLeftOpen);
        robot.servoR.setPosition(kServoRightOpen);
        robot.lift.setTargetPosition(0);

    }

    @Override
    public void loop() {

        telemetry.addData("left servo",robot.servoL.getPosition());
        telemetry.addData("right servo",robot.servoR.getPosition());

        //Gamepad 2
        boolean gamepadA = gamepad2.a;
        boolean gamepadB = gamepad2.b;
        boolean gamepadX = gamepad2.x;
        boolean gamepadY = gamepad2.y;

        float leftStick2 = gamepad2.left_stick_y;
        float rightStick2 = gamepad2.right_stick_y;

        boolean rightBumper2 = gamepad2.right_bumper;
        boolean leftBumper2 = gamepad2.left_bumper;


        //Glyph servo positions
        if (gamepadA && gamepadB) {
            robot.doNothing();
        } else if(gamepadA){
            robot.servoL.setPosition(kServoLeftClosed);
            robot.servoR.setPosition(kServoRightClosed);
        } else if(gamepadB){
            robot.servoL.setPosition(kServoLeftOpen);
            robot.servoR.setPosition(kServoRightOpen);
        }

        //Retracts relic mechanism.
        relicMover.setPower(rightStick2);

        //Relic flipper position
        if (rightBumper2 && leftBumper2) {
            robot.doNothing();
        } else if(rightBumper2){
            grabber.setPosition(kFlipOut);
        } else if(leftBumper2){
            grabber.setPosition(kFlipIn);
        }

        //Relic grabber position
        if (gamepadX && gamepadY) {
            robot.doNothing();
        } else if(gamepadX){
            grabber.setPosition(kGrabberClosed);
        } else if(gamepadB){
            grabber.setPosition(kGrabberOpen);
        }

        /*Some extra stuff
        boolean dpadUp = gamepad2.dpad_up;
        boolean rightBumper2 = gamepad2.right_bumper; */

        //Gamepad 1 stick y values
        //float rightStickY = gamepad1.right_stick_y;
        float leftStickY = gamepad1.left_stick_y;

        //Gamepad 1 stick x values
        float leftStickX = -gamepad1.left_stick_x;
        float rightStickX = gamepad1.right_stick_x;



        if (Math.abs(leftStick2)>kLeftStick2Deadzone){
            robot.lift.setPower(leftStick2/2);
            telemetry.addData("Left Stick", leftStick2);
        } else {
            robot.lift.setPower(0.0);
        }
        if (Math.abs(leftStickX) < kLeftStickXDeadzone){
            leftStickX = 0;
        } else if (Math.abs(leftStickY) < kLeftStickYDeadzone){
            leftStickY = 0;
        }

        float BD = Range.clip(.5f*(leftStickY-leftStickX), -1, 1); //was x+y
        float AC = Range.clip(.5f*(leftStickY+leftStickX), -1, 1); //was y-x

        robot.jewelServo.setPosition(0.0);

        if (Math.abs(rightStickX) > kSpinDeadzone) {
            robot.A.setPower(-rightStickX);
            robot.D.setPower(-rightStickX);

            robot.C.setPower(rightStickX);
            robot.B.setPower(rightStickX);
        } else {
            robot.D.setPower(BD);
            robot.B.setPower(BD);
            robot.A.setPower(AC);
            robot.C.setPower(AC);
        }
    }
}

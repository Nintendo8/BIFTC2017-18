package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.util.Range;

/**
 * Created by rose on 10/25/17.
 */

@TeleOp(name = "Glyph Lifter TeleOp Test")

public class GlyphLifterTeleOp extends OpMode {

    private OmniDriveRobot robot = new OmniDriveRobot();
    final double kServoRightClosed = 0.4;
    final double kServoLeftClosed = 0.6;

    final double kServoRightOpen = 0.7;
    final double kServoLeftOpen = 0.3;


    final double kLeftStickXDeadzone = 0.2;
    final double kLeftStickYDeadzone = 0.2;

    final double kLeftStick2Deadzone = 0.1;

    final double kRightStickXDeadzone = 0.3;

    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {

        //Gamepad 2
        boolean gamepadA = gamepad2.a;
        boolean gamepadB = gamepad2.b;

        float leftStick2 = gamepad2.left_stick_y;

        /*Some extra stuff
        boolean dpadUp = gamepad2.dpad_up;
        boolean rightBumper2 = gamepad2.right_bumper; */

        //Gamepad 1 stick y values
        //float rightStickY = gamepad1.right_stick_y;
        float leftStickY = gamepad1.left_stick_y;

        //Gamepad 1 stick x values
        float leftStickX = -gamepad1.left_stick_x;
        float rightStickX = gamepad1.right_stick_x;

        if (gamepadA && gamepadB) {
            robot.doNothing();
        } else if(gamepadA){
            robot.servoL.setPosition(kServoLeftClosed);
            robot.servoR.setPosition(kServoRightClosed);
        } else if(gamepadB){
            robot.servoL.setPosition(kServoLeftOpen);
            robot.servoR.setPosition(kServoRightOpen);
        }

        if (Math.abs(leftStick2)>kLeftStick2Deadzone){
            robot.lift.setPower(leftStick2/2);
        } else {
            robot.lift.setPower(0.0);
        }
        if (Math.abs(leftStickX) < kLeftStickXDeadzone){
            leftStickX = 0;
        } else if (Math.abs(leftStickY) < kLeftStickYDeadzone){
            leftStickY = 0;
        }

        float BD = Range.clip(.5f*(leftStickX+leftStickY), -1, 1);
        float AC = Range.clip(.5f*(leftStickY-leftStickX), -1, 1);

        if (Math.abs(rightStickX) > kRightStickXDeadzone) {
            robot.A.setPower(rightStickX);
            robot.D.setPower(rightStickX);

            robot.C.setPower(-rightStickX);
            robot.B.setPower(-rightStickX);
        } else {
            robot.D.setPower(BD);
            robot.B.setPower(BD);
            robot.A.setPower(AC);
            robot.C.setPower(AC);
        }
    }
}

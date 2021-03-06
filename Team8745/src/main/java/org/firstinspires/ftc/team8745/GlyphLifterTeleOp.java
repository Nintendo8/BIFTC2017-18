package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.util.Range;

        import org.firstinspires.ftc.team8745.OmniDriveRobot;

/**
 * Created by rose on 10/25/17.
 */

//@Disabled
@TeleOp(name = "Glyph Lifter TeleOp Test")

public class GlyphLifterTeleOp extends OpMode {

    private OmniDriveRobot robot = new OmniDriveRobot();

    final double kServoRightOpen = 0.0;
    final double kServoLeftOpen = 1.0;

    final double kServoRightClosed = 1.0;
    final double kServoLeftClosed = 0.0;


    final double kLeftStickXDeadzone = 0.1;
    final double kLeftStickYDeadzone = 0;

    final double kLeftStick2Deadzone = 0.1;

    final double kSpinDeadzone = 0.1;

    @Override
    public void init() {
        robot.init(hardwareMap);

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

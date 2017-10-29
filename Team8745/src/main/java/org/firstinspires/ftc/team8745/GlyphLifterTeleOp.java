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
            robot.servoL.setPosition(0.4);
            robot.servoR.setPosition(0.4);
        } else if(gamepadB){
            robot.servoL.setPosition(0.0);
            robot.servoR.setPosition(0.0);
        }

        robot.lift.setPower(leftStick2/2);

        if (Math.abs(leftStickX) < .2){
            leftStickX = 0;
        } else if (Math.abs(leftStickY) < .2){
            leftStickY = 0;
        }

        float BD = Range.clip(.5f*(leftStickX+leftStickY), -1, 1);
        float AC = Range.clip(.5f*(leftStickY-leftStickX), -1, 1);

        if (Math.abs(rightStickX) > .3) {
            robot.A.setPower(rightStickX);
            robot.D.setPower(rightStickX);

            robot.C.setPower(-rightStickX);
            robot.B.setPower(-rightStickX);
            telemetry.addData("D Power", robot.D.getPower());
        } else {
            robot.D.setPower(BD);
            robot.B.setPower(BD);
            robot.A.setPower(AC);
            robot.C.setPower(AC);
        }
    }
}

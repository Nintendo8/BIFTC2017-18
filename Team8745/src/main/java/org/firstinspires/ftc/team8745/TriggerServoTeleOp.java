package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.team8745.OmniDriveRobot;


/**
 * Created by rose on 11/3/17.
 */


@TeleOp(name = "8K Main TeleOp 11/30")

public class TriggerServoTeleOp extends OpMode {

    private OmniDriveRobot robot = new OmniDriveRobot();

    private ElapsedTime upTime = new ElapsedTime();
    private ElapsedTime downTime = new ElapsedTime();

    final double kServoRightOpen = 0.0;
    final double kServoLeftOpen = 1.0;

    final double kServoRightClosed = 1.0;
    final double kServoLeftClosed = 0.0;


    final double kLeftStickXDeadzone = 0.1;
    final double kLeftStickYDeadzone = 0;

    final double kLeftStick2Deadzone = 0.1;
    final double kRightStick2Deadzone = 0.1;

    final double kSpinDeadzone = 0.1;

    @Override
    public void init() {
        robot.init(hardwareMap);

        robot.servoL.setPosition(kServoLeftOpen);
        robot.servoR.setPosition(kServoRightOpen);

    }

    @Override
    public void loop() {

        robot.jewelServo.setPosition(1.0);

        boolean up = gamepad1.dpad_up;
        boolean down = gamepad1.dpad_down;

        float rightTrigger = gamepad1.right_trigger;
        float leftTrigger = gamepad1.left_trigger;

        if (rightTrigger > 0 || leftTrigger > 0 ){
            robot.balanceLift.setPower(rightTrigger-leftTrigger);
        }
        else {
            robot.balanceLift.setPower(0.0);
        }

        float rightTrigger2 = gamepad2.right_trigger;
        float leftTrigger2 = gamepad2.left_trigger;

        //Gamepad 2
        boolean gamepadA = gamepad2.a;
        boolean gamepadB = gamepad2.b;

        robot.servoR.setPosition(kServoRightOpen+rightTrigger2);
        robot.servoL.setPosition(kServoLeftOpen-leftTrigger2);

        /*
        //obsolete
        if (gamepadA && gamepadB) {
            robot.doNothing();
        } else if(gamepadA){
            robot.servoL.setPosition(kServoLeftClosed);
            robot.servoR.setPosition(kServoRightClosed);
        } else if(gamepadB){
            robot.servoL.setPosition(kServoLeftOpen);
            robot.servoR.setPosition(kServoRightOpen);
        }*/

        float leftStick2 = gamepad2.left_stick_y;
        float rightStick2 = gamepad2.right_stick_y;

        /*Some extra stuff
        boolean dpadUp = gamepad2.dpad_up;
        boolean rightBumper2 = gamepad2.right_bumper; */

        //Gamepad 1 stick y values
        float leftStickY = gamepad1.left_stick_y;
        float rightStickY = gamepad1.right_stick_y;

        //Gamepad 1 stick x values
        float leftStickX = -gamepad1.left_stick_x;
        float rightStickX = gamepad1.right_stick_x;

        //Squared left stick values that stay negative
        float squaredLeftStickY = leftStickY*leftStickY * Math.signum(leftStickY);
        float squaredLeftStickX = leftStickX*leftStickX * Math.signum(leftStickX);

        //Squared right stick values that stay negative
        float squaredRightStickY = rightStickY*rightStickY * Math.signum(rightStickY);
        float squaredRightStickX = rightStickX*rightStickX * Math.signum(rightStickX);

        if (Math.abs(leftStick2)>kLeftStick2Deadzone){
            robot.lift.setPower(leftStick2/2);
            telemetry.addData("Operator Left Stick Y", leftStick2);
            telemetry.update();
        } else if (Math.abs(rightStick2)>kRightStick2Deadzone){
            robot.lift.setPower(rightStick2/4);
            telemetry.addData("Operator Left Stick Y", leftStick2);
            telemetry.update();
        } else {
            robot.lift.setPower(0.0);
            telemetry.addData("Operator Left Stick Y", leftStick2);
            telemetry.update();
        }
        if (Math.abs(leftStickX) < kLeftStickXDeadzone){
            leftStickX = 0;
        } else if (Math.abs(leftStickY) < kLeftStickYDeadzone){
            leftStickY = 0;
        }

        float BD = Range.clip(.5f*(squaredLeftStickY-squaredLeftStickX), -1, 1); //was x+y
        float AC = Range.clip(.5f*(squaredLeftStickY+squaredLeftStickX), -1, 1); //was y-x

        if (Math.abs(rightStickX) > kSpinDeadzone) {
            robot.A.setPower(squaredRightStickX * -1);
            robot.D.setPower(squaredRightStickX * -1);

            robot.C.setPower(squaredRightStickX);
            robot.B.setPower(squaredRightStickX);
        } else {
            robot.D.setPower(BD);
            robot.B.setPower(BD);
            robot.A.setPower(AC);
            robot.C.setPower(AC);
        }
        telemetry.addData("left servo",robot.servoL.getPosition());
        telemetry.addData("right servo",robot.servoR.getPosition());
        telemetry.update();
    }
}

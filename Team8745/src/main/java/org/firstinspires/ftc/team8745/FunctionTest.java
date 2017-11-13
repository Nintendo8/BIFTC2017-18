package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.util.Range;

        import org.firstinspires.ftc.team8745.OmniDriveRobot;


/**
 * Created by rose on 11/10/17.
 */


@TeleOp(name = "Function Test")

public class FunctionTest extends OpMode {

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

        boolean up = gamepad1.dpad_up;
        boolean right = gamepad1.dpad_right;
        boolean down = gamepad1.dpad_down;
        boolean left = gamepad1.dpad_left;

        if (up) {
            robot.driveForwards(1.0);
        }

        if (right) {
            robot.driveRight(1.0);
        }

        if (down) {
            robot.driveBackwards(1.0);
        }

        if (left) {
            robot.driveLeft(1.0);
        }

        if (!up && !right && !down && !left ) {
            robot.driveStop();
        }


    }
}

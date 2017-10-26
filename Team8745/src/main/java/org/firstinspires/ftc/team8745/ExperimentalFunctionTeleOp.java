package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.util.Range;

/**
 * Created by rose on 10/22/17.
 */
@TeleOp(name = "Omni Drive Function Test")

public class ExperimentalFunctionTeleOp extends OpMode {


    private OmniDriveRobot robot = new OmniDriveRobot();

    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {



        float rightStickY = gamepad1.right_stick_y;
        float leftStickY = gamepad1.left_stick_y;
        boolean dpadUp = gamepad2.dpad_up;
        boolean rightBumper = gamepad1.right_bumper;
        float leftStickX = -gamepad1.left_stick_x;
        float rightStickX = gamepad1.right_stick_x;

        if (Math.abs(leftStickX) < .3){
            leftStickX = 0;
        } else if (Math.abs(leftStickY) < .3){
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
            robot.driveStrafe(leftStickY);
        }
    }
}
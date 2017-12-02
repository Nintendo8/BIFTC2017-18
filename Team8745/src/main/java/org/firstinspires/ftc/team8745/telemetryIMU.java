package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by rose on 11/26/17.
 */


@Disabled
@Autonomous(name ="IMU Telemetry")
public class telemetryIMU extends OpMode {

    OmniDriveRobot robot = new OmniDriveRobot();

    @Override
    public void init(){
    robot.init(hardwareMap);

    }

    @Override
    public void loop(){
    //Orientation angles   = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    //telemetry.addData("Angles", angles.firstAngle);

    }

}

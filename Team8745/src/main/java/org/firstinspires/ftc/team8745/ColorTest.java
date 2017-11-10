package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;



/**
 * Created by rose on 11/8/17.
 */
@TeleOp(name="Color Sensor Accuracy")

public class ColorTest extends OpMode {


    /* Declare OpMode members. */
    OmniDriveRobot robot = new OmniDriveRobot();

    String color = "none";

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.jewelSensor.enableLed(true);
    }

    @Override
    public void loop() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here


         */
        robot.init(hardwareMap);

        if (robot.jewelSensor.blue() > robot.jewelSensor.red()){
            color = "Blue";
        } else if (robot.jewelSensor.red() > robot.jewelSensor.blue()){

            color = "Red";
        }

        //When done
        /*robot.jewelServo.setPosition(0.0);

        while (robot.jewelServo.getPosition()!= 0.0);   {
            //Do nothing until the servo is down.

        }*/

        //Updates color to what the sensor sees.
        telemetry.addData("argb", robot.jewelSensor.argb());
        telemetry.addData("Color", color);
        telemetry.addData("Red",robot.jewelSensor.red());
        telemetry.addData("Green",robot.jewelSensor.green());
        telemetry.addData("Blue",robot.jewelSensor.blue());
        telemetry.update();

    }
}

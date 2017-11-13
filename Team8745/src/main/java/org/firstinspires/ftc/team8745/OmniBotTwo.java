package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by rose on 11/10/17.
 */


public class OmniBotTwo {

    public DcMotor A;
    public DcMotor B;
    public DcMotor C;
    public DcMotor D;

    public DcMotor lift;

    public Servo servoL;
    public Servo servoR;

    public Servo jewelServo;

    public ColorSensor jewelSensor;

    public void init(HardwareMap hardwareMap) {

        /*lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        //Front Motors
        B = hardwareMap.dcMotor.get("B");
        D = hardwareMap.dcMotor.get("D");

        //Back Motors
        A = hardwareMap.dcMotor.get("A");
        C = hardwareMap.dcMotor.get("C");

        lift = hardwareMap.dcMotor.get("Lift");

        servoL = hardwareMap.servo.get("Left Servo");
        servoR = hardwareMap.servo.get("Right Servo");

        jewelServo = hardwareMap.servo.get("Jewel Servo");
        jewelSensor = hardwareMap.colorSensor.get("Jewel Sensor");


    }

    public void driveStraight (double speed) {
        A.setPower(speed);
        D.setPower(speed);

        B.setPower(-speed);
        C.setPower(-speed);

    }

    public void driveStrafe (double speed) {
        A.setPower(speed);
        B.setPower(speed);
        C.setPower(speed);
        D.setPower(speed);

    }

    public void  doNothing (){
    }
}


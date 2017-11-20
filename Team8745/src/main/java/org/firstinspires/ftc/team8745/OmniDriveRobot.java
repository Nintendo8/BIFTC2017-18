package org.firstinspires.ftc.team8745;

        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.hardware.configuration.MatrixConstants;
        import com.qualcomm.robotcore.util.Range;


/**
 * Created by rose on 10/15/17.
 */


public class OmniDriveRobot {


  /*public DcMotor left_b;
    public DcMotor left_f;
    public DcMotor right_b;
    public DcMotor right_f;*/

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

        //Reverse Mode
        B.setDirection(DcMotorSimple.Direction.REVERSE);
        C.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");

        servoL = hardwareMap.servo.get("Left Servo");
        servoR = hardwareMap.servo.get("Right Servo");

        jewelServo = hardwareMap.servo.get("Jewel Servo");
        jewelSensor = hardwareMap.colorSensor.get("Jewel Sensor");

        jewelServo.setPosition(1.0);

    }

    public void driveStrafe (double speed) {
        A.setPower(speed);
        D.setPower(speed);

        B.setPower(speed);
        C.setPower(speed);

    }

    //float BD = Range.clip(.5f*(leftStickY-leftStickX), -1, 1);
    //float AC = Range.clip(.5f*(leftStickY+leftStickX), -1, 1);

    // Forwards: BD = 1, AC = 1
    // Backwards: BD = -1, AC = -1
    // Right: BD = -1, AC = 1
    // Left: BD = 1, AC = -1


    public void driveBackwards (double speed){ // was forwards
        double motorsBD = Math.abs(speed);
        double motorsAC = Math.abs(speed);

        A.setPower(motorsAC);
        C.setPower(motorsAC);
        B.setPower(motorsBD);
        D.setPower(motorsBD);
    }

    public void driveForwards (double speed){ // was backwards
        double motorsBD = Math.abs(speed)*-1;
        double motorsAC = Math.abs(speed)*-1;

        A.setPower(motorsAC);
        C.setPower(motorsAC);
        B.setPower(motorsBD);
        D.setPower(motorsBD);
    }

    public void driveLeft (double speed){ // was right
        double motorsBD = Math.abs(speed)*-1;
        double motorsAC = Math.abs(speed);

        A.setPower(motorsAC);
        C.setPower(motorsAC);
        B.setPower(motorsBD);
        D.setPower(motorsBD);
    }

    public void driveRight (double speed){ //was left
        double motorsBD = Math.abs(speed);
        double motorsAC = Math.abs(speed)*-1;

        A.setPower(motorsAC);
        C.setPower(motorsAC);
        B.setPower(motorsBD);
        D.setPower(motorsBD);
    }

    public void driveStop (){
        A.setPower(0);
        C.setPower(0);
        B.setPower(0);
        D.setPower(0);
    }

    public void  doNothing (){
    }
}


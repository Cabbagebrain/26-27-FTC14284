package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrivetrain {
    private Gamepad gamepad1;
    private double denominator;
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private  DcMotorEx backRight;
    private double flPower;
    private double frPower;
    private double blPower;
    private double brPower;
    public MecanumDrivetrain(HardwareMap hardwareMap) {
        //retrieve motors from hardware map
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft"); //port 0
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight"); //port 1
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft"); //port 2
        backRight = hardwareMap.get(DcMotorEx.class, "backRight"); //port 3

        frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setPower(IMU imu, double x, double y, double rx) {
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        flPower = (rotY + rotX + rx) / denominator;
        blPower = (rotY - rotX + rx) / denominator;
        frPower = (rotY - rotX - rx) / denominator;
        brPower = (rotY + rotX - rx) / denominator;
    }
    public void drive() {
        frontLeft.setPower(flPower);
        frontRight.setPower(frPower);
        backLeft.setPower(blPower);
        backRight.setPower(brPower);
    }
    public void slowDrive() {
        frontLeft.setPower(flPower / 2);
        frontRight.setPower(frPower / 2);
        backLeft.setPower(blPower / 2);
        backRight.setPower(brPower / 2);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class IntakeSubsystem extends SubsystemBase {
    private DcMotor intake;
    private DcMotor feed;
    private DcMotor loader;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake"); //port 3 expansion
        feed = hardwareMap.get(DcMotor.class, "feed"); //port 0 expansion
        loader = hardwareMap.get(DcMotor.class, "loader"); //port 0 control

        feed.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void runFeedIntake(double power) {
        feed.setPower(power);
        intake.setPower(power);
    }

    public void stopFeedIntake() {
        feed.setPower(0);
        intake.setPower(0);
    }

    public void runIntake(double power) {
        intake.setPower(power);
    }

    public void stopIntake() {
        intake.setPower(0);
    }

    public void runFeed(double power) {
        feed.setPower(power);
    }

    public void stopFeed() {
        feed.setPower(0);
    }

    public void startLoad(double power) {
        loader.setPower(power);
    }

    public void stopLoad() {
        loader.setPower(0);
    }
}
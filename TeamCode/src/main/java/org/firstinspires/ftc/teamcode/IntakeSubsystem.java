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
    private ServoEx gate;
    private double gate_pos;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotor.class, "intake"); //port 0
        feed = hardwareMap.get(DcMotor.class, "feed"); //port 1
        loader = hardwareMap.get(DcMotor.class, "loader"); //port 2
        gate = hardwareMap.get(ServoEx.class,"Name");

        feed.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        gate_pos = gate.getRawPosition();
    }

    public void runIntake(double power) {
        intake.setPower(power);
        feed.setPower(power);
    }

    public void stopIntake() {
        intake.setPower(0);
        feed.setPower(0);
    }

    public void startLoad(double power) {
        loader.setPower(power);
    }

    public void stopLoad() {
        loader.setPower(0);
    }

    public void openGate()
    {
        gate.set(gate_pos + 0.1);
    }

    public void closeGate()
    {
        gate.set(gate_pos);
    }
}
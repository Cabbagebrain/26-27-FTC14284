package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase{

    private DcMotorEx shooter;
    private Servo gate;
    public ShooterSubsystem(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter"); //port 3 control
        gate = hardwareMap.get(Servo.class,"servoGate"); //port 0 expansion
        shooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void runShooter(double power) {
       shooter.setPower(power);
    }

    public void stopShooter(){
        shooter.setPower(0);
    }

    public void openGate()
    {
        gate.setPosition(0.83);
    }

    public void closeGate()
    {
        gate.setPosition(0.28);
    }

    public DcMotorEx getShooter() {
        return shooter;
    }
}
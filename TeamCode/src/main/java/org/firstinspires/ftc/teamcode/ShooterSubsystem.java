package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase{

    private DcMotor shooter;
    private Servo gate;
    public ShooterSubsystem(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(DcMotor.class, "shooter"); //port 3 control
        gate = hardwareMap.get(Servo.class,"servoGate"); //port 0 expansion
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
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
}
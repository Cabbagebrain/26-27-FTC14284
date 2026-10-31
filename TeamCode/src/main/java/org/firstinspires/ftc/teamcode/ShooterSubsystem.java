package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase{

    private DcMotor shooterLeft;
    private DcMotor shooterRight;
    public ShooterSubsystem(HardwareMap hardwareMap) {
        shooterLeft = hardwareMap.get(DcMotor.class, "shooterLeft"); //port 2
        shooterRight = hardwareMap.get(DcMotor.class, "shooterRight"); //port 3

        shooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooterRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void shoot_power(double power) {
        shooterLeft.setPower(power);
        shooterRight.setPower(power);
    }

    public void stopShooter(){
        shooterRight.setPower(0);
        shooterLeft.setPower(0);
    }
}
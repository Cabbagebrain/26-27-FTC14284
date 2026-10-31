package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

@TeleOp
public class RobotContainer extends LinearOpMode {
    private IMU imu;
    private double x;
    private double y;
    private double rx;

    @Override
    public void runOpMode() throws InterruptedException {
        GamepadEx driverOP = new GamepadEx(gamepad1);        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);

        //init limelight
        Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!

        MecanumDrivetrainSubsystem drivetrain = new MecanumDrivetrainSubsystem(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);

        Command shootCommand = new SequentialCommandGroup(new InstantCommand(() -> shooter.shoot_power(0.8))
                                                        , new InstantCommand(() -> intake.runIntake(1))
                                                        , new InstantCommand(() -> intake.startLoad(1)));
                                                        //, new InstantCommand(() -> intake.openGate()));

        Command stopShoot = new SequentialCommandGroup(new InstantCommand(() -> shooter.shoot_power(0))
                , new InstantCommand(() -> intake.runIntake(0))
                , new InstantCommand(() -> intake.startLoad(0)));
                //, new InstantCommand(() -> intake.closeGate()));

        Button left_bumper = new GamepadButton(driverOP, GamepadKeys.Button.LEFT_BUMPER);
        Button right_bumper = new GamepadButton(driverOP, GamepadKeys.Button.RIGHT_BUMPER);
        Button b_button = new GamepadButton(driverOP, GamepadKeys.Button.B);

        ElapsedTime runtime = new ElapsedTime();
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {
            y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;

            left_bumper.whenPressed(shootCommand)
                    .whenReleased(stopShoot);

            b_button.whenPressed(new InstantCommand(() -> intake.openGate()))
                    .whenReleased(new InstantCommand(() -> intake.closeGate()));

            right_bumper.toggleWhenPressed(new InstantCommand(() -> intake.runIntake(0.8)),
                    (new InstantCommand(() -> intake.stopIntake())));

            /*if(driverOP.a)
            {
                intake.startLoad(1);
            }
            else{
                intake.stopLoad();
            }*/

            if(driverOP.getButton(GamepadKeys.Button.DPAD_DOWN)){
                imu.resetYaw();
            }

            drivetrain.setPower(imu, x, y, rx);
            if (driverOP.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                drivetrain.slowDrive();
            } else {
                drivetrain.drive();
            }
        }
    }
}

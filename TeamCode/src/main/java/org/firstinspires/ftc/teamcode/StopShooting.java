package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

public class StopShooting extends SequentialCommandGroup {
    private final ShooterSubsystem shooter;
    private final IntakeSubsystem intake;

    public StopShooting(ShooterSubsystem shooter, IntakeSubsystem intake) {
        this.shooter = shooter;
        this.intake = intake;
        addCommands(
                new InstantCommand(() -> shooter.stopShooter()),
                new InstantCommand(() -> intake.stopIntake()),
                new InstantCommand(() -> intake.stopLoad())
        );
        addRequirements(shooter);
        addRequirements(intake);
    }

    /*
    @Override
    //runs when command is scheduled
    public  void initialize() {

    }

    @Override
    //runs when command is called
    public void execute () {

    }
    */

    @Override
    //must be overridden or command will not end
    public boolean isFinished() {
        return true;
    }
}

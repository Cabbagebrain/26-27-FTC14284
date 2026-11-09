package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

public class Shoot extends SequentialCommandGroup {
    private final ShooterSubsystem shooter;
    private final IntakeSubsystem intake;
    public Shoot(ShooterSubsystem shooter, IntakeSubsystem intake) {
        this.shooter = shooter;
        this.intake = intake;
        addCommands(
                new InstantCommand(() -> shooter.openGate()),
                new InstantCommand(() -> intake.startLoad(.5))
        );
        addRequirements(shooter);
        addRequirements(intake);
    }

    @Override
    //must be overridden or command will not end
    public boolean isFinished() {
        return true;
    }
}

package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

public class StopShoot extends SequentialCommandGroup {
    private final ShooterSubsystem shooter;
    private final IntakeSubsystem intake;
    public StopShoot(ShooterSubsystem shooter, IntakeSubsystem intake) {
        this.shooter = shooter;
        this.intake = intake;
        addCommands(
                new InstantCommand(() -> shooter.closeGate()),
                new InstantCommand(() -> intake.stopLoad())
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

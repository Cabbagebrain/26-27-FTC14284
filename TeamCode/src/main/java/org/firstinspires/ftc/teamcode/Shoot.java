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
                new InstantCommand(() -> shooter.runShooter(0.8)),
                new InstantCommand(() -> intake.runFeed(1)),
                new InstantCommand(() -> intake.startLoad(1))
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

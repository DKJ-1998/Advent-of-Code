package code.of.advent.diveshj21.day13;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClawCalculator {
    private static final Logger logger = LoggerFactory.getLogger(ClawCalculator.class);

    public int getMinTokens(List<Machine> machines) {
        var minTokens = 0;
        for (var machine : machines) {
            minTokens += getMinTokensForMachine(machine);
        }
        return minTokens;
    }

    private int getMinTokensForMachine(Machine machine) {
//        logger.info("Machine: {}", machine);
        var buttonAPresses = 0;
        var buttonBPressesGetsToPrize = getButtonBPressesToGetToPrize(machine, buttonAPresses);
        while (buttonBPressesGetsToPrize.isEmpty() && buttonAPresses <= 100) {
            buttonAPresses++;
            buttonBPressesGetsToPrize = getButtonBPressesToGetToPrize(machine, buttonAPresses);
        }

        if (buttonAPresses <= 100) {
//            logger.info("Machine: {}, Button A Presses: {}, Button B Presses: {}", machine, buttonAPresses, buttonBPressesGetsToPrize.get());
            return buttonAPresses * 3 + buttonBPressesGetsToPrize.get();
        } else {
//            logger.info("Machine: {}, 0 button presses", machine);
            return 0;
        }
    }

    private Optional<Integer> getButtonBPressesToGetToPrize(Machine machine, int buttonAPresses) {
        var prizeLocation = machine.prizeLocation();

        var xMovementFromAPresses = machine.buttonA().xMovement() * buttonAPresses;
        var yMovementFromAPresses = machine.buttonA().yMovement() * buttonAPresses;

        var requiredPressesFromButtonBInXDirectionIsInt = (prizeLocation.x() - xMovementFromAPresses) % machine.buttonB().xMovement() == 0;
        var requiredPressesFromButtonBInYDirectionIsInt = (prizeLocation.y() - yMovementFromAPresses) % machine.buttonB().yMovement() == 0;
        var requiredPressesFromButtonBInXDirection = (prizeLocation.x() - xMovementFromAPresses) / machine.buttonB().xMovement();
        var requiredPressesFromButtonBInYDirection = (prizeLocation.y() - yMovementFromAPresses) / machine.buttonB().yMovement();

//        logger.info("  Button A Presses: {}", buttonAPresses);
//        logger.info("  Movement from Button A presses: {}, {}", xMovementFromAPresses, yMovementFromAPresses);
//        logger.info("  Required presses from button B: {}, {}", requiredPressesFromButtonBInXDirection, requiredPressesFromButtonBInYDirection);
//        logger.info("  Prize Location: {}", prizeLocation);

        if (requiredPressesFromButtonBInXDirectionIsInt
            && requiredPressesFromButtonBInYDirectionIsInt
            && requiredPressesFromButtonBInXDirection == requiredPressesFromButtonBInYDirection) {
            return Optional.of(requiredPressesFromButtonBInXDirection);
        }
        return Optional.empty();
    }

    public long getMinTokensForRealMachines(List<Machine> machines) {
        var minTokens2 = 0L;
        for (var machine : machines) {
            var prizeLocationX = machine.prizeLocation().x() + 10000000000000L;
            var prizeLocationY = machine.prizeLocation().y() + 10000000000000L;
            var buttonAPresses = (prizeLocationY - (double) (prizeLocationX * machine.buttonB().yMovement()) / machine.buttonB().xMovement()) / (machine.buttonA().yMovement() - (double) (machine.buttonA().xMovement() * machine.buttonB().yMovement()) / machine.buttonB().xMovement());
//            logger.info("Button A Presses: {}", buttonAPresses);
            var buttonBPresses = (prizeLocationX - machine.buttonA().xMovement() * buttonAPresses) / machine.buttonB().xMovement();
//            logger.info("Button B Presses: {}", buttonBPresses);

            var buttonAPressesIsInt = Math.round(buttonAPresses * 1000) % 1000 == 0;
            var buttonBPressesIsInt = Math.round(buttonBPresses * 1000) % 1000 == 0;

            if (buttonAPressesIsInt && buttonBPressesIsInt) {
                buttonAPresses = Math.round(buttonAPresses * 1000) / (double) 1000;
                buttonBPresses = Math.round(buttonBPresses * 1000) / (double) 1000;
                minTokens2 += (long) (buttonAPresses * 3 + buttonBPresses);
//                logger.info("Machine: {}, Button A Presses: {}, Button B Presses: {}", machine, buttonAPresses, buttonBPresses);
            }
//            } else {
//                logger.info("Machine: {}, 0 button presses", machine);
//            }
        }
        return minTokens2;
    }
}

package code.of.advent.diveshj21.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EquationFinder {
    private static final Logger logger = LoggerFactory.getLogger(EquationFinder.class);

    private final List<Operator> validOperators;

    public long find(Map<Long, Long[]> calibrations) {
        var sum = 0L;
        var calibrationNumber = 0;
        for (var calibration : calibrations.entrySet()) {
            logger.info("Checking calibration {} of {}", ++calibrationNumber, calibrations.size());
            var target = calibration.getKey();
            var numbers = calibration.getValue();
            if (isTargetAttainable(target, numbers, new ArrayList<>())) {
                sum += target;
            }
        }
        return sum;
    }

    private boolean isTargetAttainable(Long target, Long[] numbers, ArrayList<Operator> operators) {
        if (operators.size() == numbers.length - 1) {
            return calculate(numbers, operators) == target;
        }
        for (var operator : validOperators) {
            var newOperators = new ArrayList<>(operators);
            newOperators.add(operator);
            if (isTargetAttainable(target, numbers, newOperators)) {
                return true;
            }
        }
        return false;
    }

    private long calculate(Long[] numbers, ArrayList<Operator> operators) {
        var result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = operators.get(i - 1).getOperation().apply(result, numbers[i]);
        }
        return result;
    }
}

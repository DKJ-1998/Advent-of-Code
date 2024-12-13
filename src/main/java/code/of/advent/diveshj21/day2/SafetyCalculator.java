package code.of.advent.diveshj21.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafetyCalculator {
    private static final Logger logger = LoggerFactory.getLogger(SafetyCalculator.class);

    public boolean isSafe(List<Integer> report) {
        var increasing = report.get(1) - report.get(0) > 0;
        for (int i = 1; i < report.size(); i++) {
            var diffToLastValue = report.get(i) - report.get(i - 1);
            if (!increasing) {
                diffToLastValue *= -1;
            }
            if (diffToLastValue <= 0 || diffToLastValue > 3) {
                return false;
            }
        }
        return true;
    }

    public boolean isSafeWithDampener(List<Integer> report) {
        report = new ArrayList<>(report);

        // brute force
        for (int i = 0; i < report.size(); i++) {
            if (isSafe(Stream.concat(
                report.subList(0, i).stream(),
                report.subList(i + 1, report.size()).stream()).toList())) {
                return true;
            }
        }
        return false;

//        var increasing = report.get(1) - report.get(0) > 0;
//        for (int i = 1; i < report.size(); i++) {
//            var diffToLastValue = report.get(i) - report.get(i - 1);
//            if (!increasing) {
//                diffToLastValue *= -1;
//            }
//            if (diffToLastValue <= 0 || diffToLastValue > 3) {
//                logger.info("Unsafe: {}, {}", report, increasing ? "inc" : "dec");
//                return false;
//            }
//        }
//        logger.info("Safe: {}, {}", report, increasing ? "inc" : "dec");
//        return true;
    }
}

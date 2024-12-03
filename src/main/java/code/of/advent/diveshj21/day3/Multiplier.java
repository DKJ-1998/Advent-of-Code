package code.of.advent.diveshj21.day3;

import java.util.regex.Pattern;

public class Multiplier {

    public int multiply(String input) {
        var pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        var matcher = pattern.matcher(input);
        var sum = 0;
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        return sum;
    }

    public int multiplyWithConditional(String input) {
        var pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        var matcher = pattern.matcher(input);
        var sum = 0;
        while (matcher.find()) {
            var previousDontIndex = input.substring(0, matcher.start()).lastIndexOf("don't()");
            var previousDoIndex = input.substring(0, matcher.start()).lastIndexOf("do()");
            if (previousDontIndex <= previousDoIndex) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }
        return sum;
    }
}

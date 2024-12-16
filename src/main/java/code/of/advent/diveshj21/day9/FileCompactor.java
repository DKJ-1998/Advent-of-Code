package code.of.advent.diveshj21.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCompactor {
    private static final Logger logger = LoggerFactory.getLogger(FileCompactor.class);

    public long compact(String diskMap) {
        var rearranged = new ArrayList<Optional<Integer>>();
        var fileNumber = 0;
        var file = true;
        var diskMapArray = diskMap.toCharArray();
        for (var value : diskMapArray) {
            var blockLength = Integer.parseInt(String.valueOf(value));
            if (file) {
                rearranged.addAll(Collections.nCopies(blockLength, Optional.of(fileNumber)));
                fileNumber++;
            } else {
                rearranged.addAll(Collections.nCopies(blockLength, Optional.empty()));
            }
            file = !file;
        }

        var checksum = 0L;
        var rightPointer = rearranged.size() - 1;
        for (var leftPointer = 0; leftPointer < rearranged.size() && rightPointer > leftPointer; leftPointer++) {
            if (rearranged.get(leftPointer).isEmpty()) {
                while (rearranged.get(rightPointer).isEmpty() && rightPointer > leftPointer) {
                    rightPointer--;
                }
                if (rightPointer == leftPointer) {
                    break;
                }
                rearranged.set(leftPointer, rearranged.get(rightPointer));
                rearranged.set(rightPointer, Optional.empty());
            }
            checksum += (long) rearranged.get(leftPointer).get() * leftPointer;
//            logger.info("Adding checksum: {}", rearranged.get(leftPointer).get() * leftPointer);
//            logger.info("Left pointer: {}", leftPointer);
//            logger.info("Right pointer: {}", rightPointer);
//            printChecksum(rearranged);
        }
//        logger.info("Rearranged: {}", rearranged);

        return checksum;
    }

//    private void printChecksum(ArrayList<Optional<Integer>> rearranged) {
//        var string = rearranged.stream()
//            .map(optional -> optional.map(Object::toString).orElse("."))
//            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
//            .toString();
//        logger.info("Rearranged: {}", string);
//    }

    public long compactWholeFile(String diskMap) {
        var arrangement = new ArrayList<Block>();
        var fileNumber = 0;
        var file = true;
        var diskMapArray = diskMap.toCharArray();
        for (var value : diskMapArray) {
            var blockLength = Integer.parseInt(String.valueOf(value));
            if (file) {
                arrangement.add(new Block(new FileSpace(fileNumber), blockLength));
                fileNumber++;
            } else {
                arrangement.add(new Block(new EmptySpace(), blockLength));
            }
            file = !file;
        }
//        printArrangement(arrangement);

        var filesMoved = new HashSet<Integer>();

        for (var rightPointer = arrangement.size() - 1; rightPointer > 0; rightPointer--) {
            if (arrangement.get(rightPointer).blockSpace() instanceof EmptySpace) {
                continue;
            }
            var rightFileNumber = ((FileSpace) arrangement.get(rightPointer).blockSpace()).fileNumber();
            if (filesMoved.contains(rightFileNumber)) {
                continue;
            }
            filesMoved.add(rightFileNumber);
//            logger.info("Moving block: {}", ((FileSpace) arrangement.get(rightPointer).blockSpace()).fileNumber());
            var leftPointer = 0;
            while (
                leftPointer < rightPointer &&
                (arrangement.get(leftPointer).blockSpace() instanceof FileSpace
                    || arrangement.get(leftPointer).length() < arrangement.get(rightPointer).length())) {
                leftPointer++;
            }
            if (leftPointer < rightPointer) {
                var leftBlock = arrangement.get(leftPointer);
                var rightBlock = arrangement.get(rightPointer);
                if (leftBlock.blockSpace() instanceof FileSpace) {
                    throw new RuntimeException("Should be an empty space, is a file space");
                }
                if (leftBlock.length() > rightBlock.length()) {
                    arrangement.add(leftPointer, rightBlock);
                    arrangement.set(leftPointer + 1, new Block(new EmptySpace(), leftBlock.length() - rightBlock.length()));
                    rightPointer++;
                    arrangement.set(rightPointer, new Block(new EmptySpace(), rightBlock.length()));
                } else if (leftBlock.length() == rightBlock.length()) {
                    arrangement.set(leftPointer, rightBlock);
                    arrangement.set(rightPointer, leftBlock);
                } else {
                    throw new RuntimeException("Left block length is less than right block length");
                }
            }
        }
//        printArrangement(arrangement);

        var checksum = 0L;
        var position = 0;
        for (var block : arrangement) {
            if (block.blockSpace() instanceof FileSpace(int fileIdNumber)) {
                for (var i = position; i < position + block.length(); i++) {
//                    logger.info("Adding checksum: {} * {}", fileIdNumber, i);
                    checksum += (long) fileIdNumber * i;
                }
            }
            position += block.length();
        }

        return checksum;
    }

//    private void printArrangement(ArrayList<Block> rearrangement) {
//        var stringBuilder = new StringBuilder();
//        for (var block : rearrangement) {
//            if (block.blockSpace() instanceof FileSpace(int fileNumber)) {
//                stringBuilder.append(String.valueOf(fileNumber).repeat(block.length()));
//            } else {
//                stringBuilder.append(".".repeat(block.length()));
//            }
//        }
//        logger.info("{}: {}", "Arrangement", stringBuilder);
//    }
}

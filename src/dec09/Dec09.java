package dec09;

import framework.AOCParent;

import java.util.List;
import java.util.Objects;

public class Dec09 extends AOCParent {
    @Override
    public void part1() {
        List<Integer> diskMap = InputLoader.loadDiskMap();
        int totalSize = diskMap.stream().mapToInt(i -> i).sum();

        Integer[] disk = initializeDisk(totalSize, diskMap);
        reorderDiskPart1(disk);
        printSolution(computeChecksumPart1(disk));
    }

    @Override
    public void part2() {
        List<Integer> diskMap = InputLoader.loadDiskMap();
        int totalSize = diskMap.stream().mapToInt(i -> i).sum();

        Integer[] disk = initializeDisk(totalSize, diskMap);
        reorderDiskPart2(disk);
        printSolution(computeChecksumPart2(disk));
    }

    private static Integer[] initializeDisk(int totalSize, List<Integer> diskMap) {
        Integer[] disk = new Integer[totalSize];
        boolean data = true;
        int dataIndex = 0;
        for (int i = 0; i < diskMap.size(); i++) {
            if (data) {
                int dataId = i / 2;
                for (int j = dataIndex; j < dataIndex + diskMap.get(i); j++) {
                    disk[j] = dataId;
                }
            }
            data = !data;
            dataIndex += diskMap.get(i);
        }

        return disk;
    }

    private static void reorderDiskPart1(Integer[] disk) {
        Integer nullIndex = findNullIndex(disk, 0);
        Integer dataIndex = findDataIndex(disk, disk.length - 1);
        while (nullIndex != null && dataIndex != null && dataIndex > nullIndex) {
            disk[nullIndex] = disk[dataIndex];
            disk[dataIndex] = null;
            nullIndex = findNullIndex(disk, nullIndex);
            dataIndex = findDataIndex(disk, dataIndex);
        }
    }

    private static void reorderDiskPart2(Integer[] disk) {
        Integer nullIndex = findNullIndex(disk, 0);
        Integer dataIndex = findDataIndex(disk, disk.length - 1);
        while (nullIndex != null && dataIndex != null && dataIndex > nullIndex) {
            Integer dataBlockSize = computeDataBlockSize(disk, dataIndex);
            Integer nullBlockSize = computeNullBlockSize(disk, nullIndex);
            while (nullBlockSize < dataBlockSize) {
                nullIndex = findNullIndex(disk, nullIndex + nullBlockSize);
                if (nullIndex == null || nullIndex >= dataIndex) {
                    break;
                }
                nullBlockSize = computeNullBlockSize(disk, nullIndex);
            }
            if (nullIndex != null && nullIndex < dataIndex) {
                // Move data over
                for (int i = 0; i < dataBlockSize; i++) {
                    disk[nullIndex + i] = disk[dataIndex - i];
                    disk[dataIndex - i] = null;
                }
            }
            nullIndex = findNullIndex(disk, 0);
            dataIndex = findDataIndex(disk, dataIndex - dataBlockSize);
        }
    }

    private static Integer findNullIndex(Integer[] disk, int startingIndex) {
        for (int i = startingIndex; i < disk.length; i++) {
            if (disk[i] == null) {
                return i;
            }
        }
        return null;
    }

    private static Integer findDataIndex(Integer[] disk, int startingIndex) {
        for (int i = startingIndex; i >= 0; i--) {
            if (disk[i] != null) {
                return i;
            }
        }
        return null;
    }

    private static Integer computeDataBlockSize(Integer[] disk, int startingIndex) {
        Integer blockSize = 0;
        Integer dataId = disk[startingIndex];
        for (int i = startingIndex; Objects.equals(disk[i], dataId); i--) {
            blockSize++;
        }

        return blockSize;
    }

    private static Integer computeNullBlockSize(Integer[] disk, int startingIndex) {
        Integer blockSize = 0;
        for (int i = startingIndex; disk[i] == null; i++) {
            blockSize++;
        }

        return blockSize;
    }


    private static Long computeChecksumPart1(Integer[] disk) {
        long sum = 0;
        for (int i = 0; disk[i] != null; i++) {
            sum += disk[i] * i;
        }
        return sum;
    }

    private static Long computeChecksumPart2(Integer[] disk) {
        long sum = 0;
        for (int i = 0; i < disk.length; i++) {
            if (disk[i] != null) {
                sum += disk[i] * i;
            }
        }
        return sum;
    }
}

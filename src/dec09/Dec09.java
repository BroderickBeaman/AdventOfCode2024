package dec09;

import framework.AOCParent;

import java.util.List;

public class Dec09 extends AOCParent {
    @Override
    public void part1() {
        List<Integer> diskMap = InputLoader.loadDiskMap();
        int totalSize = diskMap.stream().mapToInt(i -> i).sum();

        Integer[] disk = initializeDisk(totalSize, diskMap);
        reorderDiskPart1(disk);
        printSolution(computeChecksum(disk));
    }

    @Override
    public void part2() {

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
        Integer dataIndex = findRightmostDataIndex(disk, disk.length - 1);
        while (nullIndex != null && dataIndex != null && dataIndex > nullIndex) {
            disk[nullIndex] = disk[dataIndex];
            disk[dataIndex] = null;
            nullIndex = findNullIndex(disk, nullIndex);
            dataIndex = findRightmostDataIndex(disk, dataIndex);
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

    private static Integer findRightmostDataIndex(Integer[] disk, int startingIndex) {
        for (int i = startingIndex; i >= 0; i--) {
            if (disk[i] != null) {
                return i;
            }
        }
        return null;
    }


    private static Long computeChecksum(Integer[] disk) {
        long sum = 0;
        for (int i = 0; disk[i] != null; i++) {
            sum += disk[i] * i;
        }
        return sum;
    }
}

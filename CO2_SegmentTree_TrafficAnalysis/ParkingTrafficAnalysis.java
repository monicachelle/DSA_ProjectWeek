class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] trafficData) {
        n = trafficData.length;
        tree = new int[4 * n];
        buildTree(trafficData, 0, 0, n - 1);
    }

    void buildTree(int[] data, int node, int start, int end) {
        if (start == end) {
            tree[node] = data[start];
        } else {
            int mid = (start + end) / 2;
            buildTree(data, 2 * node + 1, start, mid);
            buildTree(data, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    int rangeSum(int node, int start, int end, int left, int right) {
        if (right < start || end < left)
            return 0;
        if (left <= start && end <= right)
            return tree[node];
        int mid = (start + end) / 2;
        int p1 = rangeSum(2 * node + 1, start, mid, left, right);
        int p2 = rangeSum(2 * node + 2, mid + 1, end, left, right);
        return p1 + p2;
    }

    void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid)
                update(2 * node + 1, start, mid, index, value);
            else
                update(2 * node + 2, mid + 1, end, index, value);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}

public class ParkingTrafficAnalysis {
    public static void main(String[] args) {
        int[] vehiclesPerHour = {120, 150, 90, 200};

        SegmentTree trafficSystem = new SegmentTree(vehiclesPerHour);

        System.out.println("Smart Parking Traffic Analysis Report");
        int totalVehicles = trafficSystem.rangeSum(0, 0, vehiclesPerHour.length - 1, 1, 3);
        System.out.println("Total vehicles from hour 2 to hour 4: " + totalVehicles);

        System.out.println("Updating traffic count for hour 3 to reflect a new measurement...");
        trafficSystem.update(0, 0, vehiclesPerHour.length - 1, 2, 180);

        int updatedTotal = trafficSystem.rangeSum(0, 0, vehiclesPerHour.length - 1, 1, 3);
        System.out.println("Updated total vehicles from hour 2 to hour 4: " + updatedTotal);
    }
}

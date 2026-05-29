package CO1_AVL_VehicleManagement;

class VehicleNode {
    String vehicleNumber;
    VehicleNode left, right;
    int height;

    VehicleNode(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        height = 1;
    }
}

class AVLTree {
    int height(VehicleNode node) {
        return (node == null) ? 0 : node.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    VehicleNode rightRotate(VehicleNode y) {
        VehicleNode x = y.left;
        VehicleNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    VehicleNode leftRotate(VehicleNode x) {
        VehicleNode y = x.right;
        VehicleNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(VehicleNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    VehicleNode insert(VehicleNode node, String vehicleNumber) {
        if (node == null)
            return new VehicleNode(vehicleNumber);

        if (vehicleNumber.compareTo(node.vehicleNumber) < 0)
            node.left = insert(node.left, vehicleNumber);
        else if (vehicleNumber.compareTo(node.vehicleNumber) > 0)
            node.right = insert(node.right, vehicleNumber);
        else
            return node; // duplicate vehicle number is not allowed

        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && vehicleNumber.compareTo(node.left.vehicleNumber) < 0)
            return rightRotate(node);

        if (balance < -1 && vehicleNumber.compareTo(node.right.vehicleNumber) > 0)
            return leftRotate(node);

        if (balance > 1 && vehicleNumber.compareTo(node.left.vehicleNumber) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && vehicleNumber.compareTo(node.right.vehicleNumber) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void displayRecords(VehicleNode node) {
        if (node != null) {
            displayRecords(node.left);
            System.out.println("- " + node.vehicleNumber);
            displayRecords(node.right);
        }
    }
}

public class SmartParkingAVL {
    public static void main(String[] args) {
        AVLTree parkingSystem = new AVLTree();
        VehicleNode root = null;

        // Insert sample vehicle registration numbers
        root = parkingSystem.insert(root, "KA01AB1234");
        root = parkingSystem.insert(root, "KA05CD4567");
        root = parkingSystem.insert(root, "KA09EF6789");
        root = parkingSystem.insert(root, "KA11GH3456");
        root = parkingSystem.insert(root, "KA02XY2345");

        System.out.println("Smart Parking Record Management");
        System.out.println("Registered vehicles stored in the system:");
        parkingSystem.displayRecords(root);
    }
}

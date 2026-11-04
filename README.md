# Group Project

## Overview
This project is designed to manage a retail store's inventory and checkout process. It includes functionalities for scanning barcodes, managing inventory, and processing customer checkouts.

## Files
- `BarcodeScanner.py`: Scans product barcodes with the webcam (OpenCV + pyzbar), then compiles and runs the Java checkout.
- `CheckoutSystem.java`: Manages the checkout process for customers.
- `Inventory.java`: Inventory plus the `Product`, `Clothing`, `Electronics` and `Food` classes.
- `Main.java`: Standalone entry point.
- `Items.txt`, `Rewards.txt`: Product and rewards data.
- `*.png`: Sample barcodes for testing.

## Setup
1. Clone the repository to your local machine.
2. Install Python 3 and a JDK (so `javac` and `java` are on your PATH).
3. Install the Python dependencies: `pip install -r requirements.txt`
4. Run `python BarcodeScanner.py`.

## Usage
1. Start the application by running `BarcodeScanner.py`.
2. Use the barcode scanner to scan products.
3. The inventory will be updated automatically.
4. Proceed to checkout by closing the scanner.

## Contributors
- Bhrett Boggs
- William Baldwin

## License
This project is licensed under the MIT License.

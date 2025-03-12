# scanner.py

import cv2
import numpy as np
from pyzbar.pyzbar import decode, ZBarSymbol
import random
import subprocess
import os

def process_frame(frame): # Process the frame and detect barcodes
    detected = False # Flag to indicate if a barcode was detected
    gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY) # Convert to grayscale
    blurred_frame = cv2.GaussianBlur(gray_frame, (5, 5), 0) # Apply Gaussian blur

    # Decode barcodes
    decoded_objects = decode(
        blurred_frame, 
        symbols=[ZBarSymbol.QRCODE, ZBarSymbol.EAN13, ZBarSymbol.UPCA, ZBarSymbol.CODE128] # Supported barcode types
    )

    global decoded_text # Global variable to store the decoded barcode text
    decoded_text = None # Initialize to None
    for obj in decoded_objects: # Iterate through detected objects
        # Set flag to True if a barcode is detected
        detected = True

        # Draw bounding box
        points = obj.polygon
        if len(points) == 4: # If the barcode is rectangular
            pts = np.array(points, dtype=np.int32).reshape((-1, 1, 2)) # Reshape points
            cv2.polylines(frame, [pts], isClosed=True, color=(0, 255, 0), thickness=2) # Draw bounding box

        # Decode barcode text
        decoded_text = obj.data.decode('utf-8')
        x, y, w, h = obj.rect # Get bounding box coordinates
        cv2.putText(frame, f"{obj.type}: {decoded_text}", (x, y - 10),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)  # Display barcode text

    return frame, detected # Return processed frame and detected flag

# Start program
barcode_count = 0 # Initialize barcode count
choice = input("Welcome! Enter 1 if you are a rewards member, or 0 to continue as a guest: ")

with open("GroupProject/Rewards.txt", "a+") as outfile: # Open rewards file
    outfile.seek(0) # Move cursor to beginning of file
    existing_ids = {line.strip().split(',')[0] for line in outfile.readlines()} # Get existing member IDs

    if choice == '0': # Guest
        member_signup = input("Would you like to sign up to become a member? (yes/no): ").lower()
        if member_signup == "yes":
            member_ID = str(random.randint(10000, 99999))
            while member_ID in existing_ids:
                member_ID = str(random.randint(10000, 99999))

            print(f'Your new member ID is: {member_ID}')
            outfile.write(f'{member_ID},0\n')
        else:
            print("Continuing as guest.")

        cap = cv2.VideoCapture(0) # Open camera

    elif choice == '1': # Rewards member
        Member_ID = input("Enter Member ID: ")
        if Member_ID not in existing_ids: # Check if member ID is valid
            print("Invalid ID.")
            exit()
        print(f"Welcome back, Member {Member_ID}!") # Display welcome message
        cap = cv2.VideoCapture(0) # Open camera
    else:
        print("Invalid input. Exiting.")
        exit()

if not cap.isOpened(): # Check if camera is opened
    print("Error: Could not open the camera.")
    exit()

barcode_detected_count = 0 # Initialize barcode detected count
decoded_text = None # Initialize decoded text

outfile_1 = open("Items.txt", "w") # Open items file

while True: # Main loop
    ret, frame = cap.read() # Read frame
    if not ret: # Check if frame is read
        print("Error: Could not read") 
        break

    processed_frame, detected = process_frame(frame) # Process frame and detect barcodes

    if detected: # If barcode is detected
        barcode_detected_count += 1 # Increment barcode detected count
        if barcode_detected_count % 1 == 0:  # Save every detected barcode
            outfile_1.write(f'{decoded_text}\n') # Write barcode to file
            barcode_count += 1 # Increment barcode count
            print(f"Item scanned: {decoded_text} | Total Items: {barcode_count}") # Display scanned barcode

    cv2.imshow('Live Barcode Scanner', processed_frame) # Display processed frame
    if cv2.waitKey(1) & 0xFF == ord('q'): # Exit on 'q' key press
        break

cap.release() # Release camera
cv2.destroyAllWindows() # Close windows
outfile_1.close() # Close items file

print(f"Scanning complete! {barcode_count} item(s) scanned.") # Display total items scanned
print("Starting checkout system...") # Display checkout system message

# Compile and run the Java checkout system
subprocess.run(["javac", "GroupProject/CheckoutSystem.java"])
subprocess.run(["java", "GroupProject.CheckoutSystem"])

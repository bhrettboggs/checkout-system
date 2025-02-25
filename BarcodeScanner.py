import cv2
import numpy as np
from pyzbar.pyzbar import decode, ZBarSymbol
import os
import random

# Define barcode/QR code detection function
def process_frame(frame):
    detected = False
    gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blurred_frame = cv2.GaussianBlur(gray_frame, (5, 5), 0)
    global decoded_text
    # Detect barcodes and QR codes
    decoded_objects = decode(
        blurred_frame, 
        symbols=[ZBarSymbol.QRCODE, ZBarSymbol.EAN13, ZBarSymbol.UPCA, ZBarSymbol.CODE128]
    )

    decoded_text = None
    global offset 
    offset = 0
    for obj in decoded_objects:
        detected = True  # Barcode detected
        offset += 1

        
        # Draw bounding box around detected barcode
        points = obj.polygon
        if len(points) == 4:
            pts = np.array(points, dtype=np.int32)
            pts = pts.reshape((-1, 1, 2))
            cv2.polylines(frame, [pts], isClosed=True, color=(0, 255, 0), thickness=2)

        # Draw decoded text above the barcode
        decoded_text = obj.data.decode('utf-8')
        x, y, w, h = obj.rect
        cv2.putText(frame, f"{obj.type}: {decoded_text}", (x, y - 10),
                    cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
        # print(f"Decoded {obj.type}: {decoded_text}")


    return frame, detected

# Ask user to select source
barcode_count = 0
choice = input("Welcome, Enter 1 if you are a rewards member, else enter 0: ")
with open("Rewards.txt", "a+") as outfile:
    outfile.seek(0)
    existing_ids = {line.split(',')[0] for line in outfile.readlines()}



# Initialize the video source
    if choice == '0':
        member_signup = input("Would you like to sign up to become a member? (yes/no) ").lower()
        if member_signup == "yes":

            member_ID = str(random.randint(10000, 99999))
            while member_ID in existing_ids:
                member_ID = str(random.randint(10000, 99999))
        
            print(f'Your new member ID is: {member_ID}')
        
            outfile.write(f'{member_ID},0\n')
        else:
            print("continuing as guest")

        cap = cv2.VideoCapture(0)  # Use the default camera
    elif choice == '1':
        Member_ID = input("Enter Member ID")
        if Member_ID not in existing_ids:
            print("Invalid ID")
            exit()
        cap = cv2.VideoCapture(0)
    else:
        print("Invalid input. Exiting the program.")
        exit()


if not cap.isOpened():
    print("Error: Could not open the camera or video file.")
else:
    frame_idx = 0  # Frame index for saving
    barcode_detected_count = 0

    outfile_1 = open("Items.txt", "w")  # List to store detected barcode data
    readfile_1 = open("Items.txt", "r")

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break  # End of video or camera feed interrupted

        # Process frame for barcode detection
        processed_frame, detected = process_frame(frame)

        # Save frames with detected barcodes
        if detected:
            barcode_detected_count += 1
            if barcode_detected_count % 25 == 0:
                outfile_1.write(f'{decoded_text}\n')
                barcode_count +=1
            
                print(f"Item scanned: {decoded_text} Total Items: {barcode_count}")
            

        # Display the frame (for camera feed)
        if choice in ('0', '1'):
            cv2.imshow('Live Barcode Scanner', processed_frame)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

        frame_idx += 1

    # Release resources
    cap.release()
    cv2.destroyAllWindows()
    print(f"Processing completed. Total items scanned: {barcode_count}")
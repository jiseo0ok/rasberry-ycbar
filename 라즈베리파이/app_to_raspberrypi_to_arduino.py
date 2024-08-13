import bluetooth
import serial

server_socket = bluetooth.BluetoothSocket(bluetooth.RFCOMM)

port = 1
server_socket.bind(("", port))
server_socket.listen(1)

print("Waiting for Bluetooth connection...")
client_socket, address = server_socket.accept()
print("Accepted connection from", address)

arduino = serial.Serial('/dev/ttyAMA1', 9600)

while True :
    while True :
        try :
            data = client_socket.recv(1024).decode('utf-8')
            print("Received : %s" % data)
            break
        except bluetooth.btcommon.BluetoothError as e :
            print("Error : ", str(3))
            print("Attempting to restart the server...")

            client_socket.close()
            server_socket.close()

            server_socket = bluetooth.BluetoothSocket(bluetooth.FRCOMM)
            server_socket.bind(("", port))
            server_socket.listen(1)

            print("Waiting for Bluetooth connection...")
            client_socket, address = server_socket.accept()
            print("Accepted connection from", address)

    arduino.write(data.encode('utf-8'))
    try :
        receive = arduino.readline().decode('utf-8').strip()
        print(receive)
    except KeyboardInterrupt :
        arduino.close()

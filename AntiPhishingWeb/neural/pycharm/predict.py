import socket
from keras.models import Sequential
from keras.layers import Dense
from keras.models import model_from_json
import numpy
import os
import socket
import sys
import cv2
import numpy as np

import urllib.parse

import tensorflow as tf
from keras.backend.tensorflow_backend import set_session
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix, label_ranking_average_precision_score, label_ranking_loss, coverage_error

import filehelper
loaded_model=filehelper.read_object("model.bin")
print("Loaded model from disk")

op=loaded_model.predict(np.array([[-1,-1,-1,1,-1,-1,-1,1,1,-1,1,1,1]]))
print(op)
 
# Create a stream based socket(i.e, a TCP socket)

# operating on IPv4 addressing scheme

port = 7813

serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM);

 

# Bind and listen

serverSocket.bind(("127.0.0.1",port));

serverSocket.listen();

print("Listening on ", port);
classes=["phishing","normal"]
# Accept connections

while(True):

    (clientConnected, clientAddress) = serverSocket.accept();

    print("Accepted a connection request from %s:%s"%(clientAddress[0], clientAddress[1]));

   

    image_path = clientConnected.recv(1024)

    print("Got Data ", image_path);
    image_path = image_path.decode()
    #image_path = urllib.parse.unquote(image_path)	
    print("decoded ", image_path)
    image_path=image_path.replace("[","");
    image_path = image_path.replace("]", "");
    image_path = image_path.replace("+", " ");
    print("final ", image_path)

    y_pred=loaded_model.predict(np.reshape(np.fromstring(image_path, dtype=float, sep=','),[1,13]))
    print("y_pred ", y_pred)

    r = ",".join(str(x) for x in classes)
    r = str(classes[int(y_pred[0])])
    print("rrr ", r.encode())

 

    # Send some data back to the client

    clientConnected.send(r.encode());
    clientConnected.close()
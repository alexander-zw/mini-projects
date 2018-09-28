import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("/tmp/data", one_hot=True)

num_inputs = 784
num_classes = 10

num_layers = 4
# input layer, 3 hidden layers, output layer
nums_in_layer = [num_inputs, 10, 10, 10, num_classes]
batch_size = 100

# string of values, width 28x28
x = tf.placeholder("float", [None, 784])
# output, one hot
y = tf.placeholder("float")

def nn_predict(data):
    layers = [None, None, None, None] # does not include input layer
    # initialize hidden layers and output layer
    # nums_in_layer starts with the input layer, NOT the first hidden layer
    for i in range(num_layers):
        layers[i] = {
            "weights": tf.Variable(tf.random_normal(
                        [nums_in_layer[i], nums_in_layer[i + 1]])),
            "biases": tf.Variable(tf.random_normal([nums_in_layer[i + 1]]))
        }

    # calculate
    result = data
    for i in range(num_layers - 1):
        result = tf.add(tf.matmul(result, layers[i]["weights"]),
                        layers[i]["biases"])
        result = tf.nn.relu(result)

    i += 1
    result = tf.add(tf.matmul(result, layers[i]["weights"]),
                    layers[i]["biases"])
    return result

def nn_train(data):
    prediction = nn_predict(data)
    cost = tf.reduce_mean(
            tf.nn.softmax_cross_entropy_with_logits(labels=prediction, logits=y))
    optimizer = tf.train.AdamOptimizer().minimize(cost)

    num_epochs = 1

    with tf.Session() as sess:
        sess.run(tf.initialize_all_variables())

        for epoch in range(num_epochs):
            epoch_loss = 0
            for _ in range(int(mnist.train.num_examples / batch_size)):
                epoch_x, epoch_y = mnist.train.next_batch(batch_size)
                _, c = sess.run([optimizer, cost],
                                feed_dict = {x: epoch_x, y: epoch_y})
                epooch_loss += c
                print("Epoch ", epoch, " completed out of ", num_epochs,
                      "; loss: ", epoch_loss)

        correct = tf.equal(tf.argmax(prediction, 1), tf.argmax(y, 1))
        accuracy = tf.reduce_mean(tf.cast(correct, "float"))
        print("Accuracy: ", accuracy.eval({x: mnist.test.images,
                                           y: mnist.test.lables}))

print("START TRAINING")
nn_train(x)

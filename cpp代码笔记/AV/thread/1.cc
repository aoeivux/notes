#include <iostream>
#include <string>
#include <thread>

void printHello(std::string msg) {
    std::cout << msg << std::endl;
    return;
}

void addNum(int &n) {
    n += 1;
    std::cout << n << std::endl;
}

void threadTest() {
    int a = 1;
    std::thread thread2 = std::thread(addNum, std::ref(a));

    if (thread2.joinable()) {

        thread2.join();
    }
}

int main(int argc, const char **argv) {
    // int a = 1;
    std::thread thread1 = std::thread(printHello, "hello world");

    if (thread1.joinable()) {

        thread1.join();
    }

    std::cout << "over" << std::endl;
    return 0;
}
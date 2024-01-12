#include <iostream>
#include <thread>
#include <mutex>
int a=0;
std::mutex mutex;

void test() {
	for (int i = 0; i < 10000; i++)
	{
		mutex.lock();
		a+=1;
		mutex.unlock();

	}
}

int main(int argc, const char** argv) {

	std::thread t1(test);
	std::cout << a << std::endl;
	std::thread t2(test);

	t1.join();
	t2.join();
	std::cout << a << std::endl;
	return 0;
}
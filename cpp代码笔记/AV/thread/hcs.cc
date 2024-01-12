// lock_guard和unique_lock
#include <chrono>
#include <mutex>
#include <thread>

int shared_data = 1;
std::mutex mtx;
std::timed_mutex mtx1;


void fun() {
  for (int i = 0; i < 1000; i++) {
    std::lock_guard<std::mutex> lg(mtx);
    shared_data++;
  }
}

void fun1() {
	std::unique_lock<std::timed_mutex> lg1(mtx1, std::defer_lock);

	//如果在指定的时间段内成功获得了锁，则返回 true，否则返回 false
	if (lg1.try_lock_for(std::chrono::seconds(5))) {
		std::this_thread::sleep_for(std::chrono::seconds(2));
	}

}



int main(int argc, const char** argv) {
	
	std::thread t0(fun);
	std::thread t1(fun1);

	t0.join();
	t1.join();

	return 0;
}


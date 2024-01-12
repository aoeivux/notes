#include <iostream>
#include <thread>


class A {
public:
	void foo() {
		std::cout << "Hello" <<std::endl;
	}
};

int main(int argc, const char** argv) {
	A a;
	// std::thread t(&A::foo, &a);

	//
	std::unique_ptr<int> s = std::make_unique<int>(53);
}

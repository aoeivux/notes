#include <iostream>

// 每一个类的非静态成员函数
class Test {
private:
	int a = 1;

public:
	int getInfo() {
		return this->a;
	}
};


int main(int argc, const char** argv) {
	Test obj;
	Test *test = &obj;
	// <<:out  | >>:in
	std::cout << test->getInfo() << std::endl;
	return 0;
}
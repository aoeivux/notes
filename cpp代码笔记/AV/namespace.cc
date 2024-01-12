#include <iostream>


namespace MyNamespace {
    int myFunction() {
        return 42;
    }
}

// 在其他地方使用命名空间中的函数或变量
int result = MyNamespace::myFunction();
int main(int argc, const char** argv) {
	std::cout << result;
	return 0;
}
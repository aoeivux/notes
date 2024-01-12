#include <iostream>


class MyClass {
private: 
	int myPrivateNum;

public:
	MyClass() {
		myPrivateNum = 1;
	}

	void setNumber(int num){
		myPrivateNum = num;
	}

	int getNumber() {
		return myPrivateNum;
	}
};


int main(int argc, const char** argv) {
	MyClass *getnum = new MyClass();
	getnum->setNumber(2);
	int a = getnum->getNumber();
	std::cout << a << std::endl;
	return 0;
}
#include <iostream>

class Base {
public:
    virtual void show() {
        std::cout << "Base class show function" << std::endl;
    }
};

class Derived : public Base {
public:
    void show() override {
        std::cout << "Derived class show function" << std::endl;
    }
};




int main(int argc, const char** argv) {
	Base *base = new Derived;
	base->show();
	return 0;
}
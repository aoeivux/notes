#include <iostream>
#include <exception>
#include <string>

// 自定义异常类 MyException
class MyException : public std::exception {
private:
    std::string errorMsg;

public:
    MyException(const std::string& msg) : errorMsg(msg) {}

    // 重写 std::exception 的虚函数 what()，返回异常信息
    const char* what() const noexcept override {
        return errorMsg.c_str();
    }
};

//多继承
/* 
	菱形继承问题

		如果两个或多个基类共同派生自同一个基类，而一个类又同时继承这两个基类
		通过虚函数解决多继承问题
	
	二义性

	派生类中存在多个基类中拥有相同名称的成员函数或成员变量时，可能会导致函数或变量的二义性
	通过使用作用域解析运算符来明确指明调用的是哪个基类的成员
 */


class Base1 {

};

class Base2 {

};

class Derived : public Base1, public Base2 {

};



// 虚继承

class Animal {
    // Animal 的定义
};

/* class Mammal : public Animal {
    // Mammal 的定义
};

class Bird : public Animal {
    // Bird 的定义
};

class Bat : public Mammal, public Bird {
    // Bat 继承自 Mammal 和 Bird
}; */


// 上面的代码继承结构为菱形继承，有数据二义性和内存浪费的缺点

// 解决：通过虚继承方式, 将虚继承应用于 Animal 类可以确保只有一个共享的 Animal 对象，而不是在每个派生类中都创建一个新的 Animal 对象。

class Mammal : virtual public Animal {
public:
	int x;
	   void showInfo();
	   int getNumber();
    // Mammal 的定义
};

class Bird : virtual public Animal {
    // Bird 的定义
};

class Bat : public Mammal, public Bird {
    // Bat 继承自 Mammal 和 Bird
};


void Mammal::showInfo() {
	std::cout << "info...." << std::endl;
}

int Mammal::getNumber() {
	return 123;
}

int main(int argc, const char** argv) {
	Mammal obj1;
	Mammal *ptr = &obj1;
	ptr->showInfo();
	std::cout  << ptr->getNumber() << std::endl;
	if(1!=2) {
		throw MyException("不相等");
	}
	return 0;
	
}




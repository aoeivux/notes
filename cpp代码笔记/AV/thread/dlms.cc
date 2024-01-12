#include <iostream>

class MyClass {
private:
    static MyClass instance;
    int value;  // 新添加的属性

    // 私有构造函数
    MyClass() : value(0) {}  // 初始化 value 属性

public:
    static MyClass& getInstance() {
        return instance;
    }

    void setValue(int newValue) {
        value = newValue;
    }

    int getValue() const {
        return value;
    }

    void printMessage() {
        std::cout << "Hello from MyClass! Value: " << value << std::endl;
    }
};

// 需要在类外进行初始化
MyClass MyClass::instance;

int main() {
    MyClass& myInstance = MyClass::getInstance();

    // 设置属性值
    myInstance.setValue(42);

    // 使用实例调用其他成员函数
    myInstance.printMessage();

    // 获取属性值
    std::cout << "Value: " << myInstance.getValue() << std::endl;

    return 0;
}

#include <iostream>
#include <string>

class MyString {
private:
    char* data;

public:
    // 构造函数
    MyString(const char* str) {
        std::cout << "Constructor called for: " << str << std::endl;
        size_t len = strlen(str) + 1;
        data = new char[len];
        strcpy_s(data, len, str);
    }

    // 移动构造函数
    MyString(MyString&& other) noexcept { // noexcept用于指示函数是否会抛出异常。这里声明移动构造函数不会抛出异常
        std::cout << "Move constructor called" << std::endl;
        data = other.data; // 直接窃取对方的资源
        other.data = nullptr; // 将对方置为空，避免资源被释放两次
    }

    // 析构函数
    ~MyString() {
        if (data != nullptr) {
            std::cout << "Destructor called for: " << data << std::endl;
            delete[] data;
        }
    }
};

int main() {
    MyString str1("Hello"); // 构造函数被调用
    MyString str2 = std::move(str1); // 移动构造函数被调用
    return 0;
}

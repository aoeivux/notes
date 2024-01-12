
#include <iostream>
int main(int argc, const char **argv) {
    int a = 5;
    double f1 = 1.11;
    bool isF = true;

    std::cout << typeid(a).name() << std::endl;
    std::cout << typeid(f1).name() << std::endl;
    std::cout << typeid(isF).name() << std::endl;

    decltype(a) c = 5;
    std::cout << c << std::endl;

    // 左值引用 使用&申明的引用
    int x = 5;
    int &ref = x; // ref 是 x 的引用，x 是左值

    // 右值引用(c++11引入) 使用&&申明的引用 主要应用于移动语义和完美转发
    int &&rref = 5; // rref 是 5 的右值引用

    return 0;
}
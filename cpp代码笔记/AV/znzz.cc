#include <iostream>
#include <memory>

int main(int argc, const char **argv) {
    // 智能指针

    // 1.同一时间只有一个 unique_ptr 实例能拥有对动态分配内存的所有权
    std::unique_ptr<int> ptr = std::make_unique<int>(12);
    std::cout << *ptr << std::endl;

    // 2.内部维护一个引用计数,std::shared_ptr
    // 允许多个智能指针共享对同一块内存的所有权
    std::shared_ptr<int> ptr1 = std::make_shared<int>(42);
    std::shared_ptr<int> ptr2 = ptr1; // 共享所有权

    // 3.std::weak_ptr 用于解决 std::shared_ptr
    // 的循环引用问题。它不增加引用计数，可以通过 lock
    // 方法获得一个指向所管理对象的 std::shared_ptr
    std::shared_ptr<int> ptr3 = std::make_shared<int>(32);
    std::weak_ptr<int> ptr4 = ptr3;
    std::shared_ptr<int> ptr5 =
        ptr4.lock(); // 获取shared_ptr，如果对象还存在的话
    if (ptr5) {
        // 使用ptr3
    }

    return 0;
}
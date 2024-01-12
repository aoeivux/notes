// 懒汉式单例模式
#include <mutex>

class Singleton {
private:
    // 私有构造函数
    Singleton() {}

    // 私有静态成员变量，用于保存唯一实例
    static Singleton* instance;
    static std::mutex mutex;

public:
    // 公有静态成员函数，用于获取实例
    static Singleton& getInstance() {
        std::lock_guard<std::mutex> lock(mutex);
        if (instance == nullptr) {
            instance = new Singleton();
        }
        return *instance;
    }
};

// 需要在类外进行初始化
Singleton* Singleton::instance = nullptr;
std::mutex Singleton::mutex;

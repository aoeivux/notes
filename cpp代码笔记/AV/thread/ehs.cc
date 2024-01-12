// 饿汉式单例模式
class Singleton {
private:
    // 私有构造函数
    Singleton() {}

    // 私有静态成员变量，用于保存唯一实例
    static Singleton instance;

public:
    // 公有静态成员函数，用于获取实例
    static Singleton& getInstance() {
        return instance;
    }
};

// 初始化静态成员变量
Singleton Singleton::instance;

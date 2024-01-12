#include <iostream>

// 定义策略接口
class  Strategy {
public:
	virtual void execute() = 0;
	virtual ~Strategy();
};


// 具体策略 - 策略1
class ConcreteStrategy1 : public Strategy {
public:
	void execute() override {
		std::cout << "Executing Strategy 1" << std::endl;
		// 实现策略1的具体方法
	}
};


// 具体策略类 - 策略2
class ConcreteStrategy2 : public Strategy {
public:
    void execute() override {
        std::cout << "Executing Strategy 2" << std::endl;
        // 实现策略2的具体算法
    }
};


// 上下文类
class Context {
private:
	Strategy *strategy;

public:
	Context(Strategy *strategy) : strategy(strategy) {}

	void setStrategy(Strategy* newStrategy) {
		strategy = newStrategy;
	}

	void executeStrategy() {
		strategy->execute();
	}
};


int main() {
    // 创建具体策略对象
    ConcreteStrategy1 strategy1;
    ConcreteStrategy2 strategy2;

    // 创建上下文对象，并设置初始策略
    Context context(&strategy1);

    // 执行初始策略
    context.executeStrategy();

    // 更改策略为策略2，并执行
    context.setStrategy(&strategy2);
    context.executeStrategy();

    return 0;
}
